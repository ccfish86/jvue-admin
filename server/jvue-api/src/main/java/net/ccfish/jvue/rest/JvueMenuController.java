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
import net.ccfish.common.web.BaseModel;
import net.ccfish.jvue.model.JvueApi;
import net.ccfish.jvue.model.JvueMenu;
import net.ccfish.jvue.model.JvueSegment;
import net.ccfish.jvue.rest.vm.CodeDto;
import net.ccfish.jvue.rest.vm.ReqMenuApis;
import net.ccfish.jvue.service.JvueApiService;
import net.ccfish.jvue.service.JvueMenuService;
import net.ccfish.jvue.service.JvueSegmentService;
import net.ccfish.jvue.service._AbstractService;
import net.ccfish.jvue.service.acl.AclResc;

/**
 * API相关
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@RestController
@RequestMapping("menu")
@AclResc(id = 5200, code = "JvueMenu", name = "画面管理", homePage = "")
@Api(tags  = "画面管理")
public class JvueMenuController implements _BaseController<JvueMenu, Integer> {

    @Autowired
    private JvueMenuService jvueMenuService;
    @Autowired
    private JvueApiService jvueApiService;
    @Autowired
    private JvueSegmentService jvueSegmentService;

    /* (non-Javadoc)
     * @see net.ccfish.jvue.rest._BaseController#baseService()
     */
    @Override
    public _AbstractService<JvueMenu, Integer> baseService() {
        return this.jvueMenuService;
    }
    
    @AclResc(id = 11, code = "names", name = "名字列表")
    @GetMapping("/ext/names")
    public BaseModel<List<CodeDto<Integer>>> names(
            @RequestParam(name = "moduleId", required = false) Integer moduleId) {
        List<JvueMenu> menus;
        if (moduleId == null) {
            menus = jvueMenuService.getAll();
        } else {
            menus = jvueMenuService.findByModule(moduleId);
        }
        List<CodeDto<Integer>> codeList = new ArrayList<>();
        for (JvueMenu module: menus) {
            CodeDto<Integer> codeDto = new CodeDto<>();
            codeDto.setCode(module.getId());
            codeDto.setName(module.getName());
            codeList.add(codeDto);
        }
        return new BaseModel<List<CodeDto<Integer>>>().setData(codeList);
    }

    @ApiOperation(value = "API列表")
    @AclResc(id = 12, code = "apis", name = "API列表")
    @GetMapping("/ext/api/{id}")
    public BaseModel<List<JvueApi>> apis(@PathVariable("id") Integer id) {
        List<JvueApi> apis = jvueApiService.findByMemu(id);
        return new BaseModel<List<JvueApi>>().setData(apis);
    }

    @ApiOperation(value = "API列表")
    @AclResc(id = 13, code = "updateApis", name = "API列表")
    @PutMapping("/ext/api/{id}")
    public BaseModel<List<JvueApi>> updateApis(@PathVariable("id") Integer id, @RequestBody ReqMenuApis req){
        List<JvueApi> apis = jvueApiService.updateApisByMemu(id, req.getApis());
        return new BaseModel<List<JvueApi>>().setData(apis);
    }

    @ApiOperation(value = "Segment列表")
    @AclResc(id = 14, code = "segments", name = "Segment列表")
    @GetMapping("/ext/segment/{id}")
    public BaseModel<List<JvueSegment>> segments(@PathVariable("id") Integer id) {
        List<JvueSegment> segments = jvueSegmentService.findByMenu(id);
        return new BaseModel<List<JvueSegment>>().setData(segments);
    }

}
