package com.ags.core.repository;

import java.util.List;

import com.ags.core.model.ProductsInventory;

public interface ProductsInventoryRepository {

	int save(final ProductsInventory productsInventory);

	int update(final ProductsInventory productsInventory);

	ProductsInventory findById(int id);

	int deleteById(int id);

	List<ProductsInventory> findAll();

	int deleteAll();
	
	ProductsInventory findByProductVendorUnit(int productId, int vendorId, int unitId);

}
