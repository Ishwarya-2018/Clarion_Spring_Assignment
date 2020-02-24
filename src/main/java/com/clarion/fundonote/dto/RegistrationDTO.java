package com.clarion.fundonote.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationDTO {

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	@NotEmpty
	@Email
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotEmpty
	@Pattern(regexp = "(^$|[0-9]{10})")
	@Size(min = 10, max = 10)
	private Long mobileNumber;

	@NotEmpty
	@Size(min = 8, max = 15)
	private String password;

	public RegistrationDTO() {
	}

	public RegistrationDTO(@NotEmpty String firstName, @NotEmpty String lastName, @NotEmpty @Email String email,
			@NotEmpty @Pattern(regexp = "(^$|[0-9]{10})") @Size(min = 10, max = 10) Long mobileNumber,
			@NotEmpty @Size(min = 8, max = 15) String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.password = password;
	}
}
