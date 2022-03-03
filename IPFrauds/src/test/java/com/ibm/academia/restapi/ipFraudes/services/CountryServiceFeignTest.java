package com.ibm.academia.restapi.ipFraudes.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ibm.academia.restapi.ipFrauds.model.entities.Country;
import com.ibm.academia.restapi.ipFrauds.services.CountryService;

@SpringBootTest
public class CountryServiceFeignTest {

	@Autowired
	private CountryService countryService;
	
	@Test
	@DisplayName("Find country by Ip")
	void findCountryByIp () {
		
		//Given
		String ip = "5.6.7.8";
		
		//When
		Country countryExpected = countryService.findGeolocation(ip);
		
		//Then
		assertThat(countryExpected.getCountryName()).isEqualTo("Germany");
		assertThat(countryExpected.getCountryCode3()).isEqualTo("DEU");
		
	}
}
