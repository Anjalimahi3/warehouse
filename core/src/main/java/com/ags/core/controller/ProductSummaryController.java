package com.ags.core.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ags.core.dto.Response;
import com.ags.core.model.ProductSummary;
import com.ags.core.service.ProductSummaryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/summary")
public class ProductSummaryController {

	private final ProductSummaryService productSummaryService;

	@GetMapping
	public List<ProductSummary> getAllProductSummary() {
		return productSummaryService.getAllProductSummary();
	}

	@GetMapping("/{psId}")
	public ProductSummary getProductSummaryById(@PathVariable("psId") final int psId) {
		return productSummaryService.getProductSummaryById(psId);
	}

	@PostMapping
	public Response createProductSummary(@RequestBody final ProductSummary productSummary) {
		return productSummaryService.createProductSummary(productSummary);
	}

	@PutMapping("/{psId}")
	public Response updateProductSummary(@PathVariable("psId") final int psId,
			@RequestBody final ProductSummary productSummary) {
		return productSummaryService.updateProductSummary(psId, productSummary);
	}

}
