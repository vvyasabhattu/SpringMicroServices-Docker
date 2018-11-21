package org.evoke.user.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.evoke.user.model.Role;
import org.evoke.user.model.RoleEnum;
import org.evoke.user.model.User;
import org.evoke.user.model.UserResponse;
import org.evoke.user.persistence.dao.UserRepository;
import org.evoke.user.web.error.ErrorCode;
import org.evoke.user.web.error.ErrorDescription;
import org.evoke.user.web.error.ErrorType;
import org.evoke.util.DateUtil;
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
	HibernateTemplate hibernateTemplate;

	@Autowired
	Session session;

	@Override
	public UserResponse registerUser(final User user) {

		System.out.println("Checking if the user already exists");
		UserResponse response = null;
		List<User> lstUser = null;
		if( null == user.getPassword() || StringUtils.isEmpty(user.getPassword())){
			
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
			List<Role> roleLst = new ArrayList<Role>();
			Role role = new Role(RoleEnum.CUSTOMER);
			role.setCreatedUser(user.getFirstName());
			role.setUpdatedUser(user.getFirstName());
			role.setCreatedDate(DateUtil.getDDMMYYDate());
			role.setUpdatedDate(DateUtil.getDDMMYYDate());
			roleLst.add(role);
			newuser.setFirstName(user.getFirstName());
			newuser.setLastName(user.getLastName());
			newuser.setPassword(passwordEncoder.encode(user.getPassword()));

			newuser.setEmail(user.getEmail());
			newuser.setContactNumber(user.getContactNumber());
			newuser.setAddressLst(user.getAddressLst());
			newuser.setRoleLst(roleLst);
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
			//session.flush();
			//session.evict(newuser);
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
				// user = repository.getUser(user.getEmail());
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
				userDetails.setPassword(null);
				userLst.add( userDetails);
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
	public void saveRegisteredUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

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

}
