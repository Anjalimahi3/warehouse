package com.ags.core.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PurchaseHistory {

	private int phId;
	private int piId;
	private double qty;
	private double pricePerUnit;
	private double totalPrice;
	private Timestamp purchaseDateTime;

	// DTO Use
	private String productName;
	private String vendorName;
	private String unit;
}
