package com.ags.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ags.core.constants.ArithmeticEnum;
import com.ags.core.dto.Response;
import com.ags.core.exception.DuplicateDataException;
import com.ags.core.exception.NoSuchElementExistsException;
import com.ags.core.exception.OutOfStockException;
import com.ags.core.exception.VDLDataAccessException;
import com.ags.core.model.Products;
import com.ags.core.model.ProductsInventory;
import com.ags.core.model.Units;
import com.ags.core.model.Vendors;
import com.ags.core.repository.ProductsInventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsInventoryService {

	private final ProductsInventoryRepository productsInventoryRepository;

	private final ProductsService productsService;

	private final VendorsService vendorsService;

	private final UnitsService unitsService;

	public List<ProductsInventory> getAllProductsInventory() {
		final List<ProductsInventory> productsInventory = new ArrayList<>();
		try {
			productsInventoryRepository.findAll().forEach(productsInventory::add);
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return productsInventory;
	}

	public ProductsInventory getProductsInventoryById(final int piId) {
		ProductsInventory productsInventory = null;
		try {
			productsInventory = productsInventoryRepository.findById(piId);
			if (Objects.isNull(productsInventory)) {
				throw new NoSuchElementExistsException("ProductsInventory not found with id = " + piId);
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return productsInventory;
	}

	public Response createProductsInventory(final ProductsInventory productsInventory) {
		try {
			final Products products = productsService.getOrCreateProduct(productsInventory.getProductName());
			if (Objects.isNull(products)) {
				throw new NoSuchElementExistsException(
						"Error in creating or finding product id for name = " + productsInventory.getProductName());
			}

			final Vendors vendors = vendorsService.getOrCreateVendors(productsInventory.getVendorName());
			if (Objects.isNull(vendors)) {
				throw new NoSuchElementExistsException(
						"Error in creating or finding vendor id for name = " + productsInventory.getVendorName());
			}

			final Units units = unitsService.getOrCreateUnits(productsInventory.getUnit());
			if (Objects.isNull(units)) {
				throw new NoSuchElementExistsException(
						"Error in creating or finding unit id for name = " + productsInventory.getUnit());
			}

			final ProductsInventory productsInventoryFromDb = productsInventoryRepository
					.findByProductVendorUnit(products.getProductId(), vendors.getVendorId(), units.getUnitId());
			if (Objects.nonNull(productsInventoryFromDb)) {
				throw new DuplicateDataException("ProductInventory found duplicate");
			}
			productsInventoryRepository.save(ProductsInventory.builder().piId(productsInventory.getPiId())
					.productId(products.getProductId()).vendorId(vendors.getVendorId()).qty(productsInventory.getQty())
					.unitId(units.getUnitId()).build());
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return new Response("ProductsInventory created successfully");
	}

	public Response updateProductsInventory(final int piId, final ProductsInventory productsInventory) {
		try {
			ProductsInventory productsInventoryFromDB = productsInventoryRepository.findById(piId);
			if (Objects.isNull(productsInventoryFromDB)) {
				throw new NoSuchElementExistsException("Cannot find ProductsInventory with id = " + piId);
			} else {

				final Products products = productsService.getOrCreateProduct(productsInventory.getProductName());
				if (Objects.isNull(products)) {
					throw new NoSuchElementExistsException(
							"Error in creating or finding product id for name = " + productsInventory.getProductName());
				}

				final Vendors vendors = vendorsService.getOrCreateVendors(productsInventory.getVendorName());
				if (Objects.isNull(vendors)) {
					throw new NoSuchElementExistsException(
							"Error in creating or finding vendor id for name = " + productsInventory.getVendorName());
				}

				final Units units = unitsService.getOrCreateUnits(productsInventory.getUnit());
				if (Objects.isNull(units)) {
					throw new NoSuchElementExistsException(
							"Error in creating or finding unit id for name = " + productsInventory.getUnit());
				}

				productsInventoryRepository.update(ProductsInventory.builder().piId(piId)
						.productId(products.getProductId()).vendorId(vendors.getVendorId())
						.qty(productsInventory.getQty()).unitId(units.getUnitId()).build());
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return new Response("ProductsInventory updated successfully");
	}

	public Response deleteProductsInventory(final int piId) {
		try {
			int result = productsInventoryRepository.deleteById(piId);
			if (result == 0) {
				throw new NoSuchElementExistsException("Cannot find ProductsInventory with id = " + piId);
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return new Response("ProductsInventory was deleted successfully.");
	}

	public ProductsInventory updateProductsInventory(final int piId, final double qty,
			final ArithmeticEnum arithmeticEnum) {
		try {
			final ProductsInventory productsInventoryFromDB = productsInventoryRepository.findById(piId);
			if (Objects.nonNull(productsInventoryFromDB)) {
				double updatedQty = 0;
				final double qtyFromDb = productsInventoryFromDB.getQty();
				if (arithmeticEnum == ArithmeticEnum.ADD) {
					updatedQty = qtyFromDb + qty;
				} else if (arithmeticEnum == ArithmeticEnum.SUBTRACT) {
					if (qtyFromDb >= qty) {
						updatedQty = qtyFromDb - qty;
					} else {
						throw new OutOfStockException(String.format(
								"Requested quantity(%f %s) is greater than available quantity(%f %s) ", qty,
								productsInventoryFromDB.getUnit(), qtyFromDb, productsInventoryFromDB.getUnit()));
					}
				} else {
					updatedQty = qty;
				}
				productsInventoryRepository
						.update(ProductsInventory.builder().piId(piId).productId(productsInventoryFromDB.getProductId())
								.vendorId(productsInventoryFromDB.getVendorId()).qty(updatedQty)
								.unitId(productsInventoryFromDB.getUnitId()).build());
				return productsInventoryFromDB;
			}
		} catch (final DataAccessException e) {
			e.printStackTrace();
			throw new VDLDataAccessException(e.getMessage());
		}
		return null;
	}

}
