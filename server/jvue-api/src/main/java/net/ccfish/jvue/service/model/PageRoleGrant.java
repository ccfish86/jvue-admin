/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.service.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 画面权限(授予)
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@ApiModel("画面权限")
public class PageRoleGrant {

    @ApiModelProperty("画面ID")
    private Integer pageId;

    @ApiModelProperty("接口ID列表")
    private List<Integer> apiIds;

    @ApiModelProperty("片段ID列表")
    private List<Integer> segmentIds;

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public List<Integer> getApiIds() {
        return apiIds;
    }

    public void setApiIds(List<Integer> apiIds) {
        this.apiIds = apiIds;
    }

    public List<Integer> getSegmentIds() {
        return segmentIds;
    }

    public void setSegmentIds(List<Integer> segmentIds) {
        this.segmentIds = segmentIds;
    }
    

}
