package com.rtsoju.dku_council_homepage.domain.event.service;

import com.rtsoju.dku_council_homepage.common.nhn.service.ObjectStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EventService {
    private static final String EVENT_IMAGE_NAME = "event-image.jpeg";
    private final ObjectStorageService service;

    public String getEventImageUrl() {
        return service.getObjectURL(EVENT_IMAGE_NAME);
    }
}

