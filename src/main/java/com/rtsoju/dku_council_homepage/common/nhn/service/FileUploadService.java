package com.rtsoju.dku_council_homepage.common.nhn.service;

import com.rtsoju.dku_council_homepage.domain.post.entity.PostFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {
    private final NHNAuthService nhnAuthService;
    private final ObjectStorageService s3service;


    public ArrayList<PostFile> uploadFiles(List<MultipartFile> files, String post){
        String token = nhnAuthService.requestToken();
        ArrayList<PostFile> postFiles = new ArrayList<>();
        files.forEach(file -> {
            String originName = file.getOriginalFilename();
            String ext = originName.substring(originName.lastIndexOf(".") + 1);

            String fileId = post + "-" + UUID.randomUUID() + "." + ext;

            try {
                s3service.uploadObject(token, fileId, file.getInputStream());
                postFiles.add(new PostFile(fileId, originName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return postFiles;
    }

    public void deletePostFiles(List<PostFile> files){
        String token = nhnAuthService.requestToken();
        for(PostFile file : files){
            s3service.deleteObject(token, file.getUrl());
        }
    }


}
