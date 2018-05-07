/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.ccfish.common.entity.CodeItem;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@ApiModel("授权API/Segment")
public class JvueRoleItem extends CodeItem<Integer> {

    @ApiModelProperty("ID项目")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
