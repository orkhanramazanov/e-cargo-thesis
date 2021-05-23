package com.orkhan.web.out.ecargo.controller;


import com.orkhan.web.out.ecargo.entity.User;
import com.orkhan.web.out.ecargo.message.request.LoginForm;
import com.orkhan.web.out.ecargo.message.request.SignUpForm;
import com.orkhan.web.out.ecargo.repository.UserRepository;
import com.orkhan.web.out.ecargo.security.jwt.JwtProvider;
import com.orkhan.web.out.ecargo.security.services.UserPrinciple;
import com.orkhan.web.out.ecargo.service.MailService;
import com.orkhan.web.out.ecargo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthControllerTests {

    @InjectMocks
    AuthController authController;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    UserRepository userRepository;

    @Mock
    JwtProvider jwtProvider;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserService userService;

    @Mock
    MailService mailService;

    @Before
    public void init() {
        userService = mock(UserService.class);
        mailService = mock(MailService.class);
    }


    @Test
    @WithMockUser()
    public void loginSuccess() throws Exception {
        LoginForm loginForm = new LoginForm();
        loginForm.setPassword("testtest");
        loginForm.setEmail("test@test.com");
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        User user = new User();
        user.setRole("CLIENT_ROLE");
        user.setUsername("test@test.com");
        user.setFirstname("testtest");
        user.setPassword("testtest");


        Authentication auth =
                new UsernamePasswordAuthenticationToken(UserPrinciple.build(user), "password", UserPrinciple.build(user).getAuthorities());
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("CLIENT_ROLE"));

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()))).thenReturn(auth);
        when(userRepository.findByUsername("test@test.com")).thenReturn(java.util.Optional.of(user));
        when(jwtProvider.generateJwtToken(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()))).thenReturn("67890");
        when(userRepository.findByStatus("test@test.com")).thenReturn("Verified");
        when(userRepository.getFirstName("testtest")).thenReturn("test");
        when(userRepository.getIdByUsername("test@test.com")).thenReturn(1L);
        ResponseEntity<?> responseMessage =  authController.authenticateUser(loginForm);
        assertEquals(responseMessage.getStatusCodeValue(),200);

    }

    @Test
    @WithMockUser()
    public void loginError() throws Exception {
        LoginForm loginForm = new LoginForm();
        loginForm.setPassword("testtest");
        loginForm.setEmail("test@test.com");
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        User user = new User();
        user.setRole("CLIENT_ROLE");
        user.setUsername("test@test.com");
        user.setFirstname("testtest");
        user.setPassword("testtest");


        Authentication auth =
                new UsernamePasswordAuthenticationToken(UserPrinciple.build(user), "password", UserPrinciple.build(user).getAuthorities());
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("CLIENT_ROLE"));

        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()))).thenReturn(auth);
        when(userRepository.findByUsername("test@test.com")).thenReturn(java.util.Optional.of(user));
        when(jwtProvider.generateJwtToken(new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword()))).thenReturn("67890");
        when(userRepository.findByStatus("test@test.com")).thenReturn("Not Verified");
        when(userRepository.getFirstName("testtest")).thenReturn("test");
        when(userRepository.getIdByUsername("test@test.com")).thenReturn(1L);
        ResponseEntity<?> responseMessage =  authController.authenticateUser(loginForm);
        assertEquals(responseMessage.getStatusCodeValue(),423);

    }

    @Test
    @WithMockUser()
    public void addAdminExistsAlreadyByUsername() throws Exception {


        when(userRepository.existsByUsername("admin@portal.com")).thenReturn(true);
        ResponseEntity<?> responseMessage =  authController.seedAdmin();
        assertEquals(responseMessage.getStatusCodeValue(),400);

    }

    @Test
    @WithMockUser()
    public void addAdminExistsAlreadyByEmail() throws Exception {

        when(userRepository.existsByEmail("admin@portal.com")).thenReturn(true);
        when(userRepository.existsByUsername("admin@portal.com")).thenReturn(false);
        ResponseEntity<?> responseMessage =  authController.seedAdmin();
        assertEquals(responseMessage.getStatusCodeValue(),400);

    }


    @Test
    @WithMockUser()
    public void addAdmin() throws Exception {



        User user = new User();
        user.setRole("CLIENT_ROLE");
        user.setUsername("test@test.com");
        user.setFirstname("testtest");
        user.setPassword("testtest");
        when(userRepository.existsByUsername("admin@portal.com")).thenReturn(false);
        when(userRepository.existsByEmail("admin@portal.com")).thenReturn(false);
        when(passwordEncoder.encode("admin12345#")).thenReturn("encoded");
        when(userService.getAlphaNumericString(10)).thenReturn("abc");
        when(userRepository.save(user)).thenReturn(user);
        ResponseEntity<?> responseMessage =  authController.seedAdmin();
        assertEquals(responseMessage.getStatusCodeValue(),200);

    }


    @Test
    @WithMockUser()
    public void signUpError() throws Exception {



        User user = new User();
        user.setRole("CLIENT_ROLE");
        user.setUsername("test@test.com");
        user.setFirstname("testtest");
        user.setPassword("testtest");

        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setCountry("test");
        signUpForm.setEmail("test@test.com");
        signUpForm.setUsername("test");
        signUpForm.setPhone("tests");
        signUpForm.setUserType("client");
        signUpForm.setFirstname("test");
        signUpForm.setPassword("test");
        signUpForm.setSurname("test");

        when(userRepository.existsByUsername("test")).thenReturn(true);
        ResponseEntity<?> responseMessage =  authController.registerUser(signUpForm);
        assertEquals(responseMessage.getStatusCodeValue(),400);

    }

    @Test
    @WithMockUser()
    public void signUpErrorOnEmail() throws Exception {



        User user = new User();
        user.setRole("CLIENT_ROLE");
        user.setUsername("test@test.com");
        user.setFirstname("testtest");
        user.setPassword("testtest");

        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setCountry("test");
        signUpForm.setEmail("test@test.com");
        signUpForm.setUsername("test");
        signUpForm.setPhone("tests");
        signUpForm.setUserType("client");
        signUpForm.setFirstname("test");
        signUpForm.setPassword("test");
        signUpForm.setSurname("test");

        when(userRepository.existsByUsername("test@test.com")).thenReturn(false);
        when(userRepository.existsByEmail("test@test.com")).thenReturn(true);
        ResponseEntity<?> responseMessage =  authController.registerUser(signUpForm);
        assertEquals(responseMessage.getStatusCodeValue(),400);

    }


    @Test
    @WithMockUser()
    public void signUpSuccess() throws Exception {



        User user = new User();
        user.setRole("Client");
        user.setUsername("test@test.com");
        user.setFirstname("testtest");
        user.setPassword("testtest");

        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setCountry("test");
        signUpForm.setEmail("test@test.com");
        signUpForm.setUsername("test");
        signUpForm.setPhone("tests");
        signUpForm.setUserType("client");
        signUpForm.setFirstname("test");
        signUpForm.setPassword("test");
        signUpForm.setSurname("test");

        String text = "<html><body> Your Account is successfully created";
        text += "\n\nPlease use link below to activate your account :\n" + "\n\nhttp://localhost:8080/auth/verifyEmail/"+"123"+"/" + user.getEmail();
        text += "</body></html>";

        when(userRepository.existsByUsername("test@test.com")).thenReturn(false);
        when(userRepository.existsByEmail("test@test.com")).thenReturn(false);
        when(mailService.sendMail(signUpForm.getEmail(),text,"Account Activation")).thenReturn(true);
        when(userService.getAlphaNumericString(10)).thenReturn("123");
        when(userRepository.save(user)).thenReturn(user);
        when(passwordEncoder.encode(signUpForm.getPassword())).thenReturn("123");
        ResponseEntity<?> responseMessage =  authController.registerUser(signUpForm);
        authController.registerUser(signUpForm);
        assertEquals(responseMessage.getStatusCodeValue(),200);

    }

}

