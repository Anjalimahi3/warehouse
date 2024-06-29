package com.ags.core.model;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductsInventory {

	private int piId;
	@JsonIgnore
	private int productId;
	@JsonIgnore
	private int vendorId;
	private double qty;
	@JsonIgnore
	private int unitId;
	private Timestamp modifiedDate;

	// DTO Use
	private String productName;
	private String vendorName;
	private String unit;

}
