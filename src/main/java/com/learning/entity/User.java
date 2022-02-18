package com.learning.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.learning.utils.AddressSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@ToString

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
public class User {
	public User(String email, String name, String password, Address address) {

		this.setEmail(email);
		this.setName(name);
		this.setPassword(password);
		this.setAddress(address);

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private long id;

	@Email
	private String email;

	@NotBlank
	@JsonIgnore
	@Size(min = 2, max = 50)
	private String name;

	@NotBlank
	@Size(min = 8, max = 14)
	private String password;

	// Same family members can order food i.e many users from same address and
	// each person has only one address
	@ManyToOne
	@JoinColumn(name = "addressId")
	@JsonSerialize(using = AddressSerializer.class)
	private Address address;

	public Collection<? extends GrantedAuthority> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
