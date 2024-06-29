package com.vm.security.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.vm.security.dao.RoleDao;
import com.vm.security.dto.Response;
import com.vm.security.dto.RoleDto;
import com.vm.security.exception.DuplicateDataException;
import com.vm.security.model.Role;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleService {

	private final RoleDao roleDao;

	public Role findRoleByName(final String name) {
		return roleDao.findByName(name);
	}

	public List<RoleDto> getAllRoles() {
		final Iterable<Role> roles = roleDao.findAll();
		final List<RoleDto> roleDtos = new ArrayList<>();
		roles.forEach(role -> {
			roleDtos.add(new RoleDto(role.getId(), role.getName()));
		});
		return roleDtos;
	}

	public Set<Role> saveAll(final Set<Role> roles) {
		final Set<Role> role = new HashSet<Role>();
		final Iterator<Role> itr = roles.iterator();
		while (itr.hasNext()) {
			final Role roleToCheck = itr.next();
			final Role roleFromDb = findRoleByName(roleToCheck.getName());
			if (roleFromDb != null) {
				role.add(roleFromDb);
				itr.remove();
			}
		}
		final Iterable<Role> rolesFromDB = roleDao.saveAll(roles);
		rolesFromDB.forEach(role::add);
		return role;
	}

	public Response save(final RoleDto roleDto) {
		final Role roleFromDb = roleDao.findByName(roleDto.getName());
		if (roleFromDb != null) {
			throw new DuplicateDataException("Role already registered");
		}
		Role role = new Role();
		role.setName(roleDto.getName());
		role = roleDao.save(role);
		return new Response("Role created successfully with id = " + role.getId());
	}

}
