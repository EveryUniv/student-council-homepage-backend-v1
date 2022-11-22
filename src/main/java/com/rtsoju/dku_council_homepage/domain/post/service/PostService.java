package com.rtsoju.dku_council_homepage.domain.post.service;

import com.rtsoju.dku_council_homepage.domain.post.entity.Comment;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.CommentRequestDto;
import com.rtsoju.dku_council_homepage.domain.post.repository.CommentRepository;
import com.rtsoju.dku_council_homepage.domain.post.repository.PostRepository;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import com.rtsoju.dku_council_homepage.domain.user.repository.UserRepository;
import com.rtsoju.dku_council_homepage.exception.FindPostWithIdNotFoundException;
import com.rtsoju.dku_council_homepage.exception.FindUserWithIdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Comment createComment(Long postId, CommentRequestDto dto, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new FindPostWithIdNotFoundException("해당 id와 일치하는 post가 없습니다."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new FindUserWithIdNotFoundException("해당 id와 일치하는 User가 없습니다."));

        Comment comment = new Comment(post, user, dto.getText());
        return commentRepository.save(comment);
    }

    public void postHitByCookie(Post post, HttpServletRequest request, HttpServletResponse response){
        Cookie oldCookie = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            oldCookie = Arrays.stream(cookies).filter(cookie -> cookie.equals("dku_post")).findFirst().get();
        }
        if(oldCookie != null){
            if(!oldCookie.getValue().contains("[" + post.getId().toString() + "]")){
                post.plusHits();
                oldCookie.setValue(oldCookie.getValue() + "_[" + post.getId() + "]");
                oldCookie.setPath("/");
                oldCookie.setMaxAge(60*60*24);
                response.addCookie(oldCookie);
            }
        }else{
            post.plusHits();
//            Cookie cookie = new Cookie("dku_post", "[" + post.getId() + "]");
//            cookie.setPath("/");
//            cookie.setSecure(false);
//            cookie.setMaxAge(60*60*24);
            ResponseCookie cookie = ResponseCookie.from("dku_post", "[" + post.getId() + "]")
                    .domain("www.dku54play.site")
                    .path("/")
                    .maxAge(60 * 60 * 24)
                    .sameSite("None")
                    .secure(false)
                    .build();
            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }

    }
}
