package com.ags.core.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ags.core.dto.Response;
import com.ags.core.model.Vendors;
import com.ags.core.service.VendorsService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/vendors")
public class VendorsController {

	private final VendorsService vendorsService;

	@GetMapping
	public List<Vendors> getAllVendors() {
		return vendorsService.getAllVendors();
	}

	@GetMapping("/{vendorId}")
	public Vendors getVendorsById(@PathVariable("vendorId") final int vendorId) {
		return vendorsService.getVendorsById(vendorId);
	}

	@PostMapping
	public Response createVendors(@RequestBody final Vendors vendors) {
		return vendorsService.createVendors(vendors);
	}

	@PutMapping("/{vendorId}")
	public Response updateVendors(@PathVariable("vendorId") final int vendorId,
			@RequestBody final Vendors vendors) {
		return vendorsService.updateVendors(vendorId, vendors);
	}

	@DeleteMapping("/{vendorId}")
	public Response deleteVendorsById(@PathVariable("vendorId") final int vendorId) {
		return vendorsService.deleteVendors(vendorId);
	}

}
