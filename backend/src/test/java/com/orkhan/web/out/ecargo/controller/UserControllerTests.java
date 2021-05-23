package com.orkhan.web.out.ecargo.controller;

import com.orkhan.web.out.ecargo.entity.Truck;
import com.orkhan.web.out.ecargo.entity.User;
import com.orkhan.web.out.ecargo.message.request.*;
import com.orkhan.web.out.ecargo.message.response.ResponseMessage;
import com.orkhan.web.out.ecargo.repository.UserRepository;
import com.orkhan.web.out.ecargo.resources.UserResource;
import com.orkhan.web.out.ecargo.service.TruckRequestService;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTests {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Mock
    TruckRequestService truckRequestService;

    @Mock
    UserRepository userRepository;


    @Before
    public void init() {
        truckRequestService = mock(TruckRequestService.class);
        userService = mock(UserService.class);
    }

    @Test
    public void getAllUser() {
        List<User> users = new ArrayList<>();
        when(userService.getAllUser()).thenReturn(users);
        ResponseEntity<?> responseMessage = userController.getAllUser();
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void deactivateUser() throws Exception {
        List<UserResource> users = new ArrayList<>();
        when(userService.deactivateUser(1L)).thenReturn(new ResponseMessage("Deactivated"));
        ResponseEntity<?> responseMessage = userController.deactivateUser(1L);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }


    @Test
    public void activateUser() throws Exception {
        List<UserResource> users = new ArrayList<>();
        when(userService.activateUser(1L)).thenReturn(new ResponseMessage("Activated"));
        ResponseEntity<?> responseMessage = userController.activateUser(1L);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void verifyEmail() throws Exception {
        when(userService.verifyEmail("token","test")).thenReturn("Verified");
        String responseMessage = userController.verifyEmail("token","test");
        assertEquals(responseMessage,null);
    }

    @Test
    public void getTrucks() throws Exception {
        when(truckRequestService.getAllTrucks()).thenReturn(new ArrayList<>());
        ResponseEntity<?> responseMessage = userController.getTrucks();
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void getTrucksByDriver() throws Exception {
        when(truckRequestService.getAllTrucksByDriver(1L)).thenReturn(new ArrayList<>());
        ResponseEntity<?> responseMessage = userController.getTrucksByDriver(1L);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void getTruckById() throws Exception {
        when(truckRequestService.getTruckById(1L)).thenReturn(java.util.Optional.of(new Truck()));
        ResponseEntity<?> responseMessage = userController.getTruckById(1L);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void updateAdminEmail() throws Exception {
        AdminEmailUpdate adminEmailUpdate = new AdminEmailUpdate();
        adminEmailUpdate.setEmail("test");
        when(userService.updateAdminEmail("test",1L)).thenReturn(new ResponseMessage("Updated"));
        ResponseEntity<?> responseMessage = userController.updateAdminEmail(1L,adminEmailUpdate);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void getTrucksByTypes() throws Exception {
        TruckTypes types= new TruckTypes();
        types.setTypes(new ArrayList<>(Arrays.asList("test")));
        when(truckRequestService.getAllTrucksByType(new ArrayList<>())).thenReturn(new ArrayList<>());
        ResponseEntity<?> responseMessage = userController.getTrucks(types);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void filterTruck() throws Exception {
        when(truckRequestService.getAllTrucksBySearch(new SearchTruck())).thenReturn(new ArrayList<>());
        ResponseEntity<?> responseMessage = userController.filterTruck(new SearchTruck());
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void updateTruckPhoto() throws Exception {
        TruckPhoto truckPhoto = new TruckPhoto();
        truckPhoto.setPhoto("test");
        when(truckRequestService.updateTruckPhoto(truckPhoto)).thenReturn(new Truck());
        ResponseEntity<?> responseMessage = userController.updateTruckPhoto(truckPhoto);
        assertEquals(responseMessage.getStatusCodeValue(),200);
    }

    @Test
    public void resetPassword() throws Exception {
        ResetPassword resetPassword= new ResetPassword();
        resetPassword.setEmail("test");
        ResponseMessage responseMessage = new ResponseMessage("Updated");
        responseMessage.setMessage("Updated");
        when(userService.resetPassword("test")).thenReturn(responseMessage);
        ResponseEntity<?> responseMessagee = userController.resetPassword(resetPassword);
        assertEquals(responseMessagee.getStatusCodeValue(),200);
    }

    @Test
    public void saveUserReturnError() throws Exception {

        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setCountry("test");
        signUpForm.setEmail("test@test.com");
        signUpForm.setUsername("test");
        signUpForm.setPhone("tests");
        signUpForm.setUserType("client");
        signUpForm.setFirstname("test");
        signUpForm.setPassword("test");
        signUpForm.setSurname("test");

        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("test");
        user1.setPassword("test");
        user1.setId(1L);
        user1.setCountry("test");
        user1.setFirstname("test");
        user1.setSurname("test");
        user1.setPhone("test");
        user1.setRole("test");
        user1.setStatus("test");

        when(userService.saveUser(1L,signUpForm)).thenReturn(user1);
        ResponseEntity<?> responseMessage = userController.saveUser(1L,signUpForm);
        assertEquals(responseMessage.getStatusCodeValue(),400);
    }

    @Test
    public void updatePasswordReturnError() throws Exception {

        PasswordForm passwordForm = new PasswordForm();
        passwordForm.setCurrentPassword("test");
        passwordForm.setNewPassword("test");

        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setUsername("test");
        user1.setPassword("test");
        user1.setId(1L);
        user1.setCountry("test");
        user1.setFirstname("test");
        user1.setSurname("test");
        user1.setPhone("test");
        user1.setRole("test");
        user1.setStatus("test");

        when(userService.updateUserPassword(1L,passwordForm)).thenReturn(user1);
        ResponseEntity<?> responseMessage = userController.updatePassword(1L,passwordForm);
        assertEquals(responseMessage.getStatusCodeValue(),400);
    }










}
