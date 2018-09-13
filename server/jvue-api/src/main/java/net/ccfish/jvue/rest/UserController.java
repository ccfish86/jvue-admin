/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.ccfish.common.JvueDataStatus;
import net.ccfish.common.acl.AclResc;
import net.ccfish.common.web.BaseModel;
import net.ccfish.common.web.PageParam;
import net.ccfish.common.web.PagedModel;
import net.ccfish.jvue.autogen.model.JvueUser;
import net.ccfish.jvue.domain.model.JvueExUser;
import net.ccfish.jvue.security.JwtUserDetails;
import net.ccfish.jvue.service.JvueUserService;
import net.ccfish.jvue.service.model.ModuleAndPages;

/**
 * 用户相关
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@RestController
@RequestMapping("/api/user")
@AclResc(id = 4000)
@Api(tags  = "用户管理")
public class UserController {

    @Autowired
    private JvueUserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("")
    @AclResc(id = 1)
    @ApiOperation(value = "列表")
    public PagedModel<JvueExUser> list(@ModelAttribute PageParam pageParam) {
        List<JvueExUser> result = userService.searchUser(pageParam);
        return PagedModel.from((Page<JvueExUser>)result);
    }
    
    @AclResc(id = 2)
    @GetMapping("{id}")
    @ApiOperation(value = "详情")
    public  BaseModel<JvueUser> detail(@PathVariable("id") Long id) {        
        JvueUser result = userService.getOne(id);
        return new BaseModel<JvueUser>().setData(result);
    }

    @PostMapping("")
    @AclResc(id = 3)
    @ApiOperation(value = "追加")
    public  BaseModel<JvueUser> add(@RequestBody JvueUser user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userService.save(user);
        return new BaseModel<JvueUser>().setData(user);
    }
    
    @PutMapping("{id}")
    @AclResc(id = 4)
    @ApiOperation(value = "更新")
    public  BaseModel<Long> update(@PathVariable("id") Long id, @RequestBody JvueUser data) {
        if (!StringUtils.isEmpty(data.getPassword())) {
            String password = passwordEncoder.encode(data.getPassword());
            data.setPassword(password);
        }
        userService.update(id, data);
        return new BaseModel<Long>().setData(id);
    }
    
    @DeleteMapping("{id}")
    @AclResc(id = 5)
    @ApiOperation(value = "删除")
    public BaseModel<Long> delete(Principal principal, @PathVariable("id") Long id) {
    	if (principal instanceof Authentication ) {
            // JwtUserDetails
            Authentication authentication = (Authentication) principal;
            if (authentication.getPrincipal() instanceof JwtUserDetails) {
                JwtUserDetails jwtUser = (JwtUserDetails) authentication.getPrincipal();
                if (Objects.equals(jwtUser.getId(),id)) {
                    return BaseModel.error("不能删除自己");
                }
            } else {
                logger.warn("无法获取登录信息  {}", authentication.getPrincipal());
            }
        } else {
            logger.warn("无法获取登录信息  {}", principal);
        }
        userService.delete(id);
        return new BaseModel<Long>().setData(id);
    }

    @PutMapping("/ext/{id}/role")
    @AclResc(id = 11)
    @ApiOperation(value = "更新权限")
    public BaseModel<JvueUser> updateRoles(@PathVariable("id") Long id, @RequestBody List<Integer> roles) {
        
        logger.debug("更新权限{} {}", id, roles);
        JvueUser user = userService.updateRoles(id, roles);
        return new BaseModel<JvueUser>().setData(user);
    }

}
