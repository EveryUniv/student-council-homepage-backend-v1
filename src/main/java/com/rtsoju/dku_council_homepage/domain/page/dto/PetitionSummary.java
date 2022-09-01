package com.rtsoju.dku_council_homepage.domain.page.dto;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import lombok.Data;
import net.bytebuddy.asm.Advice;

import java.time.LocalDateTime;
import java.time.Period;

@Data
public class PetitionSummary {
    private Long id;
    private String title;
    private String petitionStatus;
    private int D_day;

    public PetitionSummary(Petition petition){
        this.id = petition.getId();
        this.title = petition.getTitle();
        this.petitionStatus = petition.getStatus().toString();
        Period period = Period.between(petition.getCreateDate().plusDays(15).toLocalDate(), LocalDateTime.now().toLocalDate());
        this.D_day = period.getDays();
    }
}
