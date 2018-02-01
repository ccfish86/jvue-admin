/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import net.ccfish.jvue.model.JvueRole;
import net.ccfish.jvue.service.JvueRoleService;
import net.ccfish.jvue.service._AbstractService;
import net.ccfish.jvue.service.acl.AclResc;

/**
 * Role相关
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@RestController
@RequestMapping("role")
@AclResc(id = 5300, code = "JvueRole", name = "角色管理", homePage = "")
@Api(tags  = "角色管理")
public class JvueRoleController implements _BaseController<JvueRole, Integer> {

    @Autowired
    private JvueRoleService jvueRoleService;

    /* (non-Javadoc)
     * @see net.ccfish.jvue.rest._BaseController#baseService()
     */
    @Override
    public _AbstractService<JvueRole, Integer> baseService() {
        return this.jvueRoleService;
    }
    
}
