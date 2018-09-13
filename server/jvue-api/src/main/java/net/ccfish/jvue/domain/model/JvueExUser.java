/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.domain.model;

import java.util.List;
import java.util.stream.Collectors;

import net.ccfish.jvue.autogen.model.JvueUser;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public class JvueExUser extends JvueUser {

    /**
     * 
     */
    private static final long serialVersionUID = 7005756641716238160L;
    
    List<JvueExRole> roles;

    /**
     * @return the roles
     */
    public List<JvueExRole> getRoles() {
        return roles;
    }
    
    public List<Integer> getRoleIds() {
        return roles.stream().map(role->role.getId()).collect(Collectors.toList());
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<JvueExRole> roles) {
        this.roles = roles;
    }


}
