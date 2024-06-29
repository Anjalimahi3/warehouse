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
import com.ags.core.model.SaleHistory;
import com.ags.core.repository.SaleHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleHistoryService {

	private final SaleHistoryRepository saleHistoryRepository;

	private final ProductsInventoryService productsInventoryService;

	public List<SaleHistory> getAllSaleHistory() {
		final List<SaleHistory> saleHistory = new ArrayList<>();
		try {
			saleHistoryRepository.findAll().forEach(saleHistory::add);
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return saleHistory;
	}

	public SaleHistory getSaleHistoryById(final int shId) {
		SaleHistory saleHistory = null;
		try {
			saleHistory = saleHistoryRepository.findById(shId);
			if (Objects.isNull(saleHistory)) {
				throw new NoSuchElementExistsException("SaleHistory not found with id = " + shId);
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return saleHistory;
	}

	public Response createSaleHistory(final SaleHistory saleHistory) {
		try {
			if (productsInventoryService.updateProductsInventory(saleHistory.getPiId(), saleHistory.getQty(),
					ArithmeticEnum.SUBTRACT) == null) {
				throw new NoSuchElementExistsException(
						"Cannot find ProductsInventory with id = " + saleHistory.getPiId());
			}
			saleHistoryRepository.save(SaleHistory.builder().shId(saleHistory.getShId()).piId(saleHistory.getPiId())
					.qty(saleHistory.getQty()).pricePerUnit(saleHistory.getPricePerUnit())
					.totalPrice(saleHistory.getTotalPrice()).build());
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return new Response("SaleHistory created successfully");
	}

	public Response updateSaleHistory(final int shId, final SaleHistory saleHistory) {
		try {
			final SaleHistory saleHistoryFromDB = saleHistoryRepository.findById(shId);
			if (Objects.isNull(saleHistoryFromDB)) {
				throw new NoSuchElementExistsException("Cannot find SaleHistory with id = " + shId);
			} else {
				ArithmeticEnum arithOperation = null;
				double qtyToUpdate = 0;
				if (saleHistoryFromDB.getQty() > saleHistory.getQty()) {
					qtyToUpdate = saleHistoryFromDB.getQty() - saleHistory.getQty();
					arithOperation = ArithmeticEnum.ADD;
				} else {
					qtyToUpdate = saleHistory.getQty() - saleHistoryFromDB.getQty();
					arithOperation = ArithmeticEnum.SUBTRACT;
				}
				if (productsInventoryService.updateProductsInventory(saleHistory.getPiId(), qtyToUpdate,
						arithOperation) == null) {
					throw new NoSuchElementExistsException(
							"Cannot find ProductsInventory with id = " + saleHistory.getPiId());
				}
				saleHistoryRepository.update(SaleHistory.builder().shId(shId).piId(saleHistory.getPiId())
						.qty(saleHistory.getQty()).pricePerUnit(saleHistory.getPricePerUnit())
						.totalPrice(saleHistory.getTotalPrice()).build());
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return new Response("SaleHistory updated successfully");
	}

	public Response deleteSaleHistory(final int shId) {
		try {
			int result = saleHistoryRepository.deleteById(shId);
			if (result == 0) {
				throw new NoSuchElementExistsException("Cannot find SaleHistory with id = " + shId);
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return new Response("SaleHistory was deleted successfully.");
	}

	public Timestamp getMinimumTime() {
		return saleHistoryRepository.getMinimumTime();
	}
}
