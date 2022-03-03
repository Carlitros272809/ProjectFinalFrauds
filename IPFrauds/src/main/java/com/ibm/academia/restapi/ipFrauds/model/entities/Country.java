package com.ibm.academia.restapi.ipFrauds.model.entities;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Country implements Serializable {
	
	private String countryCode3;
	private String countryName;
	
	public Country(String countryName, String countryCode3) {
		this.countryName = countryName;
		this.countryCode3 = countryCode3;
	}

	private static final long serialVersionUID = -570511560783353363L;
}
