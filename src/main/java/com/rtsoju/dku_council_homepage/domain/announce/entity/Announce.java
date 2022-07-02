package com.rtsoju.dku_council_homepage.domain.announce.entity;

import com.rtsoju.dku_council_homepage.domain.base.Post;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name = "A")
public class Announce extends Post {
}
