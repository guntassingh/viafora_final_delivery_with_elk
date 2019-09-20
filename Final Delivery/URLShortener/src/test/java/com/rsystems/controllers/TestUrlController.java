package com.rsystems.controllers;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.common.net.HttpHeaders;
import com.rsystems.dtos.CreateLinkDTO;
import com.rsystems.dtos.LinkDTO;
import com.rsystems.entities.Statistic;
import com.rsystems.entities.Url;
import com.rsystems.services.StatisticService;
import com.rsystems.services.ThirdPartyService;
import com.rsystems.services.UrlService;
import com.rsystems.services.UrlServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UrlController.class, secure = false)
public class TestUrlController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UrlService urlService;

	@MockBean
	private ThirdPartyService thirdPartyServices;

	@MockBean
	private StatisticService statisticService;

	Url url = new Url("MmM3MT", "http://docker.com", "12345");
	Statistic Statistic = new Statistic("crome", "computer", "window 10", url);
	String urlJson ="{ \"customerId\": \"12345\", \"url\" : \"http://yahoo.com\"}";

	@Test
	public void findAndRedirect() throws Exception {
		Mockito.when(urlService.find("MmM3MT")).thenReturn(url);
		Mockito.when(statisticService.mapFrom(new HashMap<String, String>(), url)).thenReturn(Statistic);
		System.out.println("Long url " + url.getLongUrl());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/MmM3MT").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
	//	String expected = "{'code':'MmM3MT','longUrl:http://docker.com','customerId:12345'}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K
		// Students","steps":["Learn Maven","Import Project","First Example","Second
		// Example"]}

	//	JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
		assertEquals(HttpStatus.MOVED_PERMANENTLY.value(), result.getResponse().getStatus());

		

	}

	@Test
	public void createShortURL() throws Exception {
		CreateLinkDTO urlDto = new CreateLinkDTO();
		urlDto.setCustomerId("12345");
		urlDto.setUrl("http://yahoo.com");
		LinkDTO linkDTO = new LinkDTO();

		// studentService.addCourse to respond back with mockCourse
		Mockito.when(urlService.createShortURL(urlDto)).thenReturn(linkDTO);

		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/s/url")
				.accept(MediaType.APPLICATION_JSON).content(urlJson).header("token","").contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

		//assertEquals("http://localhost:9091/s/url", response.getHeader(HttpHeaders.LOCATION));

	}

	

}
