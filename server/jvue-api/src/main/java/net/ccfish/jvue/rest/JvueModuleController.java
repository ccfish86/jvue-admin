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
import io.swagger.annotations.ApiOperation;
import net.ccfish.common.acl.AclResc;
import net.ccfish.common.entity.CodeItem;
import net.ccfish.common.web.BaseModel;
import net.ccfish.jvue.autogen.model.JvueModule;
import net.ccfish.jvue.service.JvueModuleService;
import net.ccfish.jvue.service._AbstractService;

/**
 * Module相关
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@RestController
@RequestMapping("/api/module")
@AclResc(id = 5000)
@Api(tags = "模块管理")
public class JvueModuleController implements _BaseController<JvueModule, Integer> {

    @Autowired
    private JvueModuleService jvueModuleService;

    /* (non-Javadoc)
     * @see com.hxxt.admin.rest._BaseController#baseService()
     */
    @Override
    public _AbstractService<JvueModule, Integer> baseService() {
        return this.jvueModuleService;
    }

    @ApiOperation(value = "名字列表")
    @AclResc(id = 11)
    @GetMapping("/ext/names") 
    public BaseModel<List<CodeItem<Integer>>> names(){
        List<JvueModule> modules = jvueModuleService.getAll();
        List<CodeItem<Integer>> codeList = new ArrayList<>();
        for (JvueModule module: modules) {
            CodeItem<Integer> CodeItem = new CodeItem<>();
            CodeItem.setCode(module.getId());
            CodeItem.setName(module.getName());
            codeList.add(CodeItem);
        }
        return new BaseModel<List<CodeItem<Integer>>>().setData(codeList);
    }
//
//    @Override
//    public BaseModel<Integer> delete(@PathParam("id") Integer id) {
//        baseService().delete(id);
//        return new BaseModel<Integer>().setData(id);
//    }
}
