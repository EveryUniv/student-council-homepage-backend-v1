package com.rtsoju.dku_council_homepage.domain.post.entity.subentity;

import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("C")
@Getter
public class Conference extends Post {

    public Conference(String title, String text) {
        super(title, text);
    }


    @Column
    private Integer round;

    @Column
    private LocalDate date;

    public Conference(String title, Integer round, LocalDate date) {
        super(title);
        this.round = round;
        this.date = date;
    }
}
