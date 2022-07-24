package com.rtsoju.dku_council_homepage.domain.post.entity.dto.response;

import com.rtsoju.dku_council_homepage.domain.post.entity.PostFile;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Announce;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResponseAnnounceDto {
    private Long id;
    private String title;
    private String text;
    private List<String> fileList = new ArrayList<>();
    private String createDate;

    public ResponseAnnounceDto(Announce announce){
        this.id = announce.getId();
        this.title = announce.getTitle();
        this.text = announce.getText();
        final String s3Domain = "https://api-storage.cloud.toast.com/v1/";
        final String storageAccount = "AUTH_34f4838a2b3047f39ac9cb0701558e46";
        final String storageName = "main-storage";
        final String url = s3Domain+storageAccount+"/"+storageName+"/";
        this.fileList = announce.getFileList().stream().map(postFile -> url+postFile.getUrl()).collect(Collectors.toList());
        this.createDate = announce.ConvertDate(announce.getCreateDate());
    }

}
