package com.rtsoju.dku_council_homepage.domain.post.entity.dto.page;

import com.rtsoju.dku_council_homepage.domain.base.PetitionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagePetitionDto {
    private Long id;
    private PetitionStatus petitionStatus;
    private String title;
    private Long commentCount;
    private int postHits;
    private boolean isBlind;

}
