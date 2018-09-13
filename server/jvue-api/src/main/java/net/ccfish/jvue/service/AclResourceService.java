/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.service;

import java.util.List;

import net.ccfish.jvue.vm.AclResource;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
public interface AclResourceService {

    String getName(Integer id);

    List<AclResource> getAll();
}
