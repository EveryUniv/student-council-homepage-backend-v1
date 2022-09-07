package com.rtsoju.dku_council_homepage.domain.post.entity.dto.request;

import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Conference;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class RequestConferenceDto {

    @NotBlank(message = "title(제목)은 비어있으면 안됩니다.")
    private String title;

    @NotBlank(message = "date(개최 일자)은 비어있으면 안됩니다.")
    private String date;

    @NotNull(message = "round(회차)은 비어있으면 안됩니다.")
    private Integer round;

    @NotEmpty(message = "files(첨부 파일)은 비어있으면 안됩니다.")
    private List<MultipartFile> files = new ArrayList<>();

    public Conference toEntity() {
        LocalDate parseDate = LocalDate.parse(date);
        return new Conference(title, round, parseDate);
    }
}
