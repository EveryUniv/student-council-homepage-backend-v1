package com.rtsoju.dku_council_homepage.domain.post.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rtsoju.dku_council_homepage.domain.base.SuggestionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.QSuggestion;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Suggestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static com.rtsoju.dku_council_homepage.domain.post.entity.subentity.QSuggestion.suggestion;

public class SuggestionRepositoryImpl implements SuggestionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public SuggestionRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<Suggestion> findSuggestionPage(String query, String category, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder(suggestion.status.ne(SuggestionStatus.삭제));

        if (query != null) {
            builder.and(suggestion.title.contains(query)).or(suggestion.text.contains(query));
        }
        if (category != null) {
            builder.and(suggestion.category.eq(category));
        }



        List<Suggestion> content = queryFactory
                .selectFrom(suggestion)
                .where(builder)
                .orderBy(getOrderSpecifier(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Suggestion> countQuery = queryFactory
                .selectFrom(suggestion)
                .where(builder);


        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    private List<OrderSpecifier> getOrderSpecifier(Sort sort) {
        List<OrderSpecifier> orders = new ArrayList<>();

        sort.stream()
                .forEach(order -> {
                    Order direction = order.isAscending() ? Order.ASC : Order.DESC;
                    String prop = order.getProperty();
                    PathBuilder orderByExpression = new PathBuilder(Suggestion.class, "suggestion");
                    orders.add(new OrderSpecifier(direction, orderByExpression.get(prop)));
                });
        return orders;
    }
}
