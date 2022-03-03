package com.ibm.academia.restapi.ipFraudes.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ibm.academia.restapi.ipFrauds.model.entities.CountryInfo;
import com.ibm.academia.restapi.ipFrauds.services.CountryInfoService;

@SpringBootTest
public class CountryInfoServiceFeignTest {

	@Autowired
	private CountryInfoService countryInfoService;
	
	@Test
	@DisplayName("Find country Info by code")
	void findCountryInfoByCodeCountry () {
		
		//Given
		String codeCountry = "DEU";
		
		//When
		CountryInfo countryInfoExpected = countryInfoService.findInfoCountry(codeCountry);
		
		//Then
		assertThat(countryInfoExpected.getCurrencies().length >= 1).isTrue();
		
	}
	
}
