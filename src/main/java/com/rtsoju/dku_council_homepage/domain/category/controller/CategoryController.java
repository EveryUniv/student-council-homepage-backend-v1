package com.rtsoju.dku_council_homepage.domain.category.controller;

import com.rtsoju.dku_council_homepage.common.ResponseResult;
import com.rtsoju.dku_council_homepage.common.SuccessResponseResult;
import com.rtsoju.dku_council_homepage.domain.category.entity.Category;
import com.rtsoju.dku_council_homepage.domain.category.entity.CategoryDto;
import com.rtsoju.dku_council_homepage.domain.category.repository.CategoryRepository;
import com.rtsoju.dku_council_homepage.domain.category.service.CategoryService;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.response.ResponsePetitionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity<ResponseResult> list() {
        List<String> list = categoryService.list();
        return ResponseEntity.ok() //200
                .body(new SuccessResponseResult(list));
    }


    @PostMapping
    public ResponseEntity<ResponseResult> insert(@RequestBody CategoryDto data){
        String save = categoryService.save(data);
        return ResponseEntity.ok()
                .body(new SuccessResponseResult(save+" 카테고리 생성 완료!"));
    }

    @DeleteMapping
    public ResponseEntity<ResponseResult> delete(@RequestBody CategoryDto data){
        categoryService.delete(data);
        return ResponseEntity.ok()
                .body(new SuccessResponseResult(data.getCategory()+" 카테고리 삭제 완료!"));
    }
}
