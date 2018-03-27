/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.service;

import java.util.List;

import net.ccfish.jvue.model.User;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public interface UserService extends _AbstractService<User, Long> {

    /**
     * @param id
     * @param roles
     * @return
     * @since  1.0
     */
    User updateRoles(Long id, List<Integer> roles);


}
