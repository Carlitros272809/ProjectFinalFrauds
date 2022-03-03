package com.ibm.academia.restapi.ipFrauds.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.ibm.academia.restapi.ipFrauds.model.entities.Ip;

@FeignClient(name = "api-ip-blacklist")
public interface IpClientRest {
	
	@GetMapping("/api/v1/rest-iplocation/restapi/ipLocation/{ip}/")
	public Ip searchIpBlacklist(@PathVariable String ip, @RequestParam(value="blacklist")String blacklist);
}
