package com.rsystems.services;

public interface ThirdPartyService {
	
	 /**
     * Validate token 
     * @param user token 
     * @return boolean
     */
	public boolean validateToken(String userToken);
}
