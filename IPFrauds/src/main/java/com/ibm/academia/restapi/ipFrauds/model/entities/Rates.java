package com.ibm.academia.restapi.ipFrauds.model.entities;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Rates implements Serializable{

	private Object rates;
	
	public Rates (Rates rates) {
		this.rates = rates.getRates();
	}

	private static final long serialVersionUID = -12015859717207920L;
}