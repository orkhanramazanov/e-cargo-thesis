package com.orkhan.web.out.ecargo.controller;

import com.orkhan.web.out.ecargo.entity.Truck;
import com.orkhan.web.out.ecargo.entity.User;
import com.orkhan.web.out.ecargo.message.request.*;
import com.orkhan.web.out.ecargo.message.response.ResponseMessage;
import com.orkhan.web.out.ecargo.service.TruckRequestService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.orkhan.web.out.ecargo.resources.UserResource;
import com.orkhan.web.out.ecargo.service.UserService;
import com.orkhan.web.out.ecargo.util.Client;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {

  @Autowired
  private UserService service;

  @Autowired
  private TruckRequestService truckRequestService;

  final String clientUrl = Client.clientUrl;

  @GetMapping("/admin/getAllUser")
  @CrossOrigin(origins = clientUrl)
  public ResponseEntity<List<User>> getAllUser() {
    return new ResponseEntity<List<User>>(service.getAllUser(), HttpStatus.OK);
  }

  @GetMapping("/admin/deactivateUser/{uid}")
  @CrossOrigin(origins = clientUrl)
  public ResponseEntity<ResponseMessage> deactivateUser(@PathVariable("uid") Long uid) throws Exception {
    return new ResponseEntity<>(service.deactivateUser(uid), HttpStatus.OK);
  }

  @GetMapping("/admin/activateUser/{uid}")
  @CrossOrigin(origins = clientUrl)
  public ResponseEntity<ResponseMessage> activateUser(@PathVariable("uid") Long uid) throws Exception {
    return new ResponseEntity<>(service.activateUser(uid), HttpStatus.OK);
  }




  @GetMapping("/getUser/{uid}")
  @CrossOrigin(origins = clientUrl)
  public ResponseEntity<UserResource> getUserById(@PathVariable("uid") Long uid) {
    UserResource ur = new UserResource();
    User user = service.getUser(uid);
    BeanUtils.copyProperties(user, ur);
    return new ResponseEntity<UserResource>(ur, HttpStatus.OK);
  }

  @GetMapping("/verifyEmail/{token}/{email}")
  @CrossOrigin(origins = clientUrl)
  public String verifyEmail(@PathVariable("token") String token,@PathVariable("email") String email) {

    try {
      return service.verifyEmail(token,email);
    }
    catch(Exception e)
    {
      return "Link has expired";
    }
  }

  @GetMapping("/trucks/getAllTrucks")
  @CrossOrigin(origins = clientUrl)
  public ResponseEntity<List<Truck>> getTrucks() throws Exception {
    return new ResponseEntity<>(truckRequestService.getAllTrucks(), HttpStatus.OK);
  }

  @GetMapping("/driver/getTrucksByDriverId/{uid}")
  @CrossOrigin(origins = clientUrl)
  public ResponseEntity<List<Truck>> getTrucksByDriver(@PathVariable("uid") Long uid) throws Exception {
    return new ResponseEntity<>(truckRequestService.getAllTrucksByDriver(uid), HttpStatus.OK);
  }

  @GetMapping("/trucks/getTruckById/{uid}")
  @CrossOrigin(origins = clientUrl)
  public ResponseEntity<Optional<Truck>> getTruckById(@PathVariable("uid") Long uid) throws Exception {
    return new ResponseEntity<Optional<Truck>>(truckRequestService.getTruckById(uid), HttpStatus.OK);
  }



  @PostMapping("/trucks/getAllTrucksByTypes")
  @CrossOrigin(origins = clientUrl)
  public ResponseEntity<List<Truck>> getTrucks(@RequestBody TruckTypes types) throws Exception {
    return new ResponseEntity<>(truckRequestService.getAllTrucksByType(types.getTypes()), HttpStatus.OK);
  }

  @PostMapping("/admin/updateEmail/{uid}")
  @CrossOrigin(origins = clientUrl)
  public ResponseEntity<ResponseMessage> updateAdminEmail(@PathVariable("uid") Long uid,@Valid @RequestBody AdminEmailUpdate email) throws Exception {
    return new ResponseEntity<>(service.updateAdminEmail(email.getEmail(),uid), HttpStatus.OK);
  }



  @PostMapping("/trucks/FilterTruck")
  @CrossOrigin(origins = clientUrl)
  public ResponseEntity<List<Truck>> filterTruck(@Valid @RequestBody SearchTruck trucks) throws Exception {
    return new ResponseEntity<>(truckRequestService.getAllTrucksBySearch(trucks), HttpStatus.OK);
  }

  @PostMapping("/driver/updateTruckPhoto")
  @CrossOrigin(origins = clientUrl)
  public ResponseEntity<?> updateTruckPhoto(@Valid @RequestBody TruckPhoto truckPhoto) throws Exception {
    return new ResponseEntity<>(truckRequestService.updateTruckPhoto(truckPhoto), HttpStatus.OK);
  }


  @PostMapping("/resetPassword")
  @CrossOrigin(origins = clientUrl)
  public ResponseEntity<ResponseMessage> resetPassword(@Valid @RequestBody ResetPassword email) throws Exception {
    return new ResponseEntity<>(service.resetPassword(email.getEmail()), HttpStatus.OK);
  }

  @PostMapping("/user/{uid}")
  @CrossOrigin(origins = clientUrl)
  public ResponseEntity<?> saveUser(@PathVariable("uid") Long uid, @Valid @RequestBody SignUpForm signUpForm) throws Exception {
    try
    {
      User user = service.saveUser(uid,signUpForm);
      UserResource ur = new UserResource();
      BeanUtils.copyProperties(user, ur);
      return new ResponseEntity<UserResource>(ur, HttpStatus.OK);
    }
    catch (Exception e)
    {
      ResponseMessage responseMessage = new ResponseMessage("Email already in use");
      return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);

    }

  }

  @PostMapping("/user/updatePassword/{uid}")
  @CrossOrigin(origins = clientUrl)
  public ResponseEntity<?> updatePassword(@PathVariable("uid") Long uid, @Valid @RequestBody PasswordForm passwordForm) throws Exception {
    try
    {
      User user = service.updateUserPassword(uid,passwordForm);
      UserResource ur = new UserResource();
      BeanUtils.copyProperties(user, ur);
      return new ResponseEntity<UserResource>(ur, HttpStatus.OK);
    }
    catch (Exception e)
    {
      ResponseMessage responseMessage = new ResponseMessage("Invalid Current Password");
      return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);

    }

  }
}
