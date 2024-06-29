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
public class Vendors {

	private int vendorId;

	private String vendorName;

	public String getVendorName() {
		if (Objects.nonNull(vendorName)) {
			return vendorName.toLowerCase();
		}
		return null;
	}
}
