/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.service;

import java.util.List;

import net.ccfish.jvue.model.JvueApi;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public interface JvueApiService extends _AbstractService<JvueApi, Integer> {

    /**
     * @param menuId
     * @return
     * @since  1.0
     */
    List<JvueApi> findByMemu(Integer menuId);

    /**
     * @param menuId
     * @param apis
     * @return
     * @since  1.0
     */
    List<JvueApi> updateApisByMemu(Integer menuId, List<Integer> apis);

    /**
     * @param menuId
     * @since  1.0
     */
    void deleteByMenu(Integer menuId);

}
