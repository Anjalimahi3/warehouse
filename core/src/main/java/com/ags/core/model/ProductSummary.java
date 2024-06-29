package com.ags.core.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductSummary {

	private int psId;
	private int piId;
	private Date date;
	private double openingStock;
	private double closingStock;
	private double totalPurchaseRate;
	private double totalSaleRate;
}
