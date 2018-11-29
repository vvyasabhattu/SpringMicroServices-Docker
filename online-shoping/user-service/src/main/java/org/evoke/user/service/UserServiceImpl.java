package org.evoke.user.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.evoke.user.model.Address;
import org.evoke.user.model.AddressReq;
import org.evoke.user.model.AddressResponse;
import org.evoke.user.model.AddressResponseLst;
import org.evoke.user.model.BaseResponse;
import org.evoke.user.model.Role;
import org.evoke.user.model.User;
import org.evoke.user.model.UserResponse;
import org.evoke.user.persistence.dao.UserDao;
import org.evoke.user.persistence.dao.UserRepository;
import org.evoke.user.util.DateUtil;
import org.evoke.user.web.error.ErrorCode;
import org.evoke.user.web.error.ErrorDescription;
import org.evoke.user.web.error.ErrorType;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	HibernateTemplate hibernateTemplate;

	@Autowired
	Session session;

	@Autowired
	UserRepository userRepo;

	@Override
	public UserResponse registerUser(final User user) {

		System.out.println("Checking if the user already exists");
		UserResponse response = null;
		List<User> lstUser = null;
		if (null == user.getPassword() || StringUtils.isEmpty(user.getPassword())) {

			response = new UserResponse();
			response.setErrorCode(ErrorCode.PASSWORD_NOT_VALID);
			response.setErrorDesc(ErrorDescription.PASSWORD_NOT_VALID);
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
			return response;
		}

		if (emailExist(user.getEmail())) {

			response = new UserResponse();
			response.setErrorCode(ErrorCode.EMAIL_ALREADY_EXISTS);
			response.setErrorDesc(ErrorDescription.USER_EMIAL_EXIST);
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);

			return response;
		}

		System.out.println("Registering new user");

		try {
			final User newuser = new User();

			newuser.setFirstName(user.getFirstName());
			newuser.setLastName(user.getLastName());
			newuser.setPassword(passwordEncoder.encode(user.getPassword()));

			newuser.setEmail(user.getEmail());
			newuser.setContactNumber(user.getContactNumber());
			newuser.setAddressLst(user.getAddressLst());
			newuser.setCreatedUser(user.getFirstName());
			newuser.setUpdatedUser(user.getFirstName());
			newuser.setCreatedDate(DateUtil.getDDMMYYDate());
			newuser.setUpdatedDate(DateUtil.getDDMMYYDate());
			if (null != newuser.getAddressLst() && newuser.getAddressLst().size() > 0) {
				newuser.getAddressLst().get(0);
				newuser.getAddressLst().get(0).setCreatedDate(DateUtil.getDDMMYYDate());
				newuser.getAddressLst().get(0).setUpdatedDate(DateUtil.getDDMMYYDate());
				newuser.getAddressLst().get(0).setCreatedUser(user.getFirstName());
				newuser.getAddressLst().get(0).setUpdatedUser(user.getFirstName());

			}
			session.save(newuser);

			// session.flush();
			// session.evict(newuser);

			User user1 = session.get(User.class, newuser.getId());
			System.out.println("user id..........." + user1.getId());
			List<Role> roleLst = new ArrayList<Role>();
			Role role = new Role("Customer");
			role.setCreatedUser(user.getFirstName());
			role.setUpdatedUser(user.getFirstName());
			role.setCreatedDate(DateUtil.getDDMMYYDate());
			role.setUpdatedDate(DateUtil.getDDMMYYDate());
			role.setUser(user1);
			roleLst.add(role);
			user1.setRoleLst(roleLst);
			session.saveOrUpdate(user1);
			session.flush();

			response = new UserResponse();
			lstUser = new ArrayList<User>();
			newuser.setPassword(null);
			lstUser.add(newuser);
			response.setUserLst(lstUser);
		} catch (Exception ex) {
			System.out.println("Exception in UserServiceImpl.registerUser() " + ex.getMessage());
			response = new UserResponse();
			response.setErrorCode(ErrorCode.EMAIL_ALREADY_EXISTS);
			response.setErrorDesc(ex.getMessage());
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);

		}
		return response;
	}

	@SuppressWarnings("unchecked")
	public UserResponse userLogin(User user) {
		UserResponse response = null;
		List<User> userLst = null;
		if (emailExist(user.getEmail())) {
			if (checkIfValidPassword(user)) {
				response = new UserResponse();
				userLst = new ArrayList<User>();
				Query query = session.createQuery("from User where email=:email");
				query.setParameter("email", user.getEmail());
				List<User> list = query.list();
				if (null != list && list.size() > 0) {
					user = list.get(0);
				}
				if (null != user) {
					user.setPassword(null);
					userLst.add(user);
					response.setUserLst(userLst);
				} else {

					response = new UserResponse();
					response.setErrorCode(ErrorCode.USER_NOT_FOUND);
					response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
					response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
				}

			} else {

				response = new UserResponse();
				response.setErrorCode(ErrorCode.PASSWORD_NOT_VALID);
				response.setErrorDesc(ErrorDescription.PASSWORD_NOT_VALID);
				response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);

			}

		} else {

			response = new UserResponse();
			response.setErrorCode(ErrorCode.USER_NOT_FOUND);
			response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);

		}
		return response;
	}

	public boolean emailExist(final String email) {

		return repository.findByEmail(email) != null;
	}

	public boolean checkIfValidPassword(User user) {

		// System.out.println(passwordEncoder.encode(user.getPassword()));
		// System.out.println(repository.getUserPassword(user.getEmail()));
		CharSequence pass = user.getPassword();
		return passwordEncoder.matches(pass, repository.getUserPassword(user.getEmail()));
	}

	@Override
	public UserResponse getUser(int userId) {
		// TODO Auto-generated method stub
		User userDetails = null;
		UserResponse response = null;
		List<User> userLst = null;
		try {

			userDetails = session.get(User.class, userId);

			if (null != userDetails) {
				userLst = new ArrayList<User>();
				userDetails.setPassword(userDetails.getPassword());
				userLst.add(userDetails);
				response = new UserResponse();
				response.setUserLst(userLst);

			} else {

				response = new UserResponse();
				response.setErrorCode(ErrorCode.USER_NOT_FOUND);
				response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
				response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);

			}

		} catch (NumberFormatException ne) {

			response = new UserResponse();
			response.setErrorCode(ErrorCode.VALID_NUMBER_REQUIRED);
			response.setErrorDesc(ErrorDescription.VALID_NUMBER_REQUIRED);
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);

		} catch (Exception e) {
			response = new UserResponse();
			response.setErrorCode(ErrorCode.USER_NOT_FOUND);
			response.setErrorDesc(ErrorDescription.USER_NOT_FOUND);
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);

		}

		return response;
	}

	@Override
	public UserResponse updateUser(User user) {

		UserResponse response = null;
		List<User> lstUser = null;

		try {
			session.clear();

			user.setCreatedUser(user.getFirstName());
			user.setUpdatedUser(user.getFirstName());
			user.setCreatedDate(DateUtil.getDDMMYYDate());
			user.setUpdatedDate(DateUtil.getDDMMYYDate());

			session.update(user);
			session.flush();
			System.out.println("User Updated successfully.....!!");
			response = new UserResponse();
			lstUser = new ArrayList<User>();
			lstUser.add(user);
			response.setUserLst(lstUser);

		} catch (Exception e) {
			System.out.println("Exception while updating user ..........." + e);
			response.setErrorCode(ErrorCode.USER_DETAILS_OBJECT_NOT_FOUND);
			response.setErrorDesc(e.getMessage());
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
		}
		return response;
	}

	@Override
	public AddressResponse updateUserAddress(AddressReq adrReq) {

		AddressResponse response = null;

		try {

			List<Address> addressLst = null;

			session.clear();

			adrReq.getAddress().setCreatedDate(DateUtil.getDDMMYYDate());
			adrReq.getAddress().setUpdatedDate(DateUtil.getDDMMYYDate());

			User user = session.load(User.class, adrReq.getAddress().getUser().getId());
			adrReq.getAddress().setCreatedUser(user.getFirstName());
			adrReq.getAddress().setUpdatedUser(user.getLastName());

			session.saveOrUpdate(adrReq.getAddress());

			session.flush();
			System.out.println("User Updated successfully.....!!");
			response = new AddressResponse();
			addressLst = new ArrayList<Address>();
			addressLst.add(adrReq.getAddress());
			response.setAddressLst(addressLst);

		} catch (Exception e) {
			System.out.println("Exception while updating User Address ..........." + e);
			response.setErrorCode(ErrorCode.USER_DETAILS_OBJECT_NOT_FOUND);
			response.setErrorDesc(e.getMessage());
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
		}
		return response;
	}

	@Override
	public UserResponse updateUserRole(User user) {

		UserResponse response = null;
		List<User> lstUser = null;

		try {
			session.clear();

			List<Role> roleList = new ArrayList<Role>();
			User user1 = session.get(User.class, user.getId());

			user.setCreatedUser(user1.getFirstName());
			user.setUpdatedUser(user1.getFirstName());
			user.setUpdatedDate(DateUtil.getDDMMYYDate());
			user.setCreatedDate(DateUtil.getDDMMYYDate());

			user.setEmail(user1.getEmail());
			user.setContactNumber(user1.getContactNumber());
			user.setFirstName(user1.getFirstName());
			user.setLastName(user1.getLastName());

			if (user.getRoleLst() != null && user.getRoleLst().size() > 0) {
				for (Role role : user.getRoleLst()) {
					role.setCreatedUser(user.getFirstName());
					role.setUpdatedUser(user.getFirstName());
					role.setCreatedDate(DateUtil.getDDMMYYDate());
					role.setUpdatedDate(DateUtil.getDDMMYYDate());
					role.setUser(user);
					roleList.add(role);
					user.setRoleLst(roleList);
				}
			}

			session.clear();
			session.update(user);

			session.flush();
			response = new UserResponse();
			lstUser = new ArrayList<User>();
			lstUser.add(user);
			response.setUserLst(lstUser);

		} catch (Exception e) {
			System.out.println("Exception while updating/Adding User Role ..........." + e);
		}
		return response;
	}

	public AddressResponse insertAddress(AddressReq adrReq) {

		AddressResponse response = null;
		try {
			List<Address> addressLst = null;
			session.clear();

			adrReq.getAddress().setCreatedDate(DateUtil.getDDMMYYDate());
			adrReq.getAddress().setUpdatedDate(DateUtil.getDDMMYYDate());

			User user = session.load(User.class, adrReq.getAddress().getUser().getId());
			adrReq.getAddress().setCreatedUser(user.getFirstName());
			adrReq.getAddress().setUpdatedUser(user.getLastName());

			session.save(adrReq.getAddress());

			session.flush();
			System.out.println("User Updated successfully.....!!");
			response = new AddressResponse();
			addressLst = new ArrayList<Address>();
			addressLst.add(adrReq.getAddress());
			response.setAddressLst(addressLst);

		} catch (HibernateException e) {
			response.setErrorCode(ErrorCode.USER_DETAILS_OBJECT_NOT_FOUND);
			response.setErrorDesc(e.getMessage());
			response.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
		}
		return response;
	}

	@Override
	public void saveRegisteredUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public BaseResponse deleteAddress(AddressReq adrReq) {
		BaseResponse baseResponse = new BaseResponse();
		Map<String, Object> responseMessage = new HashMap<>();
		try {
			session.clear();
			session.delete(session.get(Address.class, adrReq.getAddress().getId()));
			session.flush();
			responseMessage.put("SUCCESS",
					"Address deleted successfully with addressId: " + adrReq.getAddress().getId());
		} catch (Exception e) {
			baseResponse.setErrorCode(ErrorCode.USER_DETAILS_OBJECT_NOT_FOUND);
			baseResponse.setErrorDesc(e.getMessage());
			baseResponse.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
			responseMessage.put("FAILED", "Address deletion failed with addressId: " + adrReq.getAddress().getId());
		}
		baseResponse.setResponse(responseMessage);
		return baseResponse;
	}

	@Override
	public BaseResponse deleteUser(User user) {
		BaseResponse baseResponse = new BaseResponse();
		Map<String, Object> responseMessage = new HashMap<>();
		try {
			session.clear();
			session.delete(session.get(User.class, user.getId()));
			session.flush();
		} catch (Exception e) {
			System.out.println("excpetion......." + e);
			baseResponse.setErrorCode(ErrorCode.USER_DETAILS_OBJECT_NOT_FOUND);
			baseResponse.setErrorDesc(e.getMessage());
			baseResponse.setErrorType(ErrorType.APPLICATION_BUSINESS_ERROR);
			responseMessage.put("FAILED", "User deletion failed with userid: " + user.getId());
		}
		baseResponse.setResponse(responseMessage);
		return baseResponse;
	}

	@Override
	public void createVerificationTokenForUser(User user, String token) {
		// TODO Auto-generated method stub

	}

	/*
	 * @Override public VerificatlionToken getVerificationToken(String
	 * VerificationToken) { // TODO Auto-generated method stub return null; }
	 * 
	 * @Override public VerificationToken generateNewVerificationToken(String token)
	 * { // TODO Auto-generated method stub return null; }
	 */

	@Override
	public void createPasswordResetTokenForUser(User user, String token) {
		// TODO Auto-generated method stub

	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByPasswordResetToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeUserPassword(User user, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkIfValidOldPassword(User user, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String validateVerificationToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateQRUrl(User user) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser2FA(boolean use2fa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getUsersFromSessionRegistry() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public AddressResponseLst getAddress(int userId) {
		
		return userDao.getAddress(userId);
	}

}
