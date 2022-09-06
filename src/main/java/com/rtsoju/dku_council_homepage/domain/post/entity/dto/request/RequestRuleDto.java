package com.rtsoju.dku_council_homepage.domain.post.entity.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class RequestRuleDto extends RequestPostDto{
    @Override
    @NotEmpty(message = "file(첨부파일)이 비어있으면 안됩니다.")
    public List<MultipartFile> getFiles() {
        return super.getFiles();
    }
}
