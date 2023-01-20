package com.rtsoju.dku_council_homepage.common.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@EnableCaching
@Configuration
public class LocalCacheConfig {

    @Bean
    public CacheManager cacheManager(){
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        //추후 필요한 경우 여러개의 Cache를 만들 수 있음.
        simpleCacheManager.setCaches(List.of(new ConcurrentMapCache("emailCode", new ConcurrentHashMap<>(10), false)));
        return simpleCacheManager;
    }
}
