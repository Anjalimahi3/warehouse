package com.ags.core.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleHistory {

	private int shId;
	private int piId;
	private double qty;
	private double pricePerUnit;
	private double totalPrice;
	private Timestamp saleDateTime;

	// DTO Use
	private String productName;
	private String vendorName;
	private String unit;
}
