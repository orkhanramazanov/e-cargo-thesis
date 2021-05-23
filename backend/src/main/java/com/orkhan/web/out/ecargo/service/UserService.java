package com.orkhan.web.out.ecargo.service;

import java.util.List;
import java.util.Optional;

import com.orkhan.web.out.ecargo.entity.User;
import com.orkhan.web.out.ecargo.message.request.PasswordForm;
import com.orkhan.web.out.ecargo.message.request.SignUpForm;
import com.orkhan.web.out.ecargo.message.response.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.orkhan.web.out.ecargo.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private MailService mailService;
 
  public List<User> getAllUser() {
      List<User> users= userRepository.findAll();

      return users;
  }
 
  
  public User getUser(long id) {
    return userRepository.findById(id).get();
  }

  public User saveUser(long id, SignUpForm signUpForm) throws Exception{
    User user = getUser(id);


    User exists = userRepository.findByEmail(signUpForm.getEmail());
    if(exists != null) {
      if(exists.getEmail() != user.getUsername()){
        user.setCountry(signUpForm.getCountry());
        user.setEmail(signUpForm.getEmail());
        user.setPhone(signUpForm.getPhone());
        user.setFirstname(signUpForm.getFirstname());
        user.setSurname(signUpForm.getSurname());
        user.setUsername(signUpForm.getEmail());

        return userRepository.save(user);
      }
      else {
        throw new DuplicateKeyException("Email already in use");
      }

    }
    else {
        user.setCountry(signUpForm.getCountry());
        user.setEmail(signUpForm.getEmail());
        user.setPhone(signUpForm.getPhone());
        user.setFirstname(signUpForm.getFirstname());
        user.setSurname(signUpForm.getSurname());
        user.setUsername(signUpForm.getEmail());

        return userRepository.save(user);

    }

  }

    public User updateUserPassword(long id, PasswordForm passwordForm) throws Exception{
        User user = getUser(id);
        if(user != null) {
           if(encoder.matches(passwordForm.getCurrentPassword(),user.getPassword())) {
               user.setPassword(encoder.encode(passwordForm.getNewPassword()));
               return userRepository.save(user);

           }

        }

            throw new Exception("Invalid Current Password");

    }

    public String verifyEmail(String token,String email) throws Exception{
        User user = userRepository.findByEmail(email);

        if(user != null) {
            try {
                String currentToken = user.getTokenVerify();
                if(currentToken.equals(token) && user.getTokenStatus().equals("Pending"))
                {
                    user.setTokenStatus("Verified");
                    user.setStatus("Activated");
                    userRepository.save(user);
                    return "Thankyou. Email has been verified.";
                }
                return "Link Expired";

            }
            catch(Exception e){
                return "Invalid Email";
            }


        }

        return "Link Expired";
    }

    public ResponseMessage resetPassword(String email) throws Exception{
        User user = userRepository.findByEmail(email);

        if(user != null) {
            try {
                String temppassword = getAlphaNumericString(10);
                user.setPassword(encoder.encode(temppassword));
                userRepository.save(user);

                mailService.sendMail(email,"Hello! \n In order to be able to reset your password,please use the temporary password below. \n Please login and update it. Thank you!\n\nPassword : " + temppassword + "","Reset Password");

                return  new ResponseMessage("Password Sent");
            }
           catch(Exception e){
               throw new Exception(e.getMessage());
           }


        }

        throw new Exception("Invalid Email");

    }

    public ResponseMessage updateAdminEmail(String email,long id) throws Exception{
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent()) {
            try {

                user.get().setEmail(email);
                user.get().setUsername(email);
                userRepository.save(user.get());


                return  new ResponseMessage("Email Updated");
            }
            catch(Exception e){
                throw new Exception(e.getMessage());
            }


        }

        throw new Exception("Invalid Email");

    }

    public String getAlphaNumericString(int n)
    {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    public ResponseMessage deactivateUser(long id) throws Exception{
        Optional<User> user = userRepository.findById(id);

        if(user != null) {
            try {

                user.get().setStatus("Deactivated");
                userRepository.save(user.get());


                return  new ResponseMessage("User Deactivated");
            }
            catch(Exception e){
                throw new Exception(e.getMessage());
            }


        }

        throw new Exception("Invalid User Id");

    }

    public ResponseMessage activateUser(long id) throws Exception{
        Optional<User> user = userRepository.findById(id);

        if(user != null) {
            try {

                user.get().setStatus("Activated");
                userRepository.save(user.get());


                return  new ResponseMessage("User Activated");
            }
            catch(Exception e){
                throw new Exception(e.getMessage());
            }


        }

        throw new Exception("Invalid User Id");

    }

}
