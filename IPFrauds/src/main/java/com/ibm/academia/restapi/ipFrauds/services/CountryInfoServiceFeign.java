package com.ibm.academia.restapi.ipFrauds.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.restapi.ipFrauds.clients.CountryInfoClientRest;
import com.ibm.academia.restapi.ipFrauds.model.entities.CountryInfo;

@Service
public class CountryInfoServiceFeign implements CountryInfoService{

	@Autowired
	private CountryInfoClientRest clientFeign;

	@Override
	public CountryInfo findInfoCountry(String countryCode3) {
		return new CountryInfo(clientFeign.findInfoCountry(countryCode3));
	}
	
	
	
}
