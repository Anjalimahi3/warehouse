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
import com.ags.core.model.SaleHistory;
import com.ags.core.service.SaleHistoryService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sale")
public class SaleHistoryController {

	private final SaleHistoryService saleHistoryService;

	@GetMapping
	public List<SaleHistory> getAllSaleHistory() {
		return saleHistoryService.getAllSaleHistory();
	}

	@GetMapping("/{shId}")
	public SaleHistory getSaleHistoryById(@PathVariable("shId") final int shId) {
		return saleHistoryService.getSaleHistoryById(shId);
	}

	@PostMapping
	public Response createSaleHistory(@RequestBody final SaleHistory saleHistory) {
		return saleHistoryService.createSaleHistory(saleHistory);
	}

	@PutMapping("/{shId}")
	public Response updateSaleHistory(@PathVariable("shId") final int shId,
			@RequestBody final SaleHistory saleHistory) {
		return saleHistoryService.updateSaleHistory(shId, saleHistory);
	}

	@DeleteMapping("/{shId}")
	public Response deleteSaleHistoryById(@PathVariable("shId") final int shId) {
		return saleHistoryService.deleteSaleHistory(shId);
	}

}
