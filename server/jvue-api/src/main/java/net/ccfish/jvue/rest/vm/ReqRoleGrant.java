/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest.vm;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.ccfish.jvue.service.model.PageRoleGrant;

/**
 * 授权请求参数
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@ApiModel("授权请求参数")
public class ReqRoleGrant {

    @ApiModelProperty("模块ID")
    private Integer moduleId; 
    
    @ApiModelProperty("画面权限")
    private List<PageRoleGrant> pageRoles;

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public List<PageRoleGrant> getPageRoles() {
        return pageRoles;
    }

    public void setPageRoles(List<PageRoleGrant> pageRoles) {
        this.pageRoles = pageRoles;
    }

}
