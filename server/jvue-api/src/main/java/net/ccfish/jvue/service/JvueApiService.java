/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.service;

import java.util.List;

import net.ccfish.jvue.autogen.model.JvueApi;


/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
public interface JvueApiService extends _AbstractService<JvueApi, Integer> {

    /**
     * @param pageId
     * @return
     * @since  1.0
     */
    List<JvueApi> findByMemu(Integer pageId);

    /**
     * @param pageId
     * @param apis
     * @return
     * @since  1.0
     */
    List<JvueApi> updateApisByMemu(Integer pageId, List<Integer> apis);

    /**
     * @param pageId
     * @since  1.0
     */
    void deleteByPage(Integer pageId);

}
