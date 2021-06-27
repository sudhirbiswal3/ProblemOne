package com.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author SudhirPc
 *
 */
@SpringBootApplication
public class ApiProgrammingTipsApplication {

	//curl --location --request GET 'http://localhost:9001/api/weather/lat?lat=34.9667&lon=138.9333'
	//curl --location --request GET 'http://localhost:9001/api/weather/city?cityCode=Shuzenji&countryCode=jpn'
	private static final int WEBSERVICE_CALL_TIMEOUT = 10000;

	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiProgrammingTipsApplication.class, args);
	}
	
	/**
	 * bean initialisation for restTemplate
	 * @return RestTemplate
	 */
	@Bean(name = "restTemplate")
	public RestTemplate prepareRestTemplate() {           
        RestTemplate restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory simpleClient = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
        simpleClient.setReadTimeout(WEBSERVICE_CALL_TIMEOUT);
        simpleClient.setConnectTimeout(WEBSERVICE_CALL_TIMEOUT);       
        return restTemplate;
    }	

}
