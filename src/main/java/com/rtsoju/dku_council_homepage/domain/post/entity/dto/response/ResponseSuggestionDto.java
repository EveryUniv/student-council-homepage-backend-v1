package com.rtsoju.dku_council_homepage.domain.post.entity.dto.response;

import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Suggestion;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class ResponseSuggestionDto {
    private Long id;
    private String title;
    private String text;
    private List<FileUrlWithNameDto> fileList;
    private String createDate;
    private String category;
    private List<CommentResponseDto> commentList;
    private int likeCount;
    private boolean isLike;
    private boolean isMine;

    public ResponseSuggestionDto(Long userId, Suggestion suggestion) {
        this.id = suggestion.getId();
        this.title = suggestion.getTitle();
        this.text = suggestion.getText();
        this.fileList = suggestion.getFiles();
        this.createDate = suggestion.convertDate(suggestion.getCreateDate());
        this.category = suggestion.getCategory();
        this.commentList = createAnonymityUser(
                suggestion.getComments().stream()
                .map(comment -> new CommentResponseDto(userId, comment))
                        .collect(Collectors.toList())
        );
//        this.answer = suggestion.getAnswer();
        this.likeCount = suggestion.getLikesList().size();
        this.isLike = findLikeUser(suggestion, userId);
        this.isMine = suggestion.getUser().getId().equals(userId);
    }

    private List<CommentResponseDto> createAnonymityUser(List<CommentResponseDto> data){
        //댓글 작성 pool 생성
        //중복으로 인한 indexJump를 예방하기 위한 중복 제거. 순서가 보장 되어야 한다.
        List<Long> collect = data.stream()
                .map(CommentResponseDto::getUserId)
                .collect(Collectors.toList());
        //중복 제거.
        List<Long> userIdPool = collect.stream().distinct().collect(Collectors.toList());
        //indexOf로 익명 번호를 생성한다. 같은 유저의 경우 초기값 인덱스를 반환.
        data.forEach(
                        commentUser -> {
                            commentUser.setAnonymousNum(userIdPool.indexOf(commentUser.getUserId()));
                        }
                );
        return data;
    }

    private boolean findLikeUser(Post post, Long userId){
        List<Long> collect = post.getLikesList()
                .stream()
                .map(likes -> likes.getUser().getId())
                .collect(Collectors.toList());
        return collect.contains(userId);

    }

}
