package com.ibm.academia.restapi.ipLocation.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.ipLocation.model.entities.Ip;

@Repository("repositoryIp")
public interface IpRepository extends CrudRepository<Ip, Long>{

	public Iterable<Ip> findIpByIp(String ip);

}
