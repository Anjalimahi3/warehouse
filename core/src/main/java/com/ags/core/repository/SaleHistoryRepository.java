package com.ags.core.repository;

import java.sql.Timestamp;
import java.util.List;

import com.ags.core.model.SaleHistory;

public interface SaleHistoryRepository {

	int save(final SaleHistory saleHistory);

	int update(final SaleHistory saleHistory);

	SaleHistory findById(int id);

	int deleteById(int id);

	List<SaleHistory> findAll();

	int deleteAll();
	
	Timestamp getMinimumTime();
}
