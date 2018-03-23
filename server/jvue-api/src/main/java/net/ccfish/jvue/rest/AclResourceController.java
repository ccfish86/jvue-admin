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
import net.ccfish.common.web.BaseModel;
import net.ccfish.jvue.service.AclResourceService;
import net.ccfish.jvue.service.acl.AclResc;
import net.ccfish.jvue.vm.AclResource;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@RestController
@RequestMapping("acl")
@Api(tags  = "ACL管理")
@AclResc(id = 5500, code = "AclResource", name = "ACL管理", homePage = "")
public class AclResourceController {
    
    @Autowired
    private AclResourceService aclResourceService;
    
    @AclResc(id = 1, code = "AclResourceList", name = "列表")
    @GetMapping("")
    public BaseModel<List<AclResource>> getResources() {
        
        List<AclResource> resources = aclResourceService.getAll();
        
        return new BaseModel<List<AclResource>>().setData(resources);
    }
}
