/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.domain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.ccfish.jvue.domain.model.JvueExRole;
import net.ccfish.jvue.domain.model.JvueExUser;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
public interface JvueExUserMapper {

    JvueExUser findByUsername(@Param("username") String username);
    
    List<JvueExUser> searchUsers(@Param("username") String username);
    
    List<JvueExRole> selectRoles(@Param("userId") Long userId);
}
