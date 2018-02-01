/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ccfish.jvue.model.JvueModule;
import net.ccfish.jvue.service.JvueModuleService;
import net.ccfish.jvue.service._AbstractService;
import net.ccfish.jvue.service.acl.AclResc;

/**
 * Module相关
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@RestController
@RequestMapping("module")
@AclResc(id = 5000, code = "JvueModule", name = "模块管理", homePage = "")
public class JvueModuleController implements _BaseController<JvueModule, Integer> {

    @Autowired
    private JvueModuleService jvueModuleService;

    /* (non-Javadoc)
     * @see net.ccfish.jvue.rest._BaseController#baseService()
     */
    @Override
    public _AbstractService<JvueModule, Integer> baseService() {
        return this.jvueModuleService;
    }
    
}
