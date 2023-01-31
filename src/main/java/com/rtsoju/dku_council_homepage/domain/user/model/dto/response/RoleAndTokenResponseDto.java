package com.rtsoju.dku_council_homepage.domain.user.model.dto.response;

import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RoleAndTokenResponseDto {
    private String accessToken;
    private String refreshToken;
    private boolean isAdmin;
    private String userName;
    private String classId;
    private String major;
    private String department;

    public RoleAndTokenResponseDto(String loginAccessToken, String loginRefreshToken, boolean isAdmin, User findUser) {
        this.accessToken = loginAccessToken;
        this.refreshToken = loginRefreshToken;
        this.isAdmin = isAdmin;
        this.userName = findUser.getName();
        this.classId = findUser.getClassId();
        this.major = findUser.getMajor().getName();
        this.department = findUser.getMajor().getDepartment().getName();

    }
}
