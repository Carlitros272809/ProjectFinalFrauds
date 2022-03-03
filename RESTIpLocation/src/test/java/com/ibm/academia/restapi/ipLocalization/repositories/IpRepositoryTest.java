package com.ibm.academia.restapi.ipLocalization.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.ipLocalization.data.DummyData;
import com.ibm.academia.restapi.ipLocation.model.entities.Ip;
import com.ibm.academia.restapi.ipLocation.repositories.IpRepository;

@DataJpaTest
public class IpRepositoryTest {
	
	@Autowired
	@Qualifier("repositoryIp")
	private IpRepository ipRepository;

	@BeforeEach
	void setUp() {
		//Given 
		Ip ip01 = ipRepository.save(DummyData.ip01());
		Ip ip02 = ipRepository.save(DummyData.ip02());
	}
	
	@AfterEach
	void tearDown() {
		ipRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Test: find ip by ip")
	void findIpByIpInBlacklist() {
		
		//When
		String ipTest = "127.0.0.1";
		List<Ip> expected = (List<Ip>)((IpRepository)ipRepository).findIpByIp(ipTest);
		int expected2 = 1;
		
		//Then
		assertThat(expected2==1).isTrue();
		
	}
	
}
