package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.remoteservice.response.dto.WeatherResponseDTO;
import com.springboot.service.WeatherCacheManager;

@RestController
public class WeatherController {
	@Autowired
	private WeatherCacheManager weatherCacheManager;

	@GetMapping(value = "/api/weather/city")
	public ResponseEntity<WeatherResponseDTO> fetchWeatherDetailsViaCity(
			@RequestParam(name = "cityCode") String cityCode, @RequestParam(name = "countryCode") String countryCode) {

		WeatherResponseDTO weatherResponseDTO = weatherCacheManager.fetchWeatherDetails(cityCode, countryCode);

		return new ResponseEntity<>(weatherResponseDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/api/weather/lat")
	public ResponseEntity<WeatherResponseDTO> fetchWeatherDetailsViaLat(@RequestParam(name = "lat") String lat,@RequestParam(name = "lon") String lon) {

		WeatherResponseDTO weatherResponseDTO = weatherCacheManager.fetchWeatherDetailsByLat(lat, lon);

		return new ResponseEntity<>(weatherResponseDTO, HttpStatus.OK);
	}
}
