package br.com.jdrmservices.util;

import java.math.BigInteger;
import java.util.UUID;

public class GerarNumeroOrdem {
		
	public static String getNumeroOrdem() {		
		String lUUID = String.format("%s", new BigInteger(UUID.randomUUID()
				.toString().replace("-", ""), 16).toString().substring(0, 14));
		
		return lUUID;
	}
}
