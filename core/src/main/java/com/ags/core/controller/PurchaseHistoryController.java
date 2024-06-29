package com.ags.core.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.ags.core.dto.Response;
import com.ags.core.model.PurchaseHistory;
import com.ags.core.service.PurchaseHistoryService;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/purchase")
public class PurchaseHistoryController {

	private final PurchaseHistoryService purchaseHistoryService;

	@GetMapping
	public List<PurchaseHistory> getAllPurchaseHistory() {
		return purchaseHistoryService.getAllPurchaseHistory();
	}

	@GetMapping("/{phId}")
	public PurchaseHistory getPurchaseHistoryById(@PathVariable("phId") final int phId) {
		return purchaseHistoryService.getPurchaseHistoryById(phId);
	}

	@PostMapping
	public Response createPurchaseHistory(@RequestBody final PurchaseHistory purchaseHistory) {
		return purchaseHistoryService.createPurchaseHistory(purchaseHistory);
	}

	@PutMapping("/{phId}")
	public Response updatePurchaseHistory(@PathVariable("phId") final int phId,
			@RequestBody final PurchaseHistory purchaseHistory) {
		return purchaseHistoryService.updatePurchaseHistory(phId, purchaseHistory);
	}

	@DeleteMapping("/{phId}")
	public Response deletePurchaseHistoryById(@PathVariable("phId") final int phId) {
		return purchaseHistoryService.deletePurchaseHistory(phId);
	}

}
