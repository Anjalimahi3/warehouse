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
public class Units {

	private int unitId;

	private String unit;

	public String getUnit() {
		if (Objects.nonNull(unit)) {
			return unit.toLowerCase();
		}
		return null;
	}
}
