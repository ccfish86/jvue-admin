/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.vm;

import java.util.List;

import org.springframework.util.MultiValueMap;

import net.ccfish.jvue.model.JvueMenu;
import net.ccfish.jvue.model.JvueModule;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
public class ModuleAndMenus<T> {

    private List<JvueModule> modules;

    private List<JvueMenu> menus;
    
    // 画面片段<画面ID> 画面片段ID
    private MultiValueMap<Integer, T> segments;

    public List<JvueModule> getModules() {
        return modules;
    }

    public void setModules(List<JvueModule> modules) {
        this.modules = modules;
    }

    public List<JvueMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<JvueMenu> menus) {
        this.menus = menus;
    }

    public MultiValueMap<Integer, T> getSegments() {
        return segments;
    }

    public void setSegments(MultiValueMap<Integer, T> segments) {
        this.segments = segments;
    }


}
