/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.ccfish.common.JvueDataStatus;
import net.ccfish.common.acl.CurrentUser;
import net.ccfish.common.web.BaseModel;
import net.ccfish.jvue.security.JwtUserDetails;
import net.ccfish.jvue.service.JvuePageService;
import net.ccfish.jvue.service.JvueRoleService;
import net.ccfish.jvue.service.model.ModuleAndPages;

/**
 * 登录
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@RestController
@Api(tags  = "登录")
public class AuthController {

    @Autowired
    private JvuePageService jvuePageService;
    @Autowired
    private JvueRoleService jvueRoleService;
    
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 操作结果
     * @throws AuthenticationException 错误信息
     */
    @ApiOperation(value = "自动登录")
    @GetMapping(value = "/login")
    public String check() throws AuthenticationException {
        // 处理自动登录/记住密码等
        return "";
    }
    
    /**
	 * 用户菜单生成
	 * 
	 * @param principal
	 * @return
	 * @since 1.0
	 */
	@ApiOperation(value = "用户菜单")
	@GetMapping(value = "/auth/page")
	public BaseModel<ModuleAndPages<Integer>> getPage(@CurrentUser JwtUserDetails jwtUser) {
		// logger.info(principal.toString());
		if (jwtUser != null) {
			if (jwtUser.getSuperUser() == JvueDataStatus.SUPER_USER_TRUE) {
				// 返回所有菜单
				ModuleAndPages<Integer> pages = jvuePageService.findModuleAndPage();
				return BaseModel.ok(pages);
			} else {
				// 返回用户菜单
				// 根据用户role和缓存中的role权限生成菜单
				List<Integer> roles = jwtUser.getRoles();
				if (roles != null && !roles.isEmpty()) {
					ModuleAndPages<Integer> pages = jvueRoleService.findModuleAndPage(roles);
					return BaseModel.ok(pages);
				} else {
					return BaseModel.error("用户未授权");
				}
			}
		} else {
			logger.warn("无法获取登录信息");
		}
		return BaseModel.error("用户未授权");
	}

}
