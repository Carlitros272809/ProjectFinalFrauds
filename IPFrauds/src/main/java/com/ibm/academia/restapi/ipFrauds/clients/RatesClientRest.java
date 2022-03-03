package com.ibm.academia.restapi.ipFrauds.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import com.ibm.academia.restapi.ipFrauds.model.entities.Rates;

@FeignClient(name = "api-dta-latest", url = "${api-dta-latest.url}")
public interface RatesClientRest {

	@GetMapping
	public Rates findRatesByCountry();

}
