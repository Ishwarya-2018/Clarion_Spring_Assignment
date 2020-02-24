package com.bridgelabz.fundonote;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.clarion.fundonote.dto.LoginDTO;
import com.clarion.fundonote.dto.RegistrationDTO;
import com.clarion.fundonote.model.UserDetails;
import com.clarion.fundonote.repository.UserRepository;
import com.clarion.fundonote.service.serviceimp.UserServiceImpl;
import com.clarion.fundonote.utils.JwtUtility;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FundoNoteAppApplicationTests {

	@Autowired
	private UserServiceImpl service;
	
	@MockBean
	private UserRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	

	
	@Test
	public void testRegistration()
	 {
		RegistrationDTO registraDto = new RegistrationDTO("Ishwarya ","Sp"," ishwarya.p@clariontechnologies",9980470026L,"ishu123");
		UserDetails userDetails=modelMapper.map(registraDto, UserDetails.class);
		userDetails.setId(101l);
		userDetails.setCreateTime(LocalDateTime.now());
		userDetails.setUpdateTime(LocalDateTime.now());
		userDetails.setIsverify(false);
		UserDetails dt=null;
		Optional<UserDetails> user1=Optional.ofNullable(dt);
		when(repository.findByEmailOrMobileNumber(registraDto.getEmail(), registraDto.getMobileNumber())).thenReturn(user1);
		when(repository.save(userDetails)).thenReturn(userDetails);
		assertTrue(userDetails.equals(service.registration(registraDto)));	
	}
//	
//	@Test
//	public void testLoginByEmail()
//	{
//		String email = "ishwarya.p@clariontechnologies.co.in";
//		UserDetails userDetails = new UserDetails();
//		userDetails.setId(101l);
//		userDetails.setFirstName("aish");
//		userDetails.setEmail("ishwarya.p@clariontechnologies.co.in");
//		userDetails.setPassword("md>Au4SB");
//		userDetails.setIsverify(true);
//		when(repository.findByEmail(email)).thenReturn(Optional.of(userDetails));
//		assertTrue(email.equals(service.loginEmail(email)));	
//	}
//	
	@Test
	public void testLogin()
	{
		LoginDTO loginDto = new LoginDTO();
		loginDto.setEmail(" ishwarya.p@clariontechnologies");
		loginDto.setPassword("ishu123");
		String token = JwtUtility.generateToken(101l);
		UserDetails userDetails = new UserDetails();
		userDetails.setId(101l);
		userDetails.setFirstName(" Ishwarya ");
		userDetails.setEmail(" ishwarya.p@clariontechnologies");
		//userDetails.setPassword(bCryptPasswordEncoder.encode("ishu123"));
		userDetails.setIsverify(true);
		when(repository.findByEmail(loginDto.getEmail())).thenReturn(Optional.of(userDetails));
		assertTrue(token.equals(service.login(loginDto)));	
	}
}
