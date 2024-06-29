package com.ags.core.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ags.core.constants.ArithmeticEnum;
import com.ags.core.dto.Response;
import com.ags.core.exception.NoSuchElementExistsException;
import com.ags.core.exception.VDLDataAccessException;
import com.ags.core.model.PurchaseHistory;
import com.ags.core.repository.PurchaseHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseHistoryService {

	private final PurchaseHistoryRepository purchaseHistoryRepository;

	private final ProductsInventoryService productsInventoryService;

	public List<PurchaseHistory> getAllPurchaseHistory() {
		final List<PurchaseHistory> purchaseHistory = new ArrayList<>();
		try {
			purchaseHistoryRepository.findAll().forEach(purchaseHistory::add);
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return purchaseHistory;
	}

	public PurchaseHistory getPurchaseHistoryById(final int phId) {
		PurchaseHistory purchaseHistory = null;
		try {
			purchaseHistory = purchaseHistoryRepository.findById(phId);
			if (Objects.isNull(purchaseHistory)) {
				throw new NoSuchElementExistsException("PurchaseHistory not found with id = " + phId);
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return purchaseHistory;
	}

	public Response createPurchaseHistory(final PurchaseHistory purchaseHistory) {
		try {
			if (productsInventoryService.updateProductsInventory(purchaseHistory.getPiId(), purchaseHistory.getQty(),
					ArithmeticEnum.ADD) == null) {
				throw new NoSuchElementExistsException(
						"Cannot find ProductsInventory with id = " + purchaseHistory.getPiId());
			}
			purchaseHistoryRepository
					.save(PurchaseHistory.builder().phId(purchaseHistory.getPhId()).piId(purchaseHistory.getPiId())
							.qty(purchaseHistory.getQty()).pricePerUnit(purchaseHistory.getPricePerUnit())
							.totalPrice(purchaseHistory.getTotalPrice()).build());
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return new Response("PurchaseHistory created successfully");
	}

	public Response updatePurchaseHistory(final int phId, final PurchaseHistory purchaseHistory) {
		try {
			final PurchaseHistory purchaseHistoryFromDB = purchaseHistoryRepository.findById(phId);
			if (Objects.isNull(purchaseHistoryFromDB)) {
				throw new NoSuchElementExistsException("Cannot find PurchaseHistory with id = " + phId);
			} else {
				ArithmeticEnum arithOperation = null;
				double qtyToUpdate = 0;
				if (purchaseHistoryFromDB.getQty() > purchaseHistory.getQty()) {
					qtyToUpdate = purchaseHistoryFromDB.getQty() - purchaseHistory.getQty();
					arithOperation = ArithmeticEnum.SUBTRACT;
				} else {
					qtyToUpdate = purchaseHistory.getQty() - purchaseHistoryFromDB.getQty();
					arithOperation = ArithmeticEnum.ADD;
				}
				if (productsInventoryService.updateProductsInventory(purchaseHistory.getPiId(), qtyToUpdate,
						arithOperation) == null) {
					throw new NoSuchElementExistsException(
							"Cannot find ProductsInventory with id = " + purchaseHistory.getPiId());
				}
				purchaseHistoryRepository.update(PurchaseHistory.builder().phId(phId).piId(purchaseHistory.getPiId())
						.qty(purchaseHistory.getQty()).pricePerUnit(purchaseHistory.getPricePerUnit())
						.totalPrice(purchaseHistory.getTotalPrice()).build());
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return new Response("PurchaseHistory updated successfully");
	}

	public Response deletePurchaseHistory(final int phId) {
		try {
			int result = purchaseHistoryRepository.deleteById(phId);
			if (result == 0) {
				throw new NoSuchElementExistsException("Cannot find PurchaseHistory with id = " + phId);
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return new Response("PurchaseHistory was deleted successfully.");
	}

	public Timestamp getMinimumTime() {
		return purchaseHistoryRepository.getMinimumTime();
	}

	public List<PurchaseHistory> getPurchaseHistoryByDate(String date) {
		return purchaseHistoryRepository.findTotalByDate(date);
	}

}
