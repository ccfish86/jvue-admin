/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.domain.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.ccfish.jvue.domain.model.JvueExRoleApi;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
public interface JvueExRoleApiMapper {

    List<JvueExRoleApi> selectByApi(@Param("apiCode") Integer apiCode);
    List<JvueExRoleApi> selectByRole(@Param("roleIds") List<Integer> roleIds);
    List<Integer> selectApiByRoleAndModule(@Param("roleId") Integer roleIds, @Param("moduleId") Integer moduleId);
}
