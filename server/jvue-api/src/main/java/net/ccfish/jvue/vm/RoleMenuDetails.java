/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.vm;

import org.springframework.util.MultiValueMap;

/**
 * 附带后台API权限
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public class RoleMenuDetails<T> extends ModuleAndMenus<T> {

    private MultiValueMap<Integer, T> apis;

    public MultiValueMap<Integer, T> getApis() {
        return apis;
    }

    public void setApis(MultiValueMap<Integer, T> apis) {
        this.apis = apis;
    }


}
