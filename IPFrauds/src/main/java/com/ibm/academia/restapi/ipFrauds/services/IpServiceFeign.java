package com.ibm.academia.restapi.ipFrauds.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.restapi.ipFrauds.clients.IpClientRest;
import com.ibm.academia.restapi.ipFrauds.model.entities.Ip;

@Service
public class IpServiceFeign implements IpService{
	
	@Autowired
	private IpClientRest clientFeign;

	@Override
	public  Ip searchIpBlacklist(String ip, String blacklist) {
		return new Ip(clientFeign.searchIpBlacklist(ip, blacklist).getIp());
	}

}
