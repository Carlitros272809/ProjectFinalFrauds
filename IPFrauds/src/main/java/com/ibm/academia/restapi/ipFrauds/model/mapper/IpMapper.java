package com.ibm.academia.restapi.ipFrauds.model.mapper;

import com.ibm.academia.restapi.ipFrauds.model.dto.IpDTO;
import com.ibm.academia.restapi.ipFrauds.model.entities.Ip;

public class IpMapper {

	public static IpDTO mapIp (Ip ip) {
		
		IpDTO ipDto = new IpDTO();
		ipDto.setIp(ip.getIp());
		ipDto.setNameCountry(ip.getCountry().getCountryName());
		ipDto.setIsoCodeCountry(ip.getCountry().getCountryCode3());
		ipDto.setCurrency(ip.getCurrencyRate());
		ipDto.setDollars(ip.getUsdCurrency());
		ipDto.setEuros(ip.getEurCurrency());
		
		return ipDto;
	}
	
}
