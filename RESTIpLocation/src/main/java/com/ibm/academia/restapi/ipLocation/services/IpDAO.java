package com.ibm.academia.restapi.ipLocation.services;

import com.ibm.academia.restapi.ipLocation.model.entities.Ip;

public interface IpDAO {
	public Iterable<Ip> findIpByIp(String ip);
	public Ip saveIpBlacklist(Ip ip);
}
