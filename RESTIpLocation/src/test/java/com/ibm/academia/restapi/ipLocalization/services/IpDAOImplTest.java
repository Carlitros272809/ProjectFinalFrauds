package com.ibm.academia.restapi.ipLocalization.services;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.ipLocation.repositories.IpRepository;
import com.ibm.academia.restapi.ipLocation.services.IpDAO;

@SpringBootTest
public class IpDAOImplTest {
	
	@MockBean
	private IpRepository ipRepository;
	
	@Autowired
	private IpDAO ipDao;
	
	@Test
	void findIpByIpBlacklist() {
	
		//When
		ipDao.findIpByIp(anyString());
		
		//Then
		verify(ipRepository).findIpByIp(anyString());
		
	}
	
}
