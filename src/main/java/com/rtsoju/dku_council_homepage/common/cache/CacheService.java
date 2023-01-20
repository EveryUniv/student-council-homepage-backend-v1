package com.rtsoju.dku_council_homepage.common.cache;

import com.rtsoju.dku_council_homepage.exception.FindUserWithIdNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class CacheService {

    @CachePut(cacheNames = "emailCode", key="#studentId")
    public CacheData createCacheData(final String studentId){
        CacheData cacheData = new CacheData();
        cacheData.setEmailCode(UUID.randomUUID().toString().substring(0,5));
        cacheData.setExpirationDate(LocalDateTime.now().plusMinutes(5));
        return cacheData;
    }

    @Cacheable(cacheNames = "emailCode", key="#studentId")
    public CacheData getCacheData(final String studentId){
        //하위 로직이 실행되면 Cache에 데이터가 없다는 것.
        //보통은 생성해서 캐싱해두지만, 우리는 확인을 해야하기에,,,
        throw new FindUserWithIdNotFoundException();
    }

    @CacheEvict(value = "emailCode", allEntries = true, cacheManager = "cacheManager")
    public void expireAllCacheData(){}


    public boolean isExpired(final CacheData cacheData){
        return !cacheData.getExpirationDate().isAfter(LocalDateTime.now());
    }
}
