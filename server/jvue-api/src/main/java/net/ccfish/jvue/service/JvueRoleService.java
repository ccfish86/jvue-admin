/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.service;

import java.util.List;

import net.ccfish.jvue.autogen.model.JvueRole;
import net.ccfish.jvue.service.model.ModuleAndPages;
import net.ccfish.jvue.service.model.PageRoleGrant;
import net.ccfish.jvue.service.model.RolePageDetails;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public interface JvueRoleService extends _AbstractService<JvueRole, Integer> {

    ModuleAndPages<Integer> findModuleAndPage(List<Integer> roles);

    JvueRole updateEnabled(Integer id, Integer enabled);
    
    List<Integer> getRolesByApi(Integer apiId);

    RolePageDetails<Integer> getRoleInfo(Integer id);

    /**
     * 角色授权
     * @param id 角色ID
     * @param moduleId 模块ID
     * @param pageRoles 画面权限
     * @return 更新（0:未;1:已更新）
     * @since  1.0
     */
    int grant(Integer id, Integer moduleId, List<PageRoleGrant> pageRoles);

}
