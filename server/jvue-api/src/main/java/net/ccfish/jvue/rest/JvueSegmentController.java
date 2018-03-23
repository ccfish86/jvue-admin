/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import net.ccfish.jvue.model.JvueSegment;
import net.ccfish.jvue.service.JvueSegmentService;
import net.ccfish.jvue.service._AbstractService;
import net.ccfish.jvue.service.acl.AclResc;

/**
 * Segment相关
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@RestController
@RequestMapping("segment")
@AclResc(id = 5400, code = "JvueSegment", name = "画面片段管理", homePage = "")
@Api(tags  = "画面片段管理")
public class JvueSegmentController implements _BaseController<JvueSegment, Integer> {

    @Autowired
    private JvueSegmentService jvueSegmentService;

    /* (non-Javadoc)
     * @see net.ccfish.jvue.rest._BaseController#baseService()
     */
    @Override
    public _AbstractService<JvueSegment, Integer> baseService() {
        return this.jvueSegmentService;
    }
    
}
