/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.service;

import java.util.List;

import net.ccfish.jvue.model.JvueMenu;
import net.ccfish.jvue.rest.vm.CodeDto;
import net.ccfish.jvue.vm.ModuleAndMenus;
import net.ccfish.jvue.vm.RoleMenuDetails;

/**
 * 处理用户权限查询
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public interface JvueMenuService extends _AbstractService<JvueMenu, Integer> {
    List<JvueMenu> findAllRootMenu();

    ModuleAndMenus<Integer> findModuleAndMenu();

    List<JvueMenu> findByModule(Integer moduleId);

    RoleMenuDetails<CodeDto<Integer>> getAllRoleInfo();
}
