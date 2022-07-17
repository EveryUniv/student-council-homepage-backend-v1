package com.rtsoju.dku_council_homepage.domain.post.entity.subentity;

import com.rtsoju.dku_council_homepage.domain.post.entity.Post;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("R")
public class Rule extends Post {
}
