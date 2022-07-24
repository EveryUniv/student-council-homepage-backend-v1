package com.rtsoju.dku_council_homepage.domain.post.entity.dto.request;

import com.rtsoju.dku_council_homepage.domain.post.entity.PostFile;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class RequestAnnounceDto {
    @NotBlank(message = "title(제목)은 비어있으면 안됩니다.")
    private String title;
    @NotBlank(message = "text(본문)이 비어있으면 안됩니다.")
    private String text;

    private List<MultipartFile> files = new ArrayList<>();

}
