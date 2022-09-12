package com.rtsoju.dku_council_homepage.domain.post.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rtsoju.dku_council_homepage.domain.base.PetitionStatus;
import com.rtsoju.dku_council_homepage.domain.post.entity.QComment;
import com.rtsoju.dku_council_homepage.domain.post.entity.dto.page.PagePetitionDto;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Petition;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.QPetition;
import com.rtsoju.dku_council_homepage.domain.post.entity.subentity.Suggestion;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.QUser;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static com.rtsoju.dku_council_homepage.domain.post.entity.QComment.comment;
import static com.rtsoju.dku_council_homepage.domain.post.entity.subentity.QPetition.petition;
import static com.rtsoju.dku_council_homepage.domain.post.entity.subentity.QSuggestion.suggestion;
import static com.rtsoju.dku_council_homepage.domain.user.model.entity.QUser.user;

public class PetitionRepositoryImpl implements PetitionRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public PetitionRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<PagePetitionDto> findPetitionPage(String query, PetitionStatus status, String category, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        if (query != null) {
            builder.and(petition.title.contains(query)).or(petition.text.contains(query));
        }
        if (status != null) {
            builder.and(petition.status.eq(status));
        }
        if (category != null) {
            builder.and(petition.category.eq(category));
        }

        List<PagePetitionDto> content = queryFactory
                .select(Projections.fields(PagePetitionDto.class,
                        petition.id,
                        petition.status.as("petitionStatus"),
                        petition.title,
                        user.name.as("userName"),
                        comment.count().as("commentCount"),
                        petition.hitCount.as("postHits"),
                        petition.isBlind
                ))
                .from(petition)
                .leftJoin(petition.comments, comment)
                .join(petition.user, user)
                .groupBy(petition.id)
                .where(builder)
                .orderBy(getOrderSpecifier(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<PagePetitionDto> countQuery = queryFactory
                .select(Projections.fields(PagePetitionDto.class,
                        petition.id,
                        petition.status.as("petitionStatus"),
                        petition.title,
                        user.name.as("userName"),
                        comment.count().as("commentCount"),
                        petition.hitCount.as("postHits"),
                        petition.isBlind
                ))
                .from(petition)
                .leftJoin(petition.comments, comment)
                .join(petition.user, user)
                .groupBy(petition.id)
                .where(builder);

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);

    }

    private List<OrderSpecifier> getOrderSpecifier(Sort sort) {
        List<OrderSpecifier> orders = new ArrayList<>();

        sort.stream()
                .forEach(order -> {
                    Order direction = order.isAscending() ? Order.ASC : Order.DESC;
                    String prop = order.getProperty();
                    System.out.println(prop);
                    if(prop.equals("comments")){
                        NumberPath<Long> commentCount = Expressions.numberPath(Long.class, "commentCount");
                        orders.add(new OrderSpecifier(direction, commentCount));
                    }else {
                        PathBuilder orderByExpression = new PathBuilder(Petition.class, "petition");
                        orders.add(new OrderSpecifier(direction, orderByExpression.get(prop)));
                    }
                });
        return orders;
    }
}
