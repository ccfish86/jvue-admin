/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import net.ccfish.common.acl.AclResc;
import net.ccfish.jvue.autogen.model.JvueUserRole;
import net.ccfish.jvue.service.JvueUserRoleService;
import net.ccfish.jvue.service._AbstractService;

/**
 * 用户相关
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@RestController
@RequestMapping("user-role")
@AclResc(id = 4100)
@Api(tags  = "用户角色管理")
public class UserRoleController implements _BaseController<JvueUserRole, Long> {

    @Autowired
    private JvueUserRoleService userRoleService;

    /* (non-Javadoc)
     * @see com.hxxt.admin.rest._BaseController#baseService()
     */
    @Override
    public _AbstractService<JvueUserRole, Long> baseService() {
        return this.userRoleService;
    }
    
    
    
}
