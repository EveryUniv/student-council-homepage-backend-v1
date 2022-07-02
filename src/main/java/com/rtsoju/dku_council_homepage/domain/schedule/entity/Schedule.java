package com.rtsoju.dku_council_homepage.domain.schedule.entity;

import com.rtsoju.dku_council_homepage.domain.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "schedule_id")
    private Long id;

    @Column(name = "start_date")
    private LocalDateTime startDateTime;

    @Column(name = " end_date")
    private LocalDateTime endDateTime;

    @Column
    private String title;
}
