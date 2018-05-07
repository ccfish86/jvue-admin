/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.service;

import java.util.List;

import net.ccfish.common.web.PageParam;
import net.ccfish.jvue.autogen.model.JvueUser;
import net.ccfish.jvue.domain.model.JvueExUser;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public interface JvueUserService extends _AbstractService<JvueUser, Long> {

    /**
     * @param id
     * @param roles
     * @return
     * @since  1.0
     */
    JvueUser updateRoles(Long id, List<Integer> roles);

    List<JvueExUser> searchUser(PageParam pageParam);
}
