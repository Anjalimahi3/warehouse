package com.ags.core.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ags.core.dto.Response;
import com.ags.core.exception.NoSuchElementExistsException;
import com.ags.core.exception.VDLDataAccessException;
import com.ags.core.model.Products;
import com.ags.core.repository.ProductsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductsService {

	private final ProductsRepository productsRepository;

	public List<Products> getAllProducts() {
		final List<Products> products = new ArrayList<>();
		try {
			productsRepository.findAll().forEach(products::add);
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return products;
	}

	public Products getProductsById(final int productId) {
		Products products = null;
		try {
			products = productsRepository.findById(productId);
			if (Objects.isNull(products)) {
				throw new NoSuchElementExistsException("Product not found with id = " + productId);
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return products;
	}

	public Response createProducts(final Products products) {
		Products updatedProducts = null;
		try {
			updatedProducts = createProducts(products.getProductId(), products.getProductName());
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return new Response("Product created successfully with id = " + updatedProducts.getProductId());
	}

	private Products createProducts(int productId, String productName) {
		productsRepository.save(new Products(productId, productName));
		return findByProductName(productName);
	}

	public Response updateProducts(final int productId, final Products products) {
		Products productsFromDB = null;
		try {
			productsFromDB = updateProducts(productId, products.getProductName());
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return new Response("Products updated successfully with id = " + productsFromDB.getProductId());
	}

	private Products updateProducts(int productId, String productName) {
		final Products productsFromDB = productsRepository.findById(productId);
		if (Objects.isNull(productsFromDB)) {
			throw new NoSuchElementExistsException("Product not found with id = " + productId);
		} else {
			productsFromDB.setProductName(productName);
			productsRepository.update(productsFromDB);
		}
		return productsFromDB;
	}

	public Response deleteProducts(final int productId) {
		try {
			int result = productsRepository.deleteById(productId);
			if (result == 0) {
				throw new NoSuchElementExistsException("Cannot find Products with id = " + productId);
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return new Response("Products was deleted successfully.");
	}

	public Products findByProductName(final String productName) {
		Products products = null;
		try {
			products = productsRepository.findByProductName(productName);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	public Products getOrCreateProduct(String productName) {
		Products productsFromDb = findByProductName(productName);
		if (Objects.isNull(productsFromDb)) {
			productsFromDb = createProducts(0, productName);
		}
		return productsFromDb;
	}

}
