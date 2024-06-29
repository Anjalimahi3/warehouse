package com.ags.core.repository;

import java.sql.Date;
import java.util.List;

import com.ags.core.model.ProductSummary;

public interface ProductSummaryRepository {

	int save(final ProductSummary productSummary);

	int update(final ProductSummary productSummary);

	ProductSummary findById(int id);

	int deleteById(int id);

	List<ProductSummary> findAll();

	int deleteAll();
	
	Date getMaxDate();

}
