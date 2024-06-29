package com.vm.security.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vm.security.config.JwtTokenUtil;
import com.vm.security.dto.JwtRequest;
import com.vm.security.dto.JwtResponse;
import com.vm.security.dto.UserDto;
import com.vm.security.model.User;
import com.vm.security.service.JwtUserDetailsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserDetailsController {

	private final AuthenticationManager authenticationManager;

	private final JwtTokenUtil jwtTokenUtil;

	private final JwtUserDetailsService userDetailsService;

	@PostMapping(value = "/authenticate")
	public JwtResponse createAuthenticationToken(@RequestBody final JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUserEmail(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUserEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		GrantedAuthority role  = userDetails.getAuthorities().stream().findFirst().get();
		return new JwtResponse(token, userDetails.getUsername(), role.getAuthority());
	}

	@PostMapping(value = "/register")
	public User saveUser(@RequestBody final UserDto user) throws Exception {
		return userDetailsService.save(user);
	}

	@PutMapping(value = "/register")
	public User updateUser(@RequestBody final UserDto user) throws Exception {
		return userDetailsService.update(user);
	}

	@PutMapping(value = "/password")
	public User updatePassword(@RequestBody final UserDto user) throws Exception {
		return userDetailsService.updatePassword(user.getUserEmail(), user.getPassword());
	}
	
	private void authenticate(final String username, final String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (final DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (final BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}