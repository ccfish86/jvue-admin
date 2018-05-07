/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.ccfish.common.acl.AclResc;
import net.ccfish.common.entity.CodeItem;
import net.ccfish.common.web.BaseModel;
import net.ccfish.jvue.autogen.model.JvueApi;
import net.ccfish.jvue.autogen.model.JvuePage;
import net.ccfish.jvue.autogen.model.JvueSegment;
import net.ccfish.jvue.domain.model.JvueRoleItem;
import net.ccfish.jvue.rest.vm.ReqPageApis;
import net.ccfish.jvue.service.JvueApiService;
import net.ccfish.jvue.service.JvuePageService;
import net.ccfish.jvue.service.JvueSegmentService;
import net.ccfish.jvue.service._AbstractService;
import net.ccfish.jvue.service.model.RolePageDetails;

/**
 * API相关
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@RestController
@RequestMapping("page")
@AclResc(id = 5200)
@Api(tags  = "画面管理")
public class JvuePageController implements _BaseController<JvuePage, Integer> {

    @Autowired
    private JvuePageService jvuePageService;
    @Autowired
    private JvueApiService jvueApiService;
    @Autowired
    private JvueSegmentService jvueSegmentService;

    /* (non-Javadoc)
     * @see com.hxxt.admin.rest._BaseController#baseService()
     */
    @Override
    public _AbstractService<JvuePage, Integer> baseService() {
        return this.jvuePageService;
    }
    
    @AclResc(id = 11)
    @ApiOperation(value = "画面名列表")
    @GetMapping("/ext/names")
    public BaseModel<List<CodeItem<Integer>>> names(
            @RequestParam(name = "moduleId", required = false) Integer moduleId) {
        List<JvuePage> pages;
        if (moduleId == null) {
            pages = jvuePageService.getAll();
        } else {
            pages = jvuePageService.findByModule(moduleId);
        }
        List<CodeItem<Integer>> codeList = new ArrayList<>();
        for (JvuePage module: pages) {
            CodeItem<Integer> CodeItem = new CodeItem<>();
            CodeItem.setCode(module.getId());
            CodeItem.setName(module.getName());
            codeList.add(CodeItem);
        }
        return new BaseModel<List<CodeItem<Integer>>>().setData(codeList);
    }

    @ApiOperation(value = "API列表")
    @AclResc(id = 12)
    @GetMapping("/ext/{id}/api")
    public BaseModel<List<JvueApi>> apis(@PathVariable("id") Integer id) {
        List<JvueApi> apis = jvueApiService.findByMemu(id);
        return new BaseModel<List<JvueApi>>().setData(apis);
    }

    @ApiOperation(value = "更新API")
    @AclResc(id = 13)
    @PutMapping("/ext/{id}/api")
    public BaseModel<List<JvueApi>> updateApis(@PathVariable("id") Integer id, @RequestBody ReqPageApis req){
        List<JvueApi> apis = jvueApiService.updateApisByMemu(id, req.getApis());
        return new BaseModel<List<JvueApi>>().setData(apis);
    }

    @ApiOperation(value = "Segment列表")
    @AclResc(id = 14)
    @GetMapping("/ext/{id}/segment")
    public BaseModel<List<JvueSegment>> segments(@PathVariable("id") Integer id) {
        List<JvueSegment> segments = jvueSegmentService.findByPage(id);
        return new BaseModel<List<JvueSegment>>().setData(segments);
    }

    @ApiOperation(value = "画面详情列表")
    @AclResc(id = 15)
    @GetMapping("/ext/pages") 
    public BaseModel<RolePageDetails<JvueRoleItem>> getPages(){
        
        RolePageDetails<JvueRoleItem> jvueRole = jvuePageService.getAllRoleInfo();
        return new BaseModel<RolePageDetails<JvueRoleItem>>().setData(jvueRole);
    }

}
