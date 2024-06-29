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
import com.ags.core.model.Products;
import com.ags.core.service.ProductsService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductsController {

	private final ProductsService productsService;

	@GetMapping
	public List<Products> getAllProducts() {
		return productsService.getAllProducts();
	}

	@GetMapping("/{productId}")
	public Products getProductsById(@PathVariable("productId") final int productId) {
		return productsService.getProductsById(productId);
	}

	@PostMapping
	public Response createProducts(@RequestBody final Products products) {
		return productsService.createProducts(products);
	}

	@PutMapping("/{productId}")
	public Response updateProducts(@PathVariable("productId") final int productId,
			@RequestBody final Products products) {
		return productsService.updateProducts(productId, products);
	}

	@DeleteMapping("/{productId}")
	public Response deleteProductsById(@PathVariable("productId") final int productId) {
		return productsService.deleteProducts(productId);
	}

}
