/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import net.ccfish.common.web.BaseModel;
import net.ccfish.jvue.model.JvueModule;
import net.ccfish.jvue.rest.vm.CodeDto;
import net.ccfish.jvue.service.JvueModuleService;
import net.ccfish.jvue.service._AbstractService;
import net.ccfish.jvue.service.acl.AclResc;

/**
 * Module相关
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@RestController
@RequestMapping("module")
@AclResc(id = 5000, code = "JvueModule", name = "模块管理", homePage = "")
@Api(tags = "模块管理")
public class JvueModuleController implements _BaseController<JvueModule, Integer> {

    @Autowired
    private JvueModuleService jvueModuleService;

    /* (non-Javadoc)
     * @see net.ccfish.jvue.rest._BaseController#baseService()
     */
    @Override
    public _AbstractService<JvueModule, Integer> baseService() {
        return this.jvueModuleService;
    }
    
    @AclResc(id = 11, code = "names", name = "名字列表")
    @GetMapping("/ext/names") 
    public BaseModel<List<CodeDto<Integer>>> names(){
        List<JvueModule> modules = jvueModuleService.getAll();
        List<CodeDto<Integer>> codeList = new ArrayList<>();
        for (JvueModule module: modules) {
            CodeDto<Integer> codeDto = new CodeDto<>();
            codeDto.setCode(module.getId());
            codeDto.setName(module.getName());
            codeList.add(codeDto);
        }
        return new BaseModel<List<CodeDto<Integer>>>().setData(codeList);
    }
//
//    @Override
//    public BaseModel<Integer> delete(@PathParam("id") Integer id) {
//        baseService().delete(id);
//        return new BaseModel<Integer>().setData(id);
//    }
}
