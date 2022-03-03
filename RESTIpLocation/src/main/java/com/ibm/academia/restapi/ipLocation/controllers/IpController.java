package com.ibm.academia.restapi.ipLocation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.ipLocation.exceptions.FoundBlackListException;
import com.ibm.academia.restapi.ipLocation.model.entities.Ip;
import com.ibm.academia.restapi.ipLocation.services.IpDAO;

@RestController
@RequestMapping("/restapi")
public class IpController {
	
	private final static Logger logger = LoggerFactory.getLogger(IpController.class);
	
	@Autowired
	private IpDAO ipDao;
	
	/**
	 * Endpoint to check if the ip is in the blacklist or not is. 
	 * @params ip and blacklist if is true, the ip will be registered in the blacklist if the ip is false it will continue.
	 * @return Return an object with the ip.
	 * @FoundBlackListException if the ip is already in the lacklist it will have an error
	 * @author CDRC - 28-02-2022
	 */
	@GetMapping("/ipLocation/{ip}/")
	public ResponseEntity<?> buscarIpEnBlacklist(@PathVariable String ip, @RequestParam(value="blacklist")String blacklist) {
		Iterable<Ip> ipBlacklist = ipDao.findIpByIp(ip);

		Ip ipReturnObject = new Ip(ip);

		if(!ipBlacklist.iterator().hasNext()) {
			if(blacklist.equals("true")) {
				ipDao.saveIpBlacklist(ipReturnObject);
			} 
		} else {
			throw new FoundBlackListException(String.format("Ip %s is in the BLACKLIST", ip));
		}
		
		try 
		{
			Thread.sleep(2000L);
		} 
		catch (InterruptedException e) 
		{
			logger.info("mensaje: " + e.getMessage() + " Causa: " + e.getCause());
			e.printStackTrace();
		}
		
		return new ResponseEntity<Ip> (ipReturnObject, HttpStatus.OK);
	}
}
