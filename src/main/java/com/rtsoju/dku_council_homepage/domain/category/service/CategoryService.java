package com.rtsoju.dku_council_homepage.domain.category.service;

import com.rtsoju.dku_council_homepage.domain.category.entity.Category;
import com.rtsoju.dku_council_homepage.domain.category.entity.CategoryDto;
import com.rtsoju.dku_council_homepage.domain.category.repository.CategoryRepository;
import com.rtsoju.dku_council_homepage.exception.AlreadyExistException;
import com.rtsoju.dku_council_homepage.exception.BadRequestException;
import com.rtsoju.dku_council_homepage.exception.FindCategoryWithIdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public List<String> list() {
        List<String> categoryList = categoryRepository.findAll().stream().map(Category::getCategory).collect(Collectors.toList());
        return categoryList;

    }

    @Transactional
    public String save(CategoryDto data) {
        categoryRepository.findByCategory(data.getCategory())
                .ifPresent(param -> {
                    throw new AlreadyExistException();
                });
        Category save = categoryRepository.save(new Category(data));
        return save.getCategory();
    }
    @Transactional
    public void delete(CategoryDto data) {
        Category category = categoryRepository.findByCategory(data.getCategory()).orElseThrow(FindCategoryWithIdNotFoundException::new);
        categoryRepository.delete(category);

    }
}
