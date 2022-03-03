package com.ibm.academia.restapi.ipFrauds.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ibm.academia.restapi.ipFrauds.model.entities.CountryInfo;

@FeignClient(name="restcountries", url="${restcountries.url}")
public interface CountryInfoClientRest {
	
	@GetMapping("{countryCode3}")
	public CountryInfo findInfoCountry(@PathVariable String countryCode3);
	
}
