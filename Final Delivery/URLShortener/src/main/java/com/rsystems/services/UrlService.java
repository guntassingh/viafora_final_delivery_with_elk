package com.rsystems.services;

import com.rsystems.dtos.CreateLinkDTO;
import com.rsystems.dtos.LinkDTO;
import com.rsystems.entities.Url;

public interface UrlService {
	/**
	 * Fetch find
	 * 
	 * @param browser, deviceType, operatingSystem and createdAt for which Statistic
	 *                 detail are fetched
	 * @return Statistic
	 */
	public Url find(String urlCode);

	/**
	 * Create createShortURL
	 * 
	 * @param customerId, url of CreateLinkDTO
	 * @return LinkDTO
	 */
	public LinkDTO createShortURL(CreateLinkDTO urlDto);

	/**
	 * Fetch fromDTO
	 * 
	 * @param customerId, url of CreateLinkDTO
	 * @return Url
	 */

	public Url fromDTO(CreateLinkDTO urlDto);

	/**
	 * Fetch toLinkDTO
	 * 
	 * @param code, longUrl,customerId,createdAt and updatedAt of Url
	 * @return LinkDTO
	 */
	public LinkDTO toLinkDTO(Url url);
}
