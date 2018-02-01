/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import net.ccfish.jvue.model.User;
import net.ccfish.jvue.service.UserService;
import net.ccfish.jvue.service._AbstractService;
import net.ccfish.jvue.service.acl.AclResc;

/**
 * 用户相关
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@RestController
@RequestMapping("user")
@AclResc(id = 6000, code = "User", name = "用户管理", homePage = "")
@Api(tags  = "用户管理")
public class UserController implements _BaseController<User, Long> {

    @Autowired
    private UserService userService;

    /* (non-Javadoc)
     * @see net.ccfish.jvue.rest._BaseController#baseService()
     */
    @Override
    public _AbstractService<User, Long> baseService() {
        return this.userService;
    }
    
}
