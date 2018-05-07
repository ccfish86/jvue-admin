/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.domain.model;

import net.ccfish.jvue.autogen.model.JvueRolePage;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public class JvueExRolePage extends JvueRolePage {

    /**
     * 
     */
    private static final long serialVersionUID = -4377721433834950628L;
    
    private JvueExPage page;

    /**
     * @return the page
     */
    public JvueExPage getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(JvueExPage page) {
        this.page = page;
    }

}
