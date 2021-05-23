package com.orkhan.web.out.ecargo.service;

import com.orkhan.web.out.ecargo.entity.User;
import com.orkhan.web.out.ecargo.message.request.PasswordForm;
import com.orkhan.web.out.ecargo.message.request.SignUpForm;
import com.orkhan.web.out.ecargo.message.response.ResponseMessage;
import com.orkhan.web.out.ecargo.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {

    @InjectMocks
    private UserService userService;


    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;



    @Test
    public void findUserByIdReturnsUser() {
        User user1 = new User();
        user1.setFirstname("Test User");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        User user = userService.getUser(1L);
        assertEquals(user1.getFirstname(),user.getFirstname());

    }

    @Test
    public void getAllUser() {
        User user1 = new User();
        user1.setFirstname("Test User");
        when(userRepository.findAll()).thenReturn(new ArrayList<User>(Arrays.asList(user1)));
        List<User> userList = userService.getAllUser();
        assertEquals(userList.get(0).getFirstname(),user1.getFirstname());
    }

    @Test
    public void saveUser() throws Exception {
        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("tests");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findByEmail("test@test.com")).thenReturn(user1);
        when(userRepository.save(user1)).thenReturn(user1);
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setCountry("test");
        signUpForm.setEmail("test@test.com");
        signUpForm.setUsername("test");
        signUpForm.setPhone("tests");
        signUpForm.setUserType("client");
        signUpForm.setFirstname("test");
        signUpForm.setPassword("test");
        signUpForm.setSurname("test");

        User saveUser = userService.saveUser(1L,signUpForm);
        assertEquals(saveUser.getFirstname(),signUpForm.getFirstname());
    }

    @Test
    public void ExceptionOnSaveUser() throws Exception {
        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("test");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findByEmail("test@test.com")).thenReturn(user1);
        when(userRepository.save(user1)).thenReturn(user1);
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setCountry("test");
        signUpForm.setEmail("test@test.com");
        signUpForm.setUsername("test");
        signUpForm.setPhone("tests");
        signUpForm.setUserType("client");
        signUpForm.setFirstname("test");
        signUpForm.setPassword("test");
        signUpForm.setSurname("test");

        try {

            User saveUser = userService.saveUser(1L, signUpForm);
        }
        catch (Exception e) {
            assertEquals(e.getMessage(),"Email already in use");
        }

    }


    @Test
    public void updateUser() throws Exception {
        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("test");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findByEmail("test@test.com")).thenReturn(null);
        when(userRepository.save(user1)).thenReturn(user1);
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setCountry("test");
        signUpForm.setEmail("test@test.com");
        signUpForm.setUsername("test");
        signUpForm.setPhone("tests");
        signUpForm.setUserType("client");
        signUpForm.setFirstname("test");
        signUpForm.setPassword("test");
        signUpForm.setSurname("test");


        User saveUser = userService.saveUser(1L, signUpForm);
        assertEquals(saveUser.getFirstname(),signUpForm.getFirstname());


    }

    @Test
    public void updateUserPassword() throws Exception {
        PasswordForm passwordForm = new PasswordForm();
        passwordForm.setNewPassword("testnew");
        passwordForm.setCurrentPassword("test");
        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("test");
        user1.setPassword("test");
        CharSequence charSequence = new StringBuffer("test");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(encoder.encode(passwordForm.getNewPassword())).thenReturn("test");
        when(encoder.matches("test","test")).thenReturn(true);
        when(userRepository.save(user1)).thenReturn(user1);
        User saveUser = userService.updateUserPassword(1L, passwordForm);
        assertEquals(saveUser.getUsername(),user1.getUsername());

    }

    @Test
    public void throwExceptionOnUpdateUserPassword() throws Exception {
        PasswordForm passwordForm = new PasswordForm();
        passwordForm.setNewPassword("testnew");
        passwordForm.setCurrentPassword("test");
        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("test");
        user1.setPassword("test");
        CharSequence charSequence = new StringBuffer("test");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(encoder.encode(passwordForm.getNewPassword())).thenReturn("test");
        when(encoder.matches("test","test")).thenReturn(false);
        when(userRepository.save(user1)).thenReturn(user1);

        try {

            User saveUser = userService.updateUserPassword(1L, passwordForm);
        }
        catch (Exception e) {
            assertEquals(e.getMessage(),"Invalid Current Password");
        }


    }

    @Test
    public void verifyEmail() throws Exception {
        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("test");
        user1.setTokenVerify("testToken");
        user1.setTokenStatus("Pending");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findByEmail("test@test.com")).thenReturn(user1);
        when(userRepository.save(user1)).thenReturn(user1);


        String verified = userService.verifyEmail("testToken", user1.getEmail());
        assertEquals(verified,"Thankyou. Email has been verified.");
    }

    @Test
    public void verifyEmaiReturnExpired() throws Exception {
        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("test");
        user1.setTokenVerify("testToken");
        user1.setTokenStatus("Expired");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.findByEmail("test@test.com")).thenReturn(user1);
        when(userRepository.save(user1)).thenReturn(user1);


        String verified = userService.verifyEmail("testToken", user1.getEmail());
        assertEquals(verified,"Link Expired");
    }

    @Test
    public void verifyEmaiReturnExpiredWhenUserNull() throws Exception {
        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("test");
        user1.setTokenVerify("testToken");
        user1.setTokenStatus("Expired");
        when(userRepository.findById(1L)).thenReturn(null);
        when(userRepository.findByEmail("test@test.com")).thenReturn(user1);
        when(userRepository.save(user1)).thenReturn(user1);


        String verified = userService.verifyEmail("testToken", user1.getEmail());
        assertEquals(verified,"Link Expired");
    }

    @Test
    public void resetPassword() throws Exception {
        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("test");
        user1.setTokenVerify("testToken");
        user1.setTokenStatus("Expired");
        when(userRepository.findByEmail("test@test.com")).thenReturn(null);
        try {
            ResponseMessage verified = userService.resetPassword(user1.getEmail());
        }
        catch(Exception e) {
            assertEquals(e.getMessage(),"Invalid Email");
        }


    }

    @Test
    public  void updateAdminEmail() throws Exception {
        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("test");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.save(user1)).thenReturn((user1));

        ResponseMessage responseMessage = userService.updateAdminEmail(user1.getEmail(),1L);
        assertEquals(responseMessage.getMessage(),"Email Updated");

    }

    @Test
    public void getNumericString() {
        String s = userService.getAlphaNumericString(10);
        assertTrue(s instanceof  String);
    }

    @Test
    public void deactivateUser() throws Exception {
        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("test");
        user1.setStatus("Activated");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.save(user1)).thenReturn((user1));
        ResponseMessage saved = userService.deactivateUser(1L);
        assertEquals(saved.getMessage(),"User Deactivated");

    }

    @Test
    public void deactivateUserReturnsError() throws Exception {
        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("test");
        user1.setStatus("Activated");
        when(userRepository.findById(1L)).thenReturn(null);
        try {
            ResponseMessage saved = userService.deactivateUser(1L);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(), "Invalid User Id");
        }
    }

    @Test
    public void activateUser() throws Exception {
        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("test");
        user1.setStatus("Deactivated");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        when(userRepository.save(user1)).thenReturn((user1));
        ResponseMessage saved = userService.activateUser(1L);
        assertEquals(saved.getMessage(),"User Activated");

    }

    @Test
    public void activateUserReturnsError() throws Exception {
        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("test");
        user1.setStatus("Deactivated");
        when(userRepository.findById(1L)).thenReturn(null);
        try {
            ResponseMessage saved = userService.activateUser(1L);
        }
        catch(Exception e) {
            assertEquals(e.getMessage(), "Invalid User Id");
        }
    }



}
