package com.ibm.academia.restapi.ipFrauds.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ibm.academia.restapi.ipFrauds.model.dto.IpDTO;
import com.ibm.academia.restapi.ipFrauds.model.entities.Country;
import com.ibm.academia.restapi.ipFrauds.model.entities.CountryInfo;
import com.ibm.academia.restapi.ipFrauds.model.entities.Ip;
import com.ibm.academia.restapi.ipFrauds.model.mapper.IpMapper;
import com.ibm.academia.restapi.ipFrauds.model.entities.Rates;
import com.ibm.academia.restapi.ipFrauds.services.CountryInfoService;
import com.ibm.academia.restapi.ipFrauds.services.CountryService;
import com.ibm.academia.restapi.ipFrauds.services.IpService;
import com.ibm.academia.restapi.ipFrauds.services.RatesService; 

@RestController
@RequestMapping("/restapi")
public class IpController {
	
	private final static Logger logger = LoggerFactory.getLogger(IpController.class);
	
	@Autowired
	private CircuitBreakerFactory circuitBreaker;

	@Autowired
	private IpService ipService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CountryInfoService countryInfoService;
	
	@Autowired
	private RatesService ratesService;
	
	/**
	 * consultIp: Endpoint to consult all the info from a country with the ip. 
	 * @params ip to consult and value of blacklist true or false.
	 * @return Return a list of ipdto because some countries has two currencies.
	 * @author CDRC - 01-03-2022
	 */
	@GetMapping("/ipLocation/{ip}/")
	public List<?> consultIp(@PathVariable String ip, @RequestParam(value="blacklist")String blacklist) {
		List<?> ipListIpDTO = getAllInfoByFeignClients(ip,blacklist);
		return (List<?>) circuitBreaker.create("ips")
				.run(() -> ipListIpDTO , e -> alternativeMethod(e));
	}

	/**
	 * Alternative Method: for fault tolerance 
	 * @params ip to consult and value of blacklist true or false.
	 * @return Return a object type of ipdto.
	 * @author CDRC - 01-03-2022
	 */
	public IpDTO alternativeMethod(Throwable e) {
		
		logger.info(e.getMessage());
		
		IpDTO ipDto = new IpDTO();
		
		ipDto.setIp("127.0.0.1");
		ipDto.setNameCountry("United States");
		ipDto.setIsoCodeCountry("USA");
		ipDto.setCurrency("1$");
		ipDto.setDollars("USD: 1.0");
		ipDto.setEuros("EUR: 0.8918800564024948");

		return ipDto;
	}
	

	/**
	 * getAllInfoByFeignClients: method to get all the info by the apis.
	 * @params the ip will be consulted and the blacklist if it will be saved
	 * @return Return a list of type IpDTO mapped.
	 * @author CDRC - 01-03-2022
	 */
	public List<?> getAllInfoByFeignClients(String ip, String blacklist) {
		
		List<Ip> listIps = new ArrayList<Ip>();
		
		//check if the ip is in the blacklist
		Ip ipConsulted = ipService.searchIpBlacklist(ip, blacklist);
		
		/*
		(Ip) circuitBreaker.create("ips")
				.run(() -> ipService.searchIpBlacklist(ip, blacklist), e -> new Ip(ip));
		 */
		
		//get the geolocation with the ip
		Country countryConsulted = countryService.findGeolocation(ip);
		
		//get the currency of the country 
		CountryInfo countryInfoConsulted = countryInfoService.findInfoCountry(countryConsulted.getCountryCode3());	

		//get the rates of all countries and transform it to a json
		Rates ratesServiced = ratesService.findRatesByCountry();
		var ratesJsonString = ratesServiced.getRates().toString();
		JsonObject ratesJson = new Gson().fromJson(ratesJsonString, JsonObject.class);
		
		//make the conversion of the currencies of the country consulted
		listIps = currenciesConversion(ipConsulted, countryConsulted, countryInfoConsulted,ratesJson,listIps);
			
		//Map the object of ip to an object of type IpDTO to clear the data
		return mapListToIpDTOList(listIps);
		
	}
	
	/**
	 * currenciesConversion: method to make all the conversion of currency from the country consulted
	 * @params Object Ip consulted, Country consulted, CountryInfo consulted, a json of all the Rates and a List of type Ip to work with it.
	 * @return Return a list of type Ip with the all the attributes that we used.
	 * @author CDRC - 01-03-2022
	 */
	public List<Ip> currenciesConversion(Ip ip, Country country, CountryInfo countryInfo, JsonObject ratesJson, List<Ip> listIps) {
		
		//Get the rates of usd and eur to make the conversion
		double usdRate = Double.parseDouble(ratesJson.get("USD").toString());
		double eurRate = Double.parseDouble(ratesJson.get("EUR").toString());
		
		//There is a for because some countries has two currencies
		for(var i=0; i<countryInfo.getCurrencies().length; i++) {
		
			//get the first currency of the country consulted
			double countryRate = Double.parseDouble(ratesJson.get(countryInfo.getCurrencies()[i].getCode()).toString());
			
			//make the conversion to usd and eur
			double converterToUsd = usdRate/countryRate;
			double converterToEur = eurRate/countryRate;
			
			//fill the corresponding fields of currency and conversion and change them to string
			String currencyRate = "1" + countryInfo.getCurrencies()[i].getSymbol();
			String usdCurrency = "USD: " + converterToUsd;
			String eurCurrency = "EUR: " + converterToEur;
			
			//create an object of type ip and we added to the list
			listIps.add(new Ip(ip.getIp(), country, countryInfo ,currencyRate, usdCurrency, eurCurrency));
		}
		
		return listIps;
	}
	
	/**
	 * mapListToIpDTOList: method to map a list of Ip to a list of object IpDTO.
	 * @params List of type Ip.
	 * @return Return a list of type IpDTO mapped.
	 * @author CDRC - 01-03-2022
	 */
	public List<?> mapListToIpDTOList(List<Ip> ips) {
		List<IpDTO> listIpObjects = ips
				.stream()
				.map(IpMapper::mapIp)
				.collect(Collectors.toList());
		return listIpObjects;
	}
	
}
