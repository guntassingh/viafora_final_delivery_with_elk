package com.rsystems.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.rsystems.dtos.CreateLinkDTO;
import com.rsystems.dtos.LinkDTO;
import com.rsystems.entities.Url;
import com.rsystems.exceptions.UrlFoundException;
import com.rsystems.exceptions.UrlShortnerHelperException;
import com.rsystems.repositories.UrlRepository;
import com.rsystems.services.UrlServiceImpl;

import junit.framework.Assert;

public class TestUrlServiceImpl {

	@InjectMocks
	@Spy
	UrlServiceImpl urlService;

	@Mock
	UrlRepository repository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	Url url = new Url("MmM3MT", "http://docker.com", "12345");
	Optional<Url> url1;
	String id = "MDVmNm";
	Optional<Url> returnCacheValue = Optional.of((Url) url);

	@Test
	public void findTest() {

		Mockito.<Optional<Url>>when(repository.findById(id)).thenReturn(returnCacheValue);
		assertEquals(returnCacheValue.get().getCustomerId(), url.getCustomerId());

	}

	@Test
	public void createShortURLTest() {

	/*	CreateLinkDTO urlDto = new CreateLinkDTO();
		urlDto.setCustomerId("12345");
		urlDto.setUrl("http://yahoo.com");
		LinkDTO linkDTO = new LinkDTO();*/

		Mockito.when(repository.save(url)).thenReturn(url);
		//assertEquals(url, url);
		assertNotNull(url);

	}
	
	@Test
	public void whenCodeExistsReturnsUrl() {
		// Given
		String existingCode = "3077yW";

		Url existingUrl = new Url(existingCode, "http://www.docker.com", "12345");
		Optional<Url> optional = Optional.of(existingUrl);
		Mockito.when(repository.findById(existingCode)).thenReturn(optional);

		// When
		Url url = urlService.find(existingCode);

		// Then
		Assert.assertEquals(existingUrl, url);
	}
	
	@Test
	public void whenCodenotExistReturnNull() {
		// Given
		String existingCode = "3077yW1";

		Url existingUrl = new Url("3077yW", "http://www.docker.com", "12345");
		Optional<Url> optional = Optional.empty();
		Mockito.when(repository.findById(existingCode)).thenReturn(optional);

		// When
		Url url = urlService.find(existingCode);

		// Then
		Assert.assertNull(url);
	}
//=================================
	  @Test 
	  public void whenLongUrlDoesNotExistSaveItAndReturnNewUrlCode() { 
			String existingCode = "MmM3MT";
			Date d = new Date();
			Url existingUrl = new Url("MmM3MT", "http://www.docker.com", "12345");
			  CreateLinkDTO createLinkDTO=new CreateLinkDTO();
			  createLinkDTO.setCustomerId("12345");
			  createLinkDTO.setUrl("http://www.docker.com");
			  existingUrl.setCreatedAt(new Date());
			Optional<Url> optional = Optional.of(existingUrl);
			Mockito.when(repository.findById(existingCode)).thenReturn(optional);
			Mockito.when(urlService.fromDTO(createLinkDTO)).thenReturn(existingUrl);
			Mockito.when(repository.save(existingUrl)).thenReturn(existingUrl);
	  

	 
	 LinkDTO linkDTO=urlService.createShortURL(createLinkDTO);
	  
	  Assert.assertEquals(linkDTO.getShortURL(), "NDRmNz");
	 
	 }
	
	  @Test(expected = UrlFoundException.class)
	    public void testGetExistingUrl_failure() {		String existingCode = "NDRmNz";
		Date d = new Date();
		Url existingUrl = new Url(existingCode, "http://www.docker.com", "12345");
		  CreateLinkDTO createLinkDTO=new CreateLinkDTO();
		  createLinkDTO.setCustomerId("12345");
		  createLinkDTO.setUrl("http://www.docker.com");
		Optional<Url> optional = Optional.of(existingUrl);
		existingUrl.setCreatedAt(new Date());
		Mockito.when(repository.findById(existingCode)).thenReturn(optional);
		Mockito.when(urlService.fromDTO(createLinkDTO)).thenReturn(existingUrl);
		Mockito.when(repository.save(existingUrl)).thenReturn(existingUrl);
		urlService.createShortURL(createLinkDTO);
		
	  }
	  
	  @Test(expected = Exception.class)
	    public void testGetExistingUrl_failure2() throws NoSuchAlgorithmException {
		  
			String existingCode = "MmM3MT";
			Date d = new Date();
			Url existingUrl = new Url(existingCode, "http://www.docker.com", "12345");
			  CreateLinkDTO createLinkDTO=new CreateLinkDTO();
			  createLinkDTO.setCustomerId("12345");
			  createLinkDTO.setUrl("http://www.docker.com");
			Optional<Url> optional = Optional.of(existingUrl);
			existingUrl.setCreatedAt(new Date());
			Mockito.when(repository.findById(existingCode)).thenReturn(optional);
			Mockito.when(urlService.fromDTO(createLinkDTO)).thenReturn(existingUrl);
			Mockito.when(repository.save(existingUrl)).thenReturn(existingUrl);
			String longUrl = "http://www.docker.com12345";
			Mockito.when(MessageDigest.getInstance("XYZ").digest(longUrl.getBytes())).thenThrow(new NoSuchAlgorithmException());  
			Mockito.doThrow(new NoSuchAlgorithmException()).when(MessageDigest.getInstance("MD5").digest(longUrl.getBytes()));
			urlService.createShortURL(createLinkDTO);
	  }
	  
}
