package com.rsystems.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rsystems.dtos.CreateLinkDTO;
import com.rsystems.dtos.LinkDTO;
import com.rsystems.entities.Url;
import com.rsystems.exceptions.UrlFoundException;
import com.rsystems.helpers.UrlShortnerHelper;
import com.rsystems.repositories.UrlRepository;
import com.rsystems.utils.Constants;

@Service
public class UrlServiceImpl implements UrlService {

	Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);

	@Autowired
	private UrlRepository repository;

	@Value("URL_EXIST")
	String urlexist;

	@Override
	public Url find(String urlCode) {

		logger.info(Constants.FINDING_URL_BY_CODE, urlCode);
		Optional<Url> optional = repository.findById(urlCode);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	/**
	 * 
	 * @param urlDto
	 * @return
	 */
	@Override
	public LinkDTO createShortURL(CreateLinkDTO urlDto) {
		Url url = fromDTO(urlDto);
		String longUrl = url.getLongUrl();
		logger.info(Constants.FINDING_OR_CREATING_URL, longUrl);
		int startIndex = 0;
		int endIndex = startIndex + Constants.URL_CODE_SIZE - 1;
		longUrl = longUrl.replaceAll(Constants.PATTERN_BREAKING_CHARACTERS, "_").concat(urlDto.getCustomerId());
		logger.info(Constants.RECURSIVE_INSERT, longUrl);
		String code = UrlShortnerHelper.generateShortURL(longUrl, startIndex, endIndex);
		Url recievedUrl = find(code);
		if (recievedUrl != null) {
			throw new UrlFoundException(urlexist);
		}
		url.setCode(code);
		url = repository.save(url);

		return toLinkDTO(url);

	}

	@Override
	public Url fromDTO(CreateLinkDTO urlDto) {
		Url url = new Url();
		url.setLongUrl(urlDto.getUrl());
		url.setCustomerId(urlDto.getCustomerId());
		return url;

	}

	@Override
	public LinkDTO toLinkDTO(Url url) {
		LinkDTO linkDTO = new LinkDTO();
		linkDTO.setDateCreated(url.getCreatedAt().getTime());
		linkDTO.setShortURL(url.getCode());
		linkDTO.setUrl(url.getLongUrl());
		return linkDTO;

	}
}
