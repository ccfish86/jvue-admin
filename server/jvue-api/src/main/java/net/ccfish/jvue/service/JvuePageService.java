/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.service;

import java.util.List;

import net.ccfish.jvue.autogen.model.JvuePage;
import net.ccfish.jvue.domain.model.JvueExPage;
import net.ccfish.jvue.domain.model.JvueRoleItem;
import net.ccfish.jvue.service.model.ModuleAndPages;
import net.ccfish.jvue.service.model.RolePageDetails;

/**
 * 处理用户权限查询
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public interface JvuePageService extends _AbstractService<JvuePage, Integer> {
    List<JvueExPage> findAllRootPage();

    ModuleAndPages<Integer> findModuleAndPage();

    List<JvuePage> findByModule(Integer moduleId);

    RolePageDetails<JvueRoleItem> getAllRoleInfo();
}
