package com.ags.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ags.core.dto.Response;
import com.ags.core.exception.NoSuchElementExistsException;
import com.ags.core.exception.VDLDataAccessException;
import com.ags.core.model.Vendors;
import com.ags.core.repository.VendorsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VendorsService {

	private final VendorsRepository vendorsRepository;

	public List<Vendors> getAllVendors() {
		final List<Vendors> vendors = new ArrayList<>();
		try {
			vendorsRepository.findAll().forEach(vendors::add);
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return vendors;
	}

	public Vendors getVendorsById(final int vendorId) {
		Vendors vendors = null;
		try {
			vendors = vendorsRepository.findById(vendorId);
			if (Objects.isNull(vendors)) {
				throw new NoSuchElementExistsException("Vendor not found with id = " + vendorId);
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return vendors;
	}

	public Response createVendors(final Vendors vendors) {
		Vendors updatedVendors = null;
		try {
			vendorsRepository.save(new Vendors(vendors.getVendorId(), vendors.getVendorName()));
			updatedVendors = vendorsRepository.findByVendorName(vendors.getVendorName());
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return new Response("Vendor created successfully with id = " + updatedVendors.getVendorId());
	}

	public Response updateVendors(final int vendorId, final Vendors vendors) {
		try {
			final Vendors vendorsFromDB = vendorsRepository.findById(vendorId);
			if (Objects.isNull(vendorsFromDB)) {
				throw new NoSuchElementExistsException("Vendor not found with id = " + vendorId);
			} else {
				vendorsFromDB.setVendorName(vendors.getVendorName());
				vendorsRepository.update(vendorsFromDB);
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return new Response("Vendors updated successfully");
	}

	public Response deleteVendors(final int vendorId) {
		try {
			int result = vendorsRepository.deleteById(vendorId);
			if (result == 0) {
				throw new NoSuchElementExistsException("Cannot find Vendors with id = " + vendorId);
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return new Response("Vendors was deleted successfully.");
	}

	public Vendors findByVendorName(final String vendorName) {
		Vendors vendors = null;
		try {
			vendors = vendorsRepository.findByVendorName(vendorName);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return vendors;
	}

	public Vendors getOrCreateVendors(final String vendorName) {
		Vendors vendorsFromDb = findByVendorName(vendorName);
		if (Objects.isNull(vendorsFromDb)) {
			vendorsFromDb = createVendors(0, vendorName);
		}
		return vendorsFromDb;
	}

	private Vendors createVendors(final int vendorId, final String vendorName) {
		vendorsRepository.save(new Vendors(vendorId, vendorName));
		return findByVendorName(vendorName);
	}

}
