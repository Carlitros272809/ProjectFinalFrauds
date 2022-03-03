package com.ibm.academia.restapi.ipFrauds.services;

import com.ibm.academia.restapi.ipFrauds.model.entities.CountryInfo;

public interface CountryInfoService {
	public CountryInfo findInfoCountry(String countryCode3);
}
