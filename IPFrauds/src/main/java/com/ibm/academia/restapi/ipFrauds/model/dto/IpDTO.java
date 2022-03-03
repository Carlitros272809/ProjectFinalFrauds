package com.ibm.academia.restapi.ipFrauds.model.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IpDTO implements Serializable{
	
	private String ip;
	private String nameCountry;
	private String isoCodeCountry;
	private String currency;
	private String dollars;
	private String euros;
	
	

	public IpDTO(String ip, String nameCountry, String isoCodeCountry, String currency, String dollars, String euros) {
		this.ip = ip;
		this.nameCountry = nameCountry;
		this.isoCodeCountry = isoCodeCountry;
		this.currency = currency;
		this.dollars = dollars;
		this.euros = euros;
	}



	private static final long serialVersionUID = 1331062779707177788L;
}
