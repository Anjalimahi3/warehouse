package com.vm.security.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vm.security.dao.UserDao;
import com.vm.security.dto.RoleDto;
import com.vm.security.dto.UserDto;
import com.vm.security.exception.DuplicateDataException;
import com.vm.security.model.Role;
import com.vm.security.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

	private final UserDao userDao;

	private final PasswordEncoder bcryptEncoder;

	private final RoleService roleService;

	@Override
	public UserDetails loadUserByUsername(final String userEmail) throws UsernameNotFoundException {
		final User user = userDao.findByUserEmail(userEmail);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with email: " + userEmail);
		}
		final Set<Role> roles = user.getRoles();
		final List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (final Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

	public User save(final UserDto user) throws DuplicateDataException {
		final User userFromDb = userDao.findByUserEmail(user.getUserEmail());
		if (userFromDb != null) {
			throw new DuplicateDataException("Email already registered");
		}
		final User newUser = new User();
		newUser.setUserEmail(user.getUserEmail());
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setEnabled(true);
		newUser.setRoles(getRoles(user.getRoles()));
		return userDao.save(newUser);
	}

	private Set<Role> getRoles(final Set<RoleDto> roles) {
		final Set<Role> rolesDB = new HashSet<>();
		roles.forEach(role -> {
			Role roleDb = new Role();
			roleDb.setId(role.getId());
			roleDb.setName(role.getName());
			rolesDB.add(roleDb);
		});
		return roleService.saveAll(rolesDB);
	}

	public User update(final UserDto user) {
		final User userFromDb = userDao.findByUserEmail(user.getUserEmail());
		if (userFromDb == null) {
			throw new UsernameNotFoundException("User not found with email: " + user.getUserEmail());
		}
		final User newUser = new User();
		newUser.setUserEmail(user.getUserEmail());
		newUser.setUsername(user.getUsername());
		newUser.setEnabled(true);
		newUser.setRoles(getRoles(user.getRoles()));
		return userDao.save(newUser);
	}

	public User updatePassword(final String userEmail, final String password) {
		final User userFromDb = userDao.findByUserEmail(userEmail);
		if (userFromDb == null) {
			throw new UsernameNotFoundException("User not found with email: " + userEmail);
		}
		userFromDb.setPassword(bcryptEncoder.encode(password));
		return userDao.save(userFromDb);
	}

}
