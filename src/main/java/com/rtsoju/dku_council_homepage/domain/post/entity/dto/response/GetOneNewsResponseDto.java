package com.rtsoju.dku_council_homepage.domain.post.entity.dto.response;

import com.rtsoju.dku_council_homepage.domain.post.entity.Comment;
import com.rtsoju.dku_council_homepage.domain.post.entity.PostFile;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.News;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class GetOneNewsResponseDto {
    private String title;
    private String text;
    private List<String> files = new ArrayList<>();
    private List<Comment> commentList = new ArrayList<>();
    private String writer;


    private final String s3Domain = "https://api-storage.cloud.toast.com/v1/";

    // 빈이 아니라 주입이 안되나..?
//    @Value("${nhn.os.storageAccount}")
//    private String storageAccount;
//    @Value("${nhn.os.storageName}")
//    private String storageName;

    private final String storageAccount = "AUTH_34f4838a2b3047f39ac9cb0701558e46";
    private final String storageName = "main-storage";

    public GetOneNewsResponseDto(News news) {
        this.writer = news.getUser().getName();
        this.title = news.getTitle();
        this.text = news.getText();
        this.commentList = news.getComments();

        List<PostFile> fileList = news.getFileList();
        String url = s3Domain + storageAccount + "/" + storageName + "/";
        for (PostFile file : fileList) {
            this.files.add(url + file.getUrl());
        }
    }


}
