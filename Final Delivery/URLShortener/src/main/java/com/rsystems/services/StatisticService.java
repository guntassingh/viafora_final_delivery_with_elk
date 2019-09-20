package com.rsystems.services;

import java.util.Map;

import com.rsystems.dtos.StatisticsSummaryDTO;
import com.rsystems.entities.Statistic;
import com.rsystems.entities.Url;

public interface StatisticService {
	 /**
     * Create new Statistic
     * @param browser, deviceType, operatingSystem and createdAt for which Statistic detail are fetched
     * @return Statistic
     */
	public Statistic create(Statistic statistic);
	/**
     * Fetch Statistic Detail
     * @param browser, deviceType, operatingSystem and createdAt for which Statistic detail are fetched
     * @return Statistic
     */
	public Statistic mapFrom(Map<String, String> headers, Url url);
	 /**
     * Fetch StatisticsSummaryDTO Detail 
     * @param numberOfHits,browsers,devicesTypes and operatingSystems
     * @return StatisticsSummaryDTO
     */
	public StatisticsSummaryDTO getStatisticsSummary();
	 /**
     * Fetch StatisticsSummaryDTO Detail 
     * @param numberOfHits,browsers,devicesTypes and operatingSystems base on code 
     * @return StatisticsSummaryDTO
     */
	public StatisticsSummaryDTO getStatisticsSummaryByCode(String code);
}
