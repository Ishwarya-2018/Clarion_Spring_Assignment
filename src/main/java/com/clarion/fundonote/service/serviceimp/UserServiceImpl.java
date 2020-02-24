package com.clarion.fundonote.service.serviceimp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clarion.fundonote.dto.LoginDTO;
import com.clarion.fundonote.dto.RegistrationDTO;
import com.clarion.fundonote.model.UserDetails;
import com.clarion.fundonote.repository.UserRepository;
import com.clarion.fundonote.service.UserService;
import com.clarion.fundonote.utils.JwtUtility;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDetails registration(RegistrationDTO registrationDto) {
		Optional<UserDetails> user = userRepository.findByEmailOrMobileNumber(registrationDto.getEmail(),
				registrationDto.getMobileNumber());//A variable whose type is Optional should never itself be null; it should always point to an Optional instance.
		UserDetails userdetails = null;//display null if User not present
		if (!user.isPresent()) {//checking for the user is present
			userdetails = modelMapper.map(registrationDto, UserDetails.class);//map the registrationDto entities to userdetails
			userdetails.setCreateTime(LocalDateTime.now());
			userdetails.setUpdateTime(LocalDateTime.now());
			userdetails.setPassword(bCryptPasswordEncoder.encode(userdetails.getPassword()));//Password  Encoder
			userdetails = userRepository.save(userdetails);
		}
		return userdetails;
	}

	@Override
	public List<UserDetails> getAllUsers(String token) {
		Long userId = JwtUtility.validateToken(token);//validate the token for a particular user by id
		Optional<UserDetails> userDetails = userRepository.findById(userId);
		List<UserDetails> users = null;//display list of user null if not present
		if (userDetails.isPresent()) {//if user present then find all the list of user
			users = userRepository.findAll();
		}
		return users;
	}

	@Override
	public String login(LoginDTO loginDto) {
		String token = null;//for login pass token and display token null if not find user by email
		Optional<UserDetails> userDetails = userRepository.findByEmail(loginDto.getEmail());
		if (userDetails.isPresent())
			token = JwtUtility.generateToken(userDetails.get().getId());//generate token if user is present
		return token;
	}
}