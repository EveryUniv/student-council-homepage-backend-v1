package com.rtsoju.dku_council_homepage.domain.post.entity.dto.request;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.News;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class RequestNewsDto extends RequestPostDto{
    public News toNewsEntity() {
        return new News(super.getTitle(), super.getText());
    }
}
