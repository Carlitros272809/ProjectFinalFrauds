package com.ibm.academia.restapi.ipFrauds.services;

import com.ibm.academia.restapi.ipFrauds.model.entities.Ip;

public interface IpService {
	public Ip searchIpBlacklist(String ip, String blacklist);
}
