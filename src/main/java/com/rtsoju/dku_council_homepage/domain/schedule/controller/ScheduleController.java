package com.rtsoju.dku_council_homepage.domain.schedule.controller;

import com.rtsoju.dku_council_homepage.common.ResponseResult;
import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.domain.schedule.dto.ScheduleResponse;
import com.rtsoju.dku_council_homepage.domain.schedule.entity.Schedule;
import com.rtsoju.dku_council_homepage.domain.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<ResponseResult> list(
            @RequestParam("from") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate from,
            @RequestParam("to") @DateTimeFormat(pattern = "yyyyMMdd") LocalDate to) {
        List<Schedule> schedules = scheduleService.getSchedules(from, to);
        List<ScheduleResponse> result = schedules.stream()
                .map((s) -> new ScheduleResponse(s.getTitle(), s.getStartDateTime(), s.getEndDateTime()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new SuccessResponseResult(result));
    }
}
