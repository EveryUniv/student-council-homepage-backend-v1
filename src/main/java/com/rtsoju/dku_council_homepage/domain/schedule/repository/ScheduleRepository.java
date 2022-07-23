package com.rtsoju.dku_council_homepage.domain.schedule.repository;

import com.rtsoju.dku_council_homepage.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("select s from Schedule s " +
            "where s.startDateTime between :startDt and :endDt or " +
            "s.endDateTime between :startDt and :endDt")
    List<Schedule> findAllByOverlapped(
            @Param("startDt") LocalDate start,
            @Param("endDt") LocalDate end);
}
