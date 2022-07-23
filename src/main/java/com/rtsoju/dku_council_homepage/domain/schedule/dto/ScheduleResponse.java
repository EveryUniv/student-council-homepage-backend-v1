package com.rtsoju.dku_council_homepage.domain.schedule.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ScheduleResponse {
    private final String title;
    private final LocalDate start;
    private final LocalDate end;
}
