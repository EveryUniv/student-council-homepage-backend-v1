package com.rtsoju.dku_council_homepage.domain.base;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass //슈퍼타입 지정
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {
    @CreatedDate
    @Column(updatable = false, name = "create_at")
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "update_at")
    private LocalDateTime updateDate;

}
