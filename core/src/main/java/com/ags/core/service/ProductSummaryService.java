package com.ags.core.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.ags.core.dto.Response;
import com.ags.core.exception.NoSuchElementExistsException;
import com.ags.core.exception.VDLDataAccessException;
import com.ags.core.model.ProductSummary;
import com.ags.core.model.PurchaseHistory;
import com.ags.core.repository.ProductSummaryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductSummaryService {

	private final ProductSummaryRepository productSummaryRepository;

	private final PurchaseHistoryService purchaseHistoryService;

	private final SaleHistoryService saleHistoryService;

	private final ProductsInventoryService productsInventoryService;

	@PostConstruct
	private void loadSummary() {
		final Date currentDate = new Date(System.currentTimeMillis());
		System.err.println("================PostContruct================");
		Date maxDate = productSummaryRepository.getMaxDate();
		if (Objects.isNull(maxDate)) {
			maxDate = currentDate;
		}
		System.err.println(maxDate);
		final Timestamp minimumPurchaseTime = purchaseHistoryService.getMinimumTime();
		if (Objects.isNull(minimumPurchaseTime)) {
		} else {
			System.err.println("Minimum Purchase DateTime :: " + minimumPurchaseTime);
		}
		final Timestamp minimumSaleTime = saleHistoryService.getMinimumTime();
		if (Objects.isNull(minimumSaleTime)) {
		} else {
			System.err.println("Minimum Sale DateTime :: " + minimumSaleTime);
		}

		
		Timestamp timeStampToLoadSummary = null;

		if (minimumPurchaseTime.before(minimumSaleTime)) {
			timeStampToLoadSummary = minimumPurchaseTime;
		} else {
			timeStampToLoadSummary = minimumSaleTime;
		}
		Date dateToLoadSummary = new Date(timeStampToLoadSummary.getTime());
		List<PurchaseHistory> purchaseHistory = purchaseHistoryService
				.getPurchaseHistoryByDate(dateToLoadSummary.toString());
		purchaseHistory.forEach(System.out::println);
	}

	public List<ProductSummary> getAllProductSummary() {
		final List<ProductSummary> productSummary = new ArrayList<>();
		try {
			productSummaryRepository.findAll().forEach(productSummary::add);
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return productSummary;
	}

	public ProductSummary getProductSummaryById(final int psId) {
		ProductSummary productSummary = null;
		try {
			productSummary = productSummaryRepository.findById(psId);
			if (Objects.isNull(productSummary)) {
				throw new NoSuchElementExistsException("ProductSummary not found with id = " + psId);
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return productSummary;
	}

	public Response createProductSummary(final ProductSummary productSummary) {
		try {
			productSummaryRepository.save(new ProductSummary(productSummary.getPsId(), productSummary.getPiId(),
					productSummary.getDate(), productSummary.getOpeningStock(), productSummary.getClosingStock(),
					productSummary.getTotalPurchaseRate(), productSummary.getTotalSaleRate()));
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return new Response("ProductSummary created successfully ");
	}

	public Response updateProductSummary(final int psId, final ProductSummary productSummary) {
		try {
			final ProductSummary productSummaryFromDB = productSummaryRepository.findById(psId);
			if (Objects.isNull(productSummaryFromDB)) {
				throw new NoSuchElementExistsException("ProductSummary not found with id = " + psId);
			} else {
				productSummaryFromDB.setPiId(productSummary.getPiId());
				productSummaryFromDB.setDate(productSummary.getDate());
				productSummaryFromDB.setOpeningStock(productSummary.getOpeningStock());
				productSummaryFromDB.setClosingStock(productSummary.getClosingStock());
				productSummaryFromDB.setTotalPurchaseRate(productSummary.getTotalPurchaseRate());
				productSummaryFromDB.setTotalSaleRate(productSummary.getTotalSaleRate());
				productSummaryRepository.update(productSummaryFromDB);
			}
		} catch (final DataAccessException e) {
			throw new VDLDataAccessException(e.getMessage());
		}
		return new Response("ProductSummary updated successfully");
	}

}
