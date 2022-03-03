package com.ibm.academia.restapi.ipFrauds.services;

import com.ibm.academia.restapi.ipFrauds.model.entities.Country;

public interface CountryService {
	public Country findGeolocation(String ip);
}
