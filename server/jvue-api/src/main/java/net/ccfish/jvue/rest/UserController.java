/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.ccfish.common.web.BaseModel;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    /* (non-Javadoc)
     * @see net.ccfish.jvue.rest._BaseController#baseService()
     */
    @Override
    public _AbstractService<User, Long> baseService() {
        return this.userService;
    }
    
    public BaseModel<User> add(@RequestBody User user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        baseService().save(user);
        return new BaseModel<User>().setData(user);
    }

    @PutMapping("/ext/{id}/role")
    @AclResc(id = 11, code = "updateRoles", name = "更新权限")
    @ApiOperation(value = "更新权限")
    public BaseModel<User> updateRoles(@PathVariable("id") Long id, @RequestBody List<Integer> roles) {
        User user = userService.updateRoles(id, roles);
        return new BaseModel<User>().setData(user);
    }
    
}
