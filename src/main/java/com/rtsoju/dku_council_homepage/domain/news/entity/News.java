package com.rtsoju.dku_council_homepage.domain.news.entity;

import com.rtsoju.dku_council_homepage.domain.base.Post;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name = "N")
public class News extends Post {
}
