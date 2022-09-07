package com.rtsoju.dku_council_homepage.domain.post.entity.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class RequestRuleDto {
    @NotBlank(message = "title(제목)은 비어있으면 안됩니다.")
    private String title;
    @NotBlank(message = "text(본문)이 비어있으면 안됩니다.")
    private String text;
    @NotEmpty(message = "files(첨부파일)이 비어있으면 안됩니다.")
    private List<MultipartFile> files = new ArrayList<>();

}
