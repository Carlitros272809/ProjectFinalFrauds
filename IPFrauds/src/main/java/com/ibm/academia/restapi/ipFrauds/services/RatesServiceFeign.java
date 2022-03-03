package com.ibm.academia.restapi.ipFrauds.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.restapi.ipFrauds.clients.RatesClientRest;
import com.ibm.academia.restapi.ipFrauds.model.entities.Rates;

@Service
public class RatesServiceFeign implements RatesService{
	
	@Autowired
	private RatesClientRest clientFeign;

	@Override
	public Rates findRatesByCountry() {
		return new Rates(clientFeign.findRatesByCountry());
	}

}
