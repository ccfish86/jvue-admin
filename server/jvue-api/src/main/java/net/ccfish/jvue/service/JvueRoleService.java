/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.service;

import java.util.List;

import net.ccfish.jvue.model.JvueRole;
import net.ccfish.jvue.vm.ModuleAndMenus;
import net.ccfish.jvue.vm.RoleMenuDetails;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public interface JvueRoleService extends _AbstractService<JvueRole, Integer> {

    ModuleAndMenus<Integer> findModuleAndMenu(List<Integer> roles);

    JvueRole updateEnabled(Integer id, byte enabled);
    
    List<Integer> getRolesByApi(Integer apiId);

    RoleMenuDetails<Integer> getRoleInfo(Integer id);

}
