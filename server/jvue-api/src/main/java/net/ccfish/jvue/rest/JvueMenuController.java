/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ccfish.jvue.model.JvueMenu;
import net.ccfish.jvue.service.JvueMenuService;
import net.ccfish.jvue.service._AbstractService;
import net.ccfish.jvue.service.acl.AclResc;

/**
 * API相关
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@RestController
@RequestMapping("menu")
@AclResc(id = 5200, code = "JvueMenu", name = "画面管理", homePage = "")
public class JvueMenuController implements _BaseController<JvueMenu, Integer> {

    @Autowired
    private JvueMenuService jvueMenuService;

    /* (non-Javadoc)
     * @see net.ccfish.jvue.rest._BaseController#baseService()
     */
    @Override
    public _AbstractService<JvueMenu, Integer> baseService() {
        return this.jvueMenuService;
    }
    
}
