package com.rtsoju.dku_council_homepage.domain.user.model.dto.request;

import com.rtsoju.dku_council_homepage.domain.base.Department;
import com.rtsoju.dku_council_homepage.domain.base.Major;
import com.rtsoju.dku_council_homepage.domain.base.Register;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import lombok.Data;

@Data
public class RequestSignupDto {
    private String classId;
    private String password;
    private String name;


    private Major major;
    private Register register;

    private String phone;


    public User toUserEntity(){
        return new User(classId, password, name, major, register, phone);
    }
}
