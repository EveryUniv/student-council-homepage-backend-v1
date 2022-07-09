package com.rtsoju.dku_council_homepage.domain.user.model.dto;

import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import lombok.Data;

@Data
public class RequestSignupDto {
    private String classId;
    private String password;
    private String name;

    private String phone;

    public User toUserEntity(){
        User user = new User(classId, password, name, phone);
        return user;
    }
}
