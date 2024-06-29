package com.vm.security.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vm.security.dto.Response;
import com.vm.security.dto.RoleDto;
import com.vm.security.service.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

	private final RoleService roleService;

	@PostMapping
	public Response createRole(@RequestBody final RoleDto roleDto) {
		return roleService.save(roleDto);
	}
	
	@GetMapping
	public List<RoleDto> listRole() {
		return roleService.getAllRoles();
	}
}
