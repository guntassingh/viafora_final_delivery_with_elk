package com.rsystems.controllers;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

import com.rsystems.dtos.CreateLinkDTO;
import com.rsystems.dtos.LinkDTO;
import com.rsystems.dtos.StatisticsSummaryDTO;
import com.rsystems.entities.Statistic;
import com.rsystems.entities.Url;
import com.rsystems.services.StatisticService;
import com.rsystems.services.ThirdPartyService;
import com.rsystems.services.UrlService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StatisticsController.class, secure = false)
public class TestStatisticsController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StatisticService statisticService;

	StatisticsSummaryDTO statisticsSummaryDTO = new StatisticsSummaryDTO();

	@Test
	public void getSummaryTest() throws Exception {
		Mockito.when(statisticService.getStatisticsSummary()).thenReturn(statisticsSummaryDTO);
		// Mockito.when(statisticService.mapFrom(new HashMap<String, String>(),
		// url)).thenReturn(Statistic);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/statistics/summary").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

	}

	@Test
	public void getSummaryByCodeTest() throws Exception {
		Mockito.when(statisticService.getStatisticsSummaryByCode("MmM3MT")).thenReturn(statisticsSummaryDTO);
		// Mockito.when(statisticService.mapFrom(new HashMap<String, String>(),
		// url)).thenReturn(Statistic);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/statistics/summary/MmM3MT")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

	}

}
