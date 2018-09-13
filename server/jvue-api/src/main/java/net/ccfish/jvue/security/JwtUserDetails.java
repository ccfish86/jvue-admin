/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */
package net.ccfish.jvue.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 安全用户模型
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public class JwtUserDetails implements UserDetails {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private final Long id;
    private final String username;
    private final String nickname;
    private final String email;
    private final String password;
    private final Integer superUser;
    private final Collection<? extends GrantedAuthority> authorities;
    private final List<Integer> roles;

    public JwtUserDetails(Long id, String username, String password, Integer superUser, String nickname, String email,
            Collection<? extends GrantedAuthority> authorities, List<Integer> roles) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.authorities = authorities;
        this.id = id;
        this.superUser = superUser;
        this.roles = roles;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public Integer getSuperUser() {
        return superUser;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public List<Integer> getRoles() {
        return roles;
    }

}
