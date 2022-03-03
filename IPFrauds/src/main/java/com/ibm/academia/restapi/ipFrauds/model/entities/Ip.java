package com.ibm.academia.restapi.ipFrauds.model.entities;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Ip implements Serializable{

	private String ip;
	private Country country;
	private String currencyRate;
	private String usdCurrency;
	private String eurCurrency;
	private CountryInfo countryInfo;

	public Ip(String ip) {
		this.ip = ip;
	}
	
	public Ip(String ip, Country country, CountryInfo countryInfo,String currencyRate, String usdCurrency, String eurCurrency) {
		this.ip = ip;
		this.country = country;
		this.countryInfo = countryInfo;
		this.currencyRate = currencyRate;
		this.usdCurrency = usdCurrency;
		this.eurCurrency = eurCurrency;
	}

	private static final long serialVersionUID = 6102909516591964818L;
}
