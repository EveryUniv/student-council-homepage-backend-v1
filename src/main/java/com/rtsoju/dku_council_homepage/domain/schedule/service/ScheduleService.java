package com.rtsoju.dku_council_homepage.domain.schedule.service;

import com.rtsoju.dku_council_homepage.domain.schedule.entity.Schedule;
import com.rtsoju.dku_council_homepage.domain.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public List<Schedule> getSchedules(LocalDate from, LocalDate to) {
        return scheduleRepository.findAllByOverlapped(from, to);
    }
}
