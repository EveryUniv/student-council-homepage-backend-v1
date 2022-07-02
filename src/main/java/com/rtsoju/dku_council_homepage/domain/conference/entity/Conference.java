package com.rtsoju.dku_council_homepage.domain.conference.entity;

import com.rtsoju.dku_council_homepage.domain.base.Post;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorColumn(name = "C")
public class Conference extends Post {

    @Column
    private Integer round;

    @Column
    private LocalDateTime date;
}
