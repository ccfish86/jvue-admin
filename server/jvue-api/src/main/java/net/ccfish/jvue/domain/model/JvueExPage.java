/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import net.ccfish.jvue.autogen.model.JvuePage;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
public class JvueExPage  extends JvuePage {
    
    /**
     * 
     */
    private static final long serialVersionUID = 3285166079344965584L;
    
    @JsonProperty("children")
    private List<JvueExPage> jvuePages;

    /**
     * @return the jvuePages
     */
    public List<JvueExPage> getJvuePages() {
        return jvuePages;
    }

    /**
     * @param jvuePages the jvuePages to set
     */
    public void setJvuePages(List<JvueExPage> jvuePages) {
        this.jvuePages = jvuePages;
    }

}
