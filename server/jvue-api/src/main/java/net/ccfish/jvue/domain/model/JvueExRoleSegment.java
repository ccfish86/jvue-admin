/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.domain.model;

import net.ccfish.jvue.autogen.model.JvueRoleSegment;
import net.ccfish.jvue.autogen.model.JvueSegment;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
public class JvueExRoleSegment extends JvueRoleSegment {

    private JvueSegment segment;

    /**
     * @return the segment
     */
    public JvueSegment getSegment() {
        return segment;
    }

    /**
     * @param segment the segment to set
     */
    public void setSegment(JvueSegment segment) {
        this.segment = segment;
    }
    
    
}
