package com.ags.core.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ProductsInventoryDto {

	private int id;
	private String productName;
	private String vendorName;
	private double qty;
	private String unit;
	private Timestamp modifiedDate;

}
