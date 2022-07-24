package com.rtsoju.dku_council_homepage.domain.post.entity.dto.page;

import com.rtsoju.dku_council_homepage.domain.post.entity.PostFile;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class PageAnnounceDto {

    private Long id;
    private String title;
    private String createDate;

    private List<String> fileList = new ArrayList<>();
    public PageAnnounceDto(Announce announce){
        final String s3Domain = "https://api-storage.cloud.toast.com/v1/";
        final String storageAccount = "AUTH_34f4838a2b3047f39ac9cb0701558e46";
        final String storageName = "main-storage";
        final String url = s3Domain+storageAccount+"/"+storageName+"/";

        this.id = announce.getId();
        this.title = announce.getTitle();
        this.fileList = announce.getFileList().stream().map(postFile -> url+postFile.getUrl()).collect(Collectors.toList());
        this.createDate = announce.ConvertDate(announce.getCreateDate());
    }

}
