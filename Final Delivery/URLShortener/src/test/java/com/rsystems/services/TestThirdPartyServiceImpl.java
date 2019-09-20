package com.rsystems.services;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.rsystems.exceptions.UnAuthorizedException;
import com.rsystems.services.ThirdPartyServiceImpl;

public class TestThirdPartyServiceImpl {

	@InjectMocks
	@Spy
	ThirdPartyServiceImpl thirdPartyServiceImpl ;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	  @Test(expected = UnAuthorizedException.class)
	    public void validateTokenTest() {		
		String userToken="12345";
		thirdPartyServiceImpl.validateToken(userToken);
		 }
	  @Test
	    public void validateTokenTest1() {		
		String userToken="1234";
		boolean c = thirdPartyServiceImpl.validateToken(userToken);
		assertTrue(c);
		 }
}
