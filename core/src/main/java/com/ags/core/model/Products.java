package com.ags.core.model;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Products {

	private int productId;

	private String productName;

	public String getProductName() {
		if (Objects.nonNull(productName)) {
			return this.productName.toLowerCase();
		}
		return null;
	}

}
