package com.springboot.service;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.springboot.remoteservice.response.dto.WeatherResponseDTO;

@Service
public class WeatherCacheManager {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	CacheManager cacheManager;

	@Cacheable(value = "weatherCache", keyGenerator = "customKeyGenerator")
	public WeatherResponseDTO fetchWeatherDetails(String cityCode, String countryCode) {
		System.out.println("fetchWeatherDetails called.... ");
		MessageFormat messageFormat = new MessageFormat(
				"http://api.openweathermap.org/data/2.5/weather?q={0},{1}&appid=ffa6f13ea40a22452bba3be726315d3f");
		Object[] args2 = { cityCode, countryCode };
		String requestUrl = messageFormat.format(args2);
		ResponseEntity<WeatherResponseDTO> result = null;
		try {
			result = restTemplate.exchange(requestUrl, HttpMethod.GET, null, WeatherResponseDTO.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		return result != null && result.getBody() != null ? result.getBody() : new WeatherResponseDTO();
	}

	@Cacheable(value = "weatherCache", keyGenerator = "customKeyGenerator")
	public WeatherResponseDTO fetchWeatherDetailsByLat(String lat, String lon) {
		System.out.println("fetchWeatherDetailsByLat called.... ");
		MessageFormat messageFormat = new MessageFormat(
				"http://api.openweathermap.org/data/2.5/weather?lat={0}&lon={0}&appid=ffa6f13ea40a22452bba3be726315d3f");
		Object[] args2 = { lat, lon };
		String requestUrl = messageFormat.format(args2);
		ResponseEntity<WeatherResponseDTO> result = null;
		try {
			result = restTemplate.exchange(requestUrl, HttpMethod.GET, null, WeatherResponseDTO.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return result != null && result.getBody() != null ? result.getBody() : new WeatherResponseDTO();
	}

}
