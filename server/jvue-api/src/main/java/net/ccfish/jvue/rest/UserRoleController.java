/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ccfish.jvue.model.UserRole;
import net.ccfish.jvue.service.UserRoleService;
import net.ccfish.jvue.service._AbstractService;
import net.ccfish.jvue.service.acl.AclResc;

/**
 * 用户相关
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@RestController
@RequestMapping("user-role")
@AclResc(id = 6100, code = "UserRole", name = "用户角色管理", homePage = "")
public class UserRoleController implements _BaseController<UserRole, Long> {

    @Autowired
    private UserRoleService userRoleService;

    /* (non-Javadoc)
     * @see net.ccfish.jvue.rest._BaseController#baseService()
     */
    @Override
    public _AbstractService<UserRole, Long> baseService() {
        return this.userRoleService;
    }
    
}
