package com.ibm.academia.restapi.ipFrauds.model.entities;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Currency implements Serializable{

	private String code;
	private String name;
	private String symbol;
	
	private static final long serialVersionUID = 7242532631763776495L;

}