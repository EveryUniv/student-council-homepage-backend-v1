package com.rtsoju.dku_council_homepage.domain.post.entity.dto.page;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageRes<T> implements Serializable {
    private List<T> content;
    private boolean hasNext;
    private int totalPages;
    private long totalElements;
    private int page;
    private int size;
    private boolean first;
    private boolean last;

    public PageRes(List<T> content, Pageable pageable, long totalCount){
        final PageImpl<T> page = new PageImpl<>(content, pageable, totalCount);
        this.content = content;
        this.hasNext = page.hasNext();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.page = page.getNumber()+1;
        this.size =  page.getSize();
        this.first = page.isFirst();
        this.last = page.isLast();
    }
}
