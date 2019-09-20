package com.rsystems.services;

import org.springframework.stereotype.Service;

import com.rsystems.exceptions.UnAuthorizedException;

@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {

	@Override
	public boolean validateToken(String userToken) {

		if (userToken.equals("1234")) {
			return true;
		} else
			throw new UnAuthorizedException("UnAuthorized User");
	}
}
