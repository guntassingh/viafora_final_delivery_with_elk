package com.rsystems.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHeaders;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.rsystems.dtos.LinkDTO;
import com.rsystems.dtos.StatisticsDTO;
import com.rsystems.dtos.StatisticsSummaryDTO;
import com.rsystems.entities.Statistic;
import com.rsystems.entities.Url;
import com.rsystems.repositories.StatisticRepository;
import com.rsystems.services.StatisticServiceImpl;

import junit.framework.Assert;

public class TestStatisticServiceImpl {

	@InjectMocks
	StatisticServiceImpl  statisticService;

	@Mock
	StatisticRepository repository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	Url url = new Url("MmM3MT", "http://docker.com", "12345");

	@Test
	public void createTest() {
		Statistic statistic = new Statistic("crome", "computer", "window 10", url);
		Mockito.when(repository.save(statistic)).thenReturn(statistic);
        Statistic Stat = statisticService.create(statistic);
        Assert.assertEquals(Stat.getBrowser(), statistic.getBrowser());
		assertNotNull(statistic);
	}
	
	@Test
	public void getStatisticsSummaryTest() {
		StatisticsSummaryDTO summaryDTO = new StatisticsSummaryDTO();
		StatisticsDTO statisticsDTO=new  StatisticsDTO();
		statisticsDTO.setName("crome");
		statisticsDTO.setTotal(new Long(10));
		List<StatisticsDTO> browsers =new ArrayList<StatisticsDTO>();
		browsers.add(statisticsDTO);
		
	}
	
	@Test
	public void whenCreatingAStatisticVerifyThatRepositorySaveIsCalled() {

		// Given
		Url url = new Url("MmM3MT", "http://docker.com", "12345");
		Statistic statistic = new Statistic("Firefox 7", "Computer", "Windows XP", url);
		
		// When
		statisticService.create(statistic);

		// Then
		Mockito.verify(repository).save(statistic);
	}
	
	@Test
	public void whenMappingFromHeadersAndStatisticReturnsStatistic() {
		// Given
		Url url = new Url("MmM3MT", "http://www.docker.com","12345");
		
		Map<String, String> headers = new HashMap<>();
		headers.put(HttpHeaders.USER_AGENT.toLowerCase(), "Mozilla/5.0 (Windows NT 5.1; rv:7.0.1) Gecko/20100101 Firefox/7.0.1");
		
		//When
		Statistic statistic = statisticService.mapFrom(headers, url);
		
		//Then
		Assert.assertEquals("Firefox 7", statistic.getBrowser());
		Assert.assertEquals("Computer", statistic.getDeviceType());
		Assert.assertEquals("Windows XP", statistic.getOperatingSystem());
		Assert.assertEquals(url, statistic.getUrl());
	}
	
	@Test
	public void whenGettingStatisticsSummaryReturnsResultsFromRepository() {
		Long numberOfHits = 3L;
		
		StatisticsDTO firefox = new StatisticsDTO("Firefox", 1L);
		StatisticsDTO chrome = new StatisticsDTO("Chrome", 2L);
		List<StatisticsDTO> browsers = Arrays.asList(new StatisticsDTO[] { firefox, chrome});

		StatisticsDTO computer = new StatisticsDTO("Computer", 1L);
		StatisticsDTO mobile = new StatisticsDTO("Mobile", 1L);
		StatisticsDTO tablet = new StatisticsDTO("Tablet", 1L);
		List<StatisticsDTO> deviceTypes = Arrays.asList(new StatisticsDTO[] { computer, mobile, tablet});

		Long totalOfLinuxHits = 3L;
		StatisticsDTO linux = new StatisticsDTO("Linux", totalOfLinuxHits);
		List<StatisticsDTO> operationSystems = Arrays.asList(new StatisticsDTO[] { linux });
		
		// Given
		Mockito.when(repository.getNumberOfHits()).thenReturn(numberOfHits);
		Mockito.when(repository.getBrowsers()).thenReturn(browsers);
		Mockito.when(repository.getDevicesTypes()).thenReturn(deviceTypes);
		Mockito.when(repository.getOperatingSystems()).thenReturn(operationSystems);

		//When
		StatisticsSummaryDTO statisticsSummary = statisticService.getStatisticsSummary();
		
		//Then
		Assert.assertEquals(numberOfHits, statisticsSummary.getNumberOfHits());
		Assert.assertEquals(2, statisticsSummary.getBrowsers().size());
		Assert.assertEquals(3, statisticsSummary.getDevicesTypes().size());
		Assert.assertEquals(1, statisticsSummary.getOperatingSystems().size());
		Assert.assertEquals(totalOfLinuxHits, statisticsSummary.getOperatingSystems().get(0).getTotal());
	}
	
	@Test
	public void whenGettingStatisticsSummaryByCodeReturnsResultsFromRepository() {
		String code = "MmM3MT";
		Long numberOfHits = 3L;
		
		StatisticsDTO firefox = new StatisticsDTO("Firefox", 1L);
		StatisticsDTO chrome = new StatisticsDTO("Chrome", 2L);
		List<StatisticsDTO> browsers = Arrays.asList(new StatisticsDTO[] { firefox, chrome});

		StatisticsDTO computer = new StatisticsDTO("Computer", 1L);
		StatisticsDTO mobile = new StatisticsDTO("Mobile", 1L);
		StatisticsDTO tablet = new StatisticsDTO("Tablet", 1L);
		List<StatisticsDTO> deviceTypes = Arrays.asList(new StatisticsDTO[] { computer, mobile, tablet});

		Long totalOfLinuxHits = 2L;
		StatisticsDTO linux = new StatisticsDTO("Linux", totalOfLinuxHits);
		StatisticsDTO windows = new StatisticsDTO("Windows", 1L);
		List<StatisticsDTO> operationSystems = Arrays.asList(new StatisticsDTO[] { linux, windows });
		
		// Given
		Mockito.when(repository.getNumberOfHitsByCode(code)).thenReturn(numberOfHits);
		Mockito.when(repository.getBrowsersByCode(code)).thenReturn(browsers);
		Mockito.when(repository.getDevicesTypesByCode(code)).thenReturn(deviceTypes);
		Mockito.when(repository.getOperatingSystemsByCode(code)).thenReturn(operationSystems);

		//When
		StatisticsSummaryDTO statisticsSummary = statisticService.getStatisticsSummaryByCode(code);
		
		//Then
		Assert.assertEquals(numberOfHits, statisticsSummary.getNumberOfHits());
		Assert.assertEquals(2, statisticsSummary.getBrowsers().size());
		Assert.assertEquals(3, statisticsSummary.getDevicesTypes().size());
		Assert.assertEquals(2, statisticsSummary.getOperatingSystems().size());
		Assert.assertEquals(totalOfLinuxHits, statisticsSummary.getOperatingSystems().get(0).getTotal());
	}

}
