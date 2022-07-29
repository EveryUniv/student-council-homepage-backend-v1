package com.rtsoju.dku_council_homepage.domain.post.service;

import com.rtsoju.dku_council_homepage.domain.base.PetitionStatus;
import com.rtsoju.dku_council_homepage.domain.page.dto.PostSummary;
import com.rtsoju.dku_council_homepage.domain.post.entity.Comment;
import com.rtsoju.dku_council_homepage.domain.post.entity.Post;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PagePetitionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.CommentRequestDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.request.RequestPetitionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.IdResponseDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.ResponsePetitionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import com.rtsoju.dku_council_homepage.domain.post.repository.CommentRepository;
import com.rtsoju.dku_council_homepage.domain.post.repository.PetitionRepository;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import com.rtsoju.dku_council_homepage.domain.user.repository.UserRepository;
import com.rtsoju.dku_council_homepage.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PetitionService {
    private final PetitionRepository petitionRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    private final int accept = 20;

    public Page<PagePetitionDto> petitionPage(String query, String status, String category, Pageable pageable) {
        Page<Petition> page;
        if (status != null && query != null) {
            PetitionStatus lookup = PetitionStatus.lookup(status);
            page = petitionRepository.findAllByStatusAndTitleContainsOrTextContains(lookup,query, query, pageable);
        }else if(status != null && query == null){
            PetitionStatus lookup = PetitionStatus.lookup(status);
            page = petitionRepository.findAllByStatus(lookup, pageable);
        }else if (query != null && status == null) {
            page = petitionRepository.findAllByTitleContainsOrTextContains(query, query, pageable);
        } else if(category != null && status != null){
            PetitionStatus lookup = PetitionStatus.lookup(status);
            page = petitionRepository.findAllByStatusAndCategory(lookup, category, pageable);
        } else if(category != null && status == null){
            page = petitionRepository.findAllByCategory(category, pageable);
        } else{
            page = petitionRepository.findAll(pageable);
        }
        return page.map(PagePetitionDto::new);
    }

    public List<PostSummary> postPage() {
        List<Petition> petitionList = petitionRepository.findTop5ByOrderByCreateDateDesc();
        return petitionList.stream().map(Post::summarize).collect(Collectors.toList());
    }


    @Transactional
    public IdResponseDto createPetition(long id, RequestPetitionDto data) {
        User user = userRepository.findById(id).orElseThrow(FindUserWithIdNotFoundException::new);
        if(user.isPetitionCreate()){
            throw new DuplicateCreatePetition("1일 1회만 청원 등록이 가능합니다.");
        }
        Petition petition = new Petition(user, data.getTitle(), data.getText(), data.getCategory());
        Petition save = petitionRepository.save(petition);
        user.createPetition();
        return new IdResponseDto(save.getId());
    }



    @Transactional //연관관계 땡겨와야하기에 나중에 repository jpql로 설정하면 지울 예정...
    public ResponsePetitionDto findOne(Long id) {
        Petition petition = petitionRepository.findById(id).orElseThrow(FindPostWithIdNotFoundException::new);
        petition.plusHits();
        return new ResponsePetitionDto(petition);
    }

    @Transactional
    public void deleteOne(Long id) {
        Petition petition = petitionRepository.findById(id).orElseThrow(FindPostWithIdNotFoundException::new);
        petitionRepository.delete(petition);
    }


    public void checkDuplicateCommentByUser(Long postId, Long userId) {
        Petition petition = petitionRepository.findById(postId).orElseThrow(FindPostWithIdNotFoundException::new);
        List<Long> userIdList = petition.getComments().stream()
                .map(comment -> comment.getUser().getId())
                .collect(Collectors.toList());
        if(userIdList.contains(userId)){
            throw new DuplicateCommentException("이미 댓글을 등록했습니다.");
        }
    }

    @Transactional
    public Comment createComment(Long postId, Long userId, CommentRequestDto data) {
        Petition petition = petitionRepository.findById(postId).orElseThrow(FindPostWithIdNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(FindUserWithIdNotFoundException::new);
        Comment comment = new Comment(petition, user, data.getText());
        if(petition.getComments().size() >= accept){
            petition.UpdateStandBy();
        }
        return commentRepository.save(comment);
    }

    public IdResponseDto createCommentByAdmin(Long id, CommentRequestDto data) {
        Petition petition = petitionRepository.findById(id).orElseThrow(FindPostWithIdNotFoundException::new);
//        checkAlreadyExistCommentByAdmin(petition);
        petition.createCommentByAdmin(data.getText());
        return new IdResponseDto(petition.getId());
    }

    private void checkAlreadyExistCommentByAdmin(Petition petition){
        if(!petition.getAdminComment().isBlank()){
            throw new DuplicateCommentException();
        }
    }


    public Petition changeBlind(Long id) {
        Petition petition = petitionRepository.findById(id).orElseThrow(FindPostWithIdNotFoundException::new);
        petition.changeBlind();
        return petition;
    }
}
