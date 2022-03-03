package com.ibm.academia.restapi.ipFrauds.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.restapi.ipFrauds.clients.CountryClientRest;
import com.ibm.academia.restapi.ipFrauds.model.entities.Country;

@Service
public class CountryServiceFeign implements CountryService{

	@Autowired
	private CountryClientRest clientFeign;
	
	@Override
	public Country findGeolocation(String ip) {
		return new Country(clientFeign.findGeolocation(ip).getCountryName(),clientFeign.findGeolocation(ip).getCountryCode3());
	}

	
	
}
