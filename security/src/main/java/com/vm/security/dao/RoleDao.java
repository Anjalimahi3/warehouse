package com.vm.security.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vm.security.model.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, Integer> {

	Role findByName(final String name);
}
