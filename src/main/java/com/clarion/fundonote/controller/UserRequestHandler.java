package com.clarion.fundonote.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clarion.fundonote.dto.LoginDTO;
import com.clarion.fundonote.dto.RegistrationDTO;
import com.clarion.fundonote.model.Response;
import com.clarion.fundonote.model.UserDetails;
import com.clarion.fundonote.service.UserService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@CrossOrigin(allowedHeaders = "*", origins = "*", exposedHeaders = { "jwtToken" })
@RequestMapping("/user")
public class UserRequestHandler {
	@Autowired
	private UserService userService;

	@PostMapping("/registration")
	public ResponseEntity<UserDetails> registerUser(@RequestBody RegistrationDTO registrationDto) {
		System.out.println("riched inside server");
		UserDetails userDetails = userService.registration(registrationDto);
		System.out.println("sending back response");
		return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
	}

//	@GetMapping("/verify_registration/{token}")
//	public ResponseEntity<String> verifyRegistration(@PathVariable String token) {
//		String message = userService.updateRegistration(token);
//		return new ResponseEntity<String>(message, HttpStatus.OK);
//	}

	@PostMapping("/login")
	public ResponseEntity<Response> loginUser(@RequestBody LoginDTO loginDto) {
		System.out.println(loginDto.getEmail());
		System.out.println(loginDto.getPassword());
		String token = userService.login(loginDto);
		System.out.println(token);
		return ResponseEntity.status(HttpStatus.OK).body(new Response(token, HttpStatus.OK.value()));
	}
	

	@GetMapping("/get_allusers")
	public ResponseEntity<List<UserDetails>> getAllUsers(@RequestHeader String token)
	{
	   List<UserDetails> users = userService.getAllUsers(token);
	   return new ResponseEntity<List<UserDetails>>(users,HttpStatus.OK);
	  // return new ResponseEntity<>(new Response(HttpStatus.OK.value(),"user list",userList),HttpStatus.OK);
	  // System.out.println(users);
	}
	}
