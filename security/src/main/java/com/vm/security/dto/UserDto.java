package com.vm.security.dto;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	@NotBlank(message = "User Name is Mandatory")
	@Min(value = 3, message = "User Name should have aleast 3 characters")
	@Max(value = 45, message = "User Name should have atmost 45 characters")
	private String username;

	@NotBlank(message = "Password is Mandatory")
	@Min(value = 8, message = "Password should have aleast 8 characters")
	@Max(value = 20, message = "Password should have atmost 20 characters")
	private String password;

	@NotBlank(message = "Email is Mandatory")
	private String userEmail;
	
	private Set<RoleDto> roles = new HashSet<>();
}
