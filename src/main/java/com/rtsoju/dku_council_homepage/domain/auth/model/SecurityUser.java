package com.rtsoju.dku_council_homepage.domain.auth.model;

import com.rtsoju.dku_council_homepage.domain.user.model.entity.User;
import com.rtsoju.dku_council_homepage.domain.user.model.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SecurityUser implements UserDetails {

    private Long id;
    private String classId;
    private String password;
    private List<String> authorities = new ArrayList<>();

    public SecurityUser(User user) {
        id = user.getId();
        classId = user.getClassId();
        password = user.getPassword();

        List<UserRole> roles = user.getRoles();
        for (UserRole role : roles) {
            authorities.add(role.getRole());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return classId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
