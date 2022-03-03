package com.ibm.academia.restapi.ipFrauds.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ibm.academia.restapi.ipFrauds.model.entities.Country;

@FeignClient(name="api-ip2country",url="${api-ip2country.url}")
public interface CountryClientRest {
	
	@GetMapping("/ip?{ip}")
	public Country findGeolocation(@PathVariable String ip);
	
}
