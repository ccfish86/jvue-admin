/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.domain.model;

import org.springframework.security.core.GrantedAuthority;

import net.ccfish.jvue.autogen.model.JvueRole;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
public class JvueExRole extends JvueRole implements GrantedAuthority {

    /**
     * 
     */
    private static final long serialVersionUID = 323659480741765788L;

    /* (non-Javadoc)
     * @see org.springframework.security.core.GrantedAuthority#getAuthority()
     */
    @Override
    public String getAuthority() {
        return getName();
    }

}
