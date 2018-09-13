/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.ccfish.common.acl.AclResc;
import net.ccfish.common.web.BaseModel;
import net.ccfish.jvue.service.AclResourceService;
import net.ccfish.jvue.vm.AclResource;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@RestController
@RequestMapping("/api/acl")
@Api(tags  = "ACL管理")
@AclResc(id = 5500)
public class AclResourceController {
    
    @Autowired
    private AclResourceService aclResourceService;
    
    @AclResc(id = 1)
    @ApiOperation(value = "资源")
    @GetMapping("")
    public BaseModel<List<AclResource>> getResources() {
        
        List<AclResource> resources = aclResourceService.getAll();
        
        return new BaseModel<List<AclResource>>().setData(resources);
    }
}
