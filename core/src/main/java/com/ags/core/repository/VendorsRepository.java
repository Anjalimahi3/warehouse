package com.ags.core.repository;

import java.util.List;

import com.ags.core.model.Vendors;

public interface VendorsRepository {

	int save(final Vendors vendors);

	int update(final Vendors vendors);

	Vendors findById(int id);

	int deleteById(int id);

	List<Vendors> findAll();

	int deleteAll();

	Vendors findByVendorName(String vendor);
}
