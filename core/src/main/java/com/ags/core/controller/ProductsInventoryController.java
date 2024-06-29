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
import com.ags.core.model.ProductsInventory;
import com.ags.core.service.ProductsInventoryService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/master")
public class ProductsInventoryController {

	private final ProductsInventoryService productsInventoryService;

	@GetMapping("/products")
	public List<ProductsInventory> getAllProductsInventory() {
		return productsInventoryService.getAllProductsInventory();
	}

	@GetMapping("/products/{piId}")
	public ProductsInventory getProductsInventoryById(@PathVariable("piId") final int piId) {
		return productsInventoryService.getProductsInventoryById(piId);
	}

	@PostMapping("/products")
	public Response createProductsInventory(@RequestBody final ProductsInventory productsInventory) {
		return productsInventoryService.createProductsInventory(productsInventory);
	}

	@PutMapping("/products/{piId}")
	public Response updateProductsInventory(@PathVariable("piId") final int piId,
			@RequestBody final ProductsInventory productsInventory) {
		return productsInventoryService.updateProductsInventory(piId, productsInventory);
	}

	@DeleteMapping("/products/{piId}")
	public Response deleteProductsInventoryById(@PathVariable("piId") final int piId) {
		return productsInventoryService.deleteProductsInventory(piId);
	}

}
