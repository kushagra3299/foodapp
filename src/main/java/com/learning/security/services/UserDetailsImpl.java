package com.learning.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.entity.Address;
import com.learning.entity.User;

import lombok.Data;

@Data

public class UserDetailsImpl implements UserDetails {

	private Long id;
	private String userName;
	private String name;

	private String email;
	private String address;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;
	//authorities are Roles here
	
	private UserDetailsImpl(Long id, String userName,String name, String email, String password, String address2,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.userName = userName;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address2;
		this.authorities = authorities;
	}
    
	public UserDetailsImpl(long id2, String userName2, String name2, String email2, String password2, Address address2,
			List<GrantedAuthority> authorities2) {
		// TODO Auto-generated constructor stub
	}

	//we have to read these authorities and should be available to UserDetailsImpl
	//we have used builder design pattern
	public static UserDetailsImpl build(User user) {
		//it should build userdetailsimpl objects
		
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role-> new SimpleGrantedAuthority(null))
				.collect(Collectors.toList());
	   //stream will transform set to stream. stream is flow of data
		
		return new UserDetailsImpl(user.getId(), user.getUserName(),user.getName(), user.getEmail(), user.getPassword(),user.getAddress(), authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o)
	      return true;
	    if (o == null || getClass() != o.getClass())
	      return false;
	    UserDetailsImpl user = (UserDetailsImpl) o;
	    return Objects.equals(id, user.id);
	  }

}
