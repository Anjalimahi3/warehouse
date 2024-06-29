package com.ags.core.repository;

import java.sql.Timestamp;
import java.util.List;

import com.ags.core.model.PurchaseHistory;

public interface PurchaseHistoryRepository {

	int save(final PurchaseHistory purchaseHistory);

	int update(final PurchaseHistory purchaseHistory);

	PurchaseHistory findById(int id);

	int deleteById(int id);

	List<PurchaseHistory> findAll();

	int deleteAll();

	Timestamp getMinimumTime();
	
	Timestamp getMaximumTime();

	List<PurchaseHistory> findTotalByDate(final String date);

}
