/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import net.ccfish.common.acl.AclResc;
import net.ccfish.jvue.autogen.model.JvueSegment;
import net.ccfish.jvue.service.JvueSegmentService;
import net.ccfish.jvue.service._AbstractService;

/**
 * Segment相关
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@RestController
@RequestMapping("/api/segment")
@AclResc(id = 5400)
@Api(tags  = "画面片段管理")
public class JvueSegmentController implements _BaseController<JvueSegment, Integer> {

    @Autowired
    private JvueSegmentService jvueSegmentService;

    /* (non-Javadoc)
     * @see com.hxxt.admin.rest._BaseController#baseService()
     */
    @Override
    public _AbstractService<JvueSegment, Integer> baseService() {
        return this.jvueSegmentService;
    }
    
}
