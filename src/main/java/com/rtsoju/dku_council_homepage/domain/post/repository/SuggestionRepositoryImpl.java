package com.rtsoju.dku_council_homepage.domain.post.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rtsoju.dku_council_homepage.domain.base.SuggestionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.QSuggestion;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Suggestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import java.util.List;

import static com.rtsoju.dku_council_homepage.domain.post.entity.subentity.QSuggestion.suggestion;

public class SuggestionRepositoryImpl implements SuggestionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public SuggestionRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<Suggestion> findSuggestionPage(String query, SuggestionStatus status, String category, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        if (query != null) {
            builder.and(suggestion.title.contains(query)).or(suggestion.text.contains(query));
        }
        if (status != null) {
            builder.and(suggestion.status.eq(status));
        }
        if (category != null) {
            builder.and(suggestion.category.eq(category));
        }


        QueryResults<Suggestion> results = queryFactory
                .selectFrom(suggestion)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Suggestion> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }
}
