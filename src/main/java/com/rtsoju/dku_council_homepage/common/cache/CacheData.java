package com.rtsoju.dku_council_homepage.common.cache;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CacheData {
    private String emailCode;
    private LocalDateTime expirationDate;
}
