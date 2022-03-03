package com.ibm.academia.restapi.ipLocation.services;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.restapi.ipLocation.model.entities.Ip;
import com.ibm.academia.restapi.ipLocation.repositories.IpRepository;

@Service
public class IpDAOImpl implements IpDAO {
	
	@Autowired
	private IpRepository ipRepository;

	@Override
	@Transactional(readOnly=true)
	public Iterable<Ip> findIpByIp(String ip) {
		return ipRepository.findIpByIp(ip);
	}

	@Override
	@Transactional
	public Ip saveIpBlacklist(Ip ip) {
		return ipRepository.save(ip);
	}
}
