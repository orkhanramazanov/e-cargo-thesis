package com.orkhan.web.out.ecargo.controller;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.orkhan.web.out.ecargo.entity.RoleName;
import com.orkhan.web.out.ecargo.entity.User;
import com.orkhan.web.out.ecargo.message.request.LoginForm;
import com.orkhan.web.out.ecargo.message.request.SignUpForm;
import com.orkhan.web.out.ecargo.message.response.JwtResponse;
import com.orkhan.web.out.ecargo.message.response.ResponseMessage;
import com.orkhan.web.out.ecargo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.orkhan.web.out.ecargo.repository.UserRepository;
import com.orkhan.web.out.ecargo.security.jwt.JwtProvider;
import com.orkhan.web.out.ecargo.service.MailService;
import com.orkhan.web.out.ecargo.util.Client;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController {

  final String clientUrl = Client.clientUrl;
  
  @Autowired
  MailService s;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;


	@Autowired
	MailService mailService;

	@Autowired
	UserService userService;


	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	@CrossOrigin(origins = clientUrl)
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		String status = userRepository.findByStatus(userDetails.getUsername());

		if(status.equals("Not Verified") || status.equals("Deactivated"))
		{
			ResponseMessage responseMessage=  new ResponseMessage("ID blocked or email not verified");
			return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.LOCKED);

		}

		long id = userRepository.getIdByUsername(userDetails.getUsername());
		String firstName  = userRepository.getFirstName(userDetails.getUsername());
		JwtResponse jwtRes = new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities(), id,firstName);
		return ResponseEntity.ok(jwtRes);
	}

	@GetMapping("/addAdmin")
	@CrossOrigin(origins =clientUrl)
	public ResponseEntity<?> seedAdmin() throws MessagingException {


		if (userRepository.existsByUsername("admin@portal.com")) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail("admin@portal.com")) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}

		User user = new User("Admin", "admin@portal.com", "admin@portal.com",
				encoder.encode("admin12345#"),"Azerbaijan","+36702278146","Super");


		user.setRole(RoleName.ROLE_ADMIN.toString());


		String token = userService.getAlphaNumericString(10);

		user.setStatus("Activated");
		user.setTokenVerify(token);
		user.setTokenStatus("Verified");
		userRepository.save(user);

		return new ResponseEntity<>("Seeded", HttpStatus.OK);
	}

	@PostMapping("/signup")
	@CrossOrigin(origins = clientUrl)
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) throws MessagingException {


		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}

		User user = new User(signUpRequest.getFirstname(), signUpRequest.getEmail(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()),signUpRequest.getCountry(),signUpRequest.getPhone(),signUpRequest.getSurname());

		if(signUpRequest.getUserType().equals("Client"))
		{
			user.setRole(RoleName.ROLE_CLIENT.toString());
		} else if(signUpRequest.getUserType().equals("TruckDriver")) {
			user.setRole(RoleName.ROLE_DRIVER.toString());
		} else if(signUpRequest.getUserType().equals("Admin")) {
			user.setRole(RoleName.ROLE_ADMIN.toString());
		}

        String token = userService.getAlphaNumericString(10);

		user.setStatus("Not Verified");
		user.setTokenVerify(token);
		user.setTokenStatus("Pending");
		userRepository.save(user);


		String text = "<html><body> Your Account is successfully created";
		text += "\n\nPlease use link below to activate your account :\n" + "\n\nhttp://localhost:8080/auth/verifyEmail/"+token+"/" + user.getEmail();
		text += "</body></html>";
		String subject = "Account Activation";

		mailService.sendMail(signUpRequest.getEmail(), text,subject);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}