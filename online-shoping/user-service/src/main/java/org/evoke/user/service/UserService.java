package org.evoke.user.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.transaction.Transactional;

import org.evoke.user.model.AddressReq;
import org.evoke.user.model.BaseResponse;
import org.evoke.user.model.User;
import org.evoke.user.web.error.UserAlreadyExistException;


@Transactional
public interface UserService {
	
	BaseResponse registerUser(User accountDto) throws UserAlreadyExistException;

	BaseResponse getUser(int userId);	

    void saveRegisteredUser(User user);

    void deleteAddress(AddressReq adrReq);

    void createVerificationTokenForUser(User user, String token);

    void createPasswordResetTokenForUser(User user, String token);

    User findUserByEmail(String email);

    //PasswordResetToken getPasswordResetToken(String token);

    User getUserByPasswordResetToken(String token);

    //Optional<UserUser> getUserByID(long id);

    void changeUserPassword(User user, String password);

    boolean checkIfValidOldPassword(User user, String password);

    String validateVerificationToken(String token);

    String generateQRUrl(User user) throws UnsupportedEncodingException;

    User updateUser2FA(boolean use2FA);

    List<String> getUsersFromSessionRegistry();

    BaseResponse updateUser(User user);
    void updateUserAddress(User user);
    void  updateUserRole(User user);
    BaseResponse insertAddress(AddressReq adr);
}
