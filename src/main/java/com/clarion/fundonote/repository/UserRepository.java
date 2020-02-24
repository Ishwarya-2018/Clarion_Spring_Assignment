package com.clarion.fundonote.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clarion.fundonote.model.UserDetails;

@Repository//Indicates that an annotated class is a "Repository"
public interface UserRepository extends JpaRepository<UserDetails, Long> {
	Optional<UserDetails> findByEmailAndPassword(String email, String password);

	Optional<UserDetails> findByEmail(String email);

	Optional<UserDetails> findByEmailOrMobileNumber(String email, Long mobileNumber);
}
