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
    private String title;
    private String createDate;
    private String deleteDate;
    private String username;
    private String text;
    private int count;
    private List<PageCommentDto> comments;
    public ResponsePetitionDto(Petition petition) {
        this.status = petition.getStatus().toString();
        this.title = petition.getTitle();
        this.createDate = petition.ConvertDate(petition.getCreateDate());
        this.deleteDate = petition.ConvertDate(petition.getCreateDate().plusDays(14));
        this.username = petition.getUser().getName(); //익명인 경우 익명 네이밍..
        this.text = petition.getText();
        this.count = petition.getComments().size();
        this.comments = petition.getComments().stream().map(PageCommentDto::new).collect(Collectors.toList());
    }
}
