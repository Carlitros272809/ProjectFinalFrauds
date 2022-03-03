package com.ibm.academia.restapi.ipFraudes.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ibm.academia.restapi.ipFrauds.model.entities.Rates;
import com.ibm.academia.restapi.ipFrauds.services.RatesService;

@SpringBootTest
public class RatesServiceFeignTest {

	@Autowired
	private RatesService ratesService;
	
	@Test
	@DisplayName("Find rates all of countries")
	void findRatesAllTheCountries () {
		
		//When
		Rates ratesExpectedd = ratesService.findRatesByCountry();
		var ratesJsonString = ratesExpectedd.getRates().toString();
		JsonObject ratesJson = new Gson().fromJson(ratesJsonString, JsonObject.class);
		
		//Then
		assertThat(ratesJson.size() >= 1).isTrue();
		
	}
	
}
