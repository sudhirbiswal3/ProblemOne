package com.springboot;

import java.util.Arrays;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@EnableCaching

/**
 * 
 * @author SudhirPc
 *
 */
public class CacheConfig extends CachingConfigurerSupport {

	private SimpleCacheManager cacheManager = null;
	
	/**
	 * Cache configuartion
	 */
	@Bean
	public CacheManager cacheManager() {
		cacheManager = new SimpleCacheManager();
		Cache weatherCache = new ConcurrentMapCache("weatherCache", false);
		cacheManager.setCaches(Arrays.asList(weatherCache));	
		return cacheManager;
	}


	@Bean("customKeyGenerator")
	public KeyGenerator keyGenerator() {
		return new CustomKeyGenerator();
	}
	
	/**
	 * Clear cache in every 2 hours via running scheduler
	 */
	//@Scheduled(fixedRate = 7200000L)
	@Scheduled(fixedRate = 1000*60*60*2L)
	public void evictCache() {
		clearCache();
	}
	/**
	 * Cache eviction 
	 */
	@CacheEvict(keyGenerator = "customKeyGenerator", allEntries = true, cacheNames = { "weatherCache" })
	public void clearCache() {
		cacheManager.getCacheNames().parallelStream().forEach(name -> cacheManager.getCache(name).clear());
		System.out.println("Clearing groups caches");
	}
}
