/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.vm;

import java.util.List;

import net.ccfish.jvue.model.JvueMenu;
import net.ccfish.jvue.model.JvueModule;
import net.ccfish.jvue.model.JvueSegment;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
public class ModuleAndMenus {

    private List<JvueModule> modules;

    private List<JvueMenu> menus;
//    
//    private List<JvueSegment> segments;

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

//    public List<JvueSegment> getSegments() {
//        return segments;
//    }

//    public void setSegments(List<JvueSegment> segments) {
//        this.segments = segments;
//    }

}
