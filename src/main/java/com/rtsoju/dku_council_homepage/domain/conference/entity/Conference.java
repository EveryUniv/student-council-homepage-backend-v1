package com.rtsoju.dku_council_homepage.domain.conference.entity;

import com.rtsoju.dku_council_homepage.domain.post.entity.POST;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorColumn("C")
public class Conference extends POST {

    @Column
    private Integer round;

    @Column
    private LocalDateTime date;
}
