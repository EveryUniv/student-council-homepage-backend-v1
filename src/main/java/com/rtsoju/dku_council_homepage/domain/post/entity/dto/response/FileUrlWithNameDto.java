package com.rtsoju.dku_council_homepage.domain.post.entity.dto.response;

import com.rtsoju.dku_council_homepage.domain.post.entity.PostFile;
import lombok.Data;

@Data
public class FileUrlWithNameDto {
    private String originName;
    private String url;

    public FileUrlWithNameDto(PostFile postFile) {
        final String s3Domain = "https://api-storage.cloud.toast.com/v1/";
        final String storageAccount = "AUTH_34f4838a2b3047f39ac9cb0701558e46";
        final String storageName = "main-storage";
        final String prefix = s3Domain + storageAccount + "/" + storageName + "/";

        this.url = prefix + postFile.getUrl();
        this.originName = postFile.getName();
    }
}
