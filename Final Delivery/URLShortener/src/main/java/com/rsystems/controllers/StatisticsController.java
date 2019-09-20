package com.rsystems.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rsystems.dtos.StatisticsSummaryDTO;
import com.rsystems.services.StatisticService;
import com.rsystems.utils.Constants;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/statistics")
public class StatisticsController {
	Logger logger = LoggerFactory.getLogger(StatisticsController.class);

	@Autowired
	private StatisticService statisticService;

	@ApiOperation(value = "Get Summary", response = StatisticsSummaryDTO.class)
	@GetMapping(path = "/summary")
	public ResponseEntity<StatisticsSummaryDTO> getSummary() {
		logger.info(Constants.GETTING_STATISTICS_SUMMARY);

		StatisticsSummaryDTO summary = statisticService.getStatisticsSummary();

		return ResponseEntity.ok().body(summary);
	}

	@ApiOperation(value = "Get Summary by Code", response = StatisticsSummaryDTO.class)
	@GetMapping(path = "/summary/{code}")
	public ResponseEntity<StatisticsSummaryDTO> getSummaryByCode(@PathVariable String code) {

		code = code.replaceAll(Constants.PATTERN_BREAKING_CHARACTERS, "_");

		logger.info(Constants.GETTING_STATISTICS_SUMMARY_BY_CODE, code);

		StatisticsSummaryDTO summary = statisticService.getStatisticsSummaryByCode(code);

		return ResponseEntity.ok().body(summary);
	}

}