/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.service.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.util.MultiValueMap;

import net.ccfish.jvue.autogen.model.JvueModule;
import net.ccfish.jvue.domain.model.JvueExPage;

/**
 * 权限数据
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
public class ModuleAndPages<T> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2418348715919493940L;

    private List<JvueModule> modules;

    private List<JvueExPage> pages;
    
    // 画面片段<画面ID> 画面片段ID
    private MultiValueMap<Integer, T> segments;

    public List<JvueModule> getModules() {
        return modules;
    }

    public void setModules(List<JvueModule> modules) {
        this.modules = modules;
    }

    public List<JvueExPage> getPages() {
        return pages;
    }

    public void setPages(List<JvueExPage> pages) {
        this.pages = pages;
    }

    public MultiValueMap<Integer, T> getSegments() {
        return segments;
    }

    public void setSegments(MultiValueMap<Integer, T> segments) {
        this.segments = segments;
    }


}
