/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.domain.model;

import net.ccfish.jvue.autogen.model.JvueApi;
import net.ccfish.jvue.autogen.model.JvueRole;
import net.ccfish.jvue.autogen.model.JvueRoleApi;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
public class JvueExRoleApi extends JvueRoleApi {

    /**
     * 
     */
    private static final long serialVersionUID = -1138621735187298157L;
    
    private JvueRole role;
    private JvueApi api;

    /**
     * @return the role
     */
    public JvueRole getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(JvueRole role) {
        this.role = role;
    }

    /**
     * @return the api
     */
    public JvueApi getApi() {
        return api;
    }

    /**
     * @param api the api to set
     */
    public void setApi(JvueApi api) {
        this.api = api;
    }
    
}
