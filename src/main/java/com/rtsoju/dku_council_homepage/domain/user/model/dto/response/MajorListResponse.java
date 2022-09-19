package com.rtsoju.dku_council_homepage.domain.user.model.dto.response;

import com.rtsoju.dku_council_homepage.domain.base.Major;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MajorListResponse {
    private List<MajorDto> majors;

    public MajorListResponse() {
        majors = Arrays.stream(Major.values())
                .map(MajorDto::fromMajor)
                .collect(Collectors.toList());
    }

    public static class MajorDto {
        public String key;
        public String name;

        private static MajorDto fromMajor(Major major) {
            MajorDto obj = new MajorDto();
            obj.key = major.name();
            obj.name = major.getName();
            return obj;
        }
    }
}
