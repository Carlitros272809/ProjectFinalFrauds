package com.ibm.academia.restapi.ipFrauds.model.entities;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CountryInfo implements Serializable{
	
	private Currency[] currencies;
	
	public CountryInfo (CountryInfo countryInfo) {
		this.currencies = countryInfo.getCurrencies();
	}

	private static final long serialVersionUID = -570511560783353363L;
}