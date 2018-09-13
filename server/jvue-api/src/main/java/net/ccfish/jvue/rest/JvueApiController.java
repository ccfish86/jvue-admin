/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MultiMap;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.ccfish.common.acl.AclResc;
import net.ccfish.common.web.BaseModel;
import net.ccfish.jvue.autogen.model.JvueApi;
import net.ccfish.jvue.service.JvueApiService;
import net.ccfish.jvue.service._AbstractService;
import net.ccfish.jvue.vm.AclResource;

/**
 * API相关
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/api")
@AclResc(id = 5100)
@Api(tags  = "接口管理")
public class JvueApiController implements _BaseController<JvueApi, Integer> {

    @Autowired
    private JvueApiService jvueApiService;

    @Autowired
    private HazelcastInstance hazelcastInstance;

    /*
     * (non-Javadoc)
     * 
     * @see com.hxxt.admin.rest._BaseController#baseService()
     */
    @Override
    public _AbstractService<JvueApi, Integer> baseService() {
        return this.jvueApiService;
    }
    
    @GetMapping("resources")
    @ApiOperation(value = "资源")
    @AclResc(id = 11)
    public BaseModel<List<AclResource>> getResources() {
        MultiMap<Integer, AclResource> resourcesMap = hazelcastInstance.getMultiMap("acl-resource");
        
        Collection<AclResource> list = resourcesMap.values();
        return new BaseModel<List<AclResource>>().setData(new ArrayList<>(list));
    }

}
