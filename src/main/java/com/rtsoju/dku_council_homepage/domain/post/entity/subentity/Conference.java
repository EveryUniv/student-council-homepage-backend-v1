package com.rtsoju.dku_council_homepage.domain.post.entity.subentity;

import com.rtsoju.dku_council_homepage.domain.post.entity.Post;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("C")
public class Conference extends Post {

    @Column
    private Integer round;

    @Column
    private LocalDateTime date;
}
