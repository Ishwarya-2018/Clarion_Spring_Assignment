package com.clarion.fundonote.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "User_Details")
public class UserDetails {
	@Id
	@Column(name = "User_Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "First_Name", nullable = false, length = 50)
	private String firstName;

	@Column(name = "Last_Name", nullable = false, length = 50)
	private String lastName;

	@Column(name = "Email", nullable = false, unique = true, length = 100)
	private String email;

	@Column(name = "Mobile_Number", nullable = false, unique = true)
	private Long mobileNumber;

	@Column(name = "Password", nullable = false)
	private String password;

	@Column(name = "isverify", columnDefinition = "tinyint(1) default 0")
	private boolean isverify;

	@Column(name = "Create_Time", nullable = false)
	private LocalDateTime createTime;

	@Column(name = "Update_Time", nullable = false)
	private LocalDateTime updateTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public boolean isIsverify() {
		return isverify;
	}

	public void setIsverify(boolean isverify) {
		this.isverify = isverify;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public List<UserNotes> getNotes() {
		return notes;
	}

	public void setNotes(List<UserNotes> notes) {
		this.notes = notes;
	}

	@Column(nullable = false)//Specifies the mapped column for a persistent property or field. If no Column annotation is specified, the default values apply.
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "User_Id")//Specifies a column for joining an entity association or element collection. If the JoinColumn annotation itself is defaulted, a single join column is assumed and the default values apply
	private List<UserNotes> notes = new ArrayList<UserNotes>();


}
