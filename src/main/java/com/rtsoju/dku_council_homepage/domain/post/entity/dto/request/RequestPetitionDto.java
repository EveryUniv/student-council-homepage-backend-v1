package com.rtsoju.dku_council_homepage.domain.post.entity.dto.request;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class RequestPetitionDto extends RequestPostDto{
    @NotBlank(message = "category(카테고리)를 지정해주세요")
    private String category;
}
