package com.rtsoju.dku_council_homepage.domain.rule.entity;

import com.rtsoju.dku_council_homepage.domain.base.Post;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name = "R")
public class Rule extends Post {
}
