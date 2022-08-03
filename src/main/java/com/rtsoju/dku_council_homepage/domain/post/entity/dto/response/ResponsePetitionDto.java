package com.rtsoju.dku_council_homepage.domain.post.entity.dto.response;

import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageCommentDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PageNewsDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Data
public class ResponsePetitionDto {
    private String status;
    private String category;
    private String title;
    private String createDate;
    private String deleteDate;
    private String text;
    private int commentCount;
    private List<PageCommentDto> comments;

    private String adminComment;
    private int postHits;
    private boolean isBlind;
    public ResponsePetitionDto(Petition petition) {
        this.status = petition.getStatus().toString();
        this.category = petition.getCategory();
        this.title = petition.getTitle();
        this.createDate = petition.convertDate(petition.getCreateDate());
        this.deleteDate = petition.convertDate(petition.getCreateDate().plusDays(14));
        this.text = petition.getText();
        this.commentCount = petition.getComments().size();
        this.comments = petition.getComments().stream().map(PageCommentDto::new).collect(Collectors.toList());
        this.adminComment = petition.getAdminComment();
        this.postHits = petition.getHitCount();
        this.isBlind = petition.isBlind();
    }
}
