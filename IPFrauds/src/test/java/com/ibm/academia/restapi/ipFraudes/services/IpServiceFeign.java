package com.ibm.academia.restapi.ipFraudes.services;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ibm.academia.restapi.ipFrauds.model.entities.Ip;
import com.ibm.academia.restapi.ipFrauds.services.IpService;

@SpringBootTest
public class IpServiceFeign {

	@Autowired
	private IpService ipService;
	
	@Test
	@DisplayName("Find Ip in blacklist")
	void findIpBlacklist() {
		
		//Given
		String ip = "5.6.7.8";
	
		//When
		Ip ipExpected = ipService.searchIpBlacklist(ip, "true");
		
		//Then
		assertThat(ipExpected.getIp()).isEqualTo(ip);
	}
	
}
