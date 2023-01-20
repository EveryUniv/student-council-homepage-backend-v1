package com.rtsoju.dku_council_homepage.common.cache;

import com.rtsoju.dku_council_homepage.exception.FindUserWithIdNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CacheServiceTest {
    @Autowired private CacheService cacheService;

    @Test
    public void test(){

        //Cache 생성
        CacheData cacheData = cacheService.createCacheData("1234");
        assertEquals(cacheData, cacheService.getCacheData("1234"));

        //Cache 업데이트
        CacheData cacheData1 = cacheService.createCacheData("1234");
        assertNotEquals(cacheData, cacheData1);

        //Cache 삭제
        cacheService.expireAllCacheData();

        //없는데 조회
        assertThrows(FindUserWithIdNotFoundException.class, () -> cacheService.getCacheData("1234"));

    }
}