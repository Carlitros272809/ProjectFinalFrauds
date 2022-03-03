package com.ibm.academia.restapi.ipLocalization.data;

import com.ibm.academia.restapi.ipLocation.model.entities.Ip;

public class DummyData {

	public static Ip ip01() {
		return new Ip("127.0.0.1");
	}
	
	public static Ip ip02 () {
		return new Ip("127.0.0.2");
	}
	
}
