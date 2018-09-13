/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.ccfish.common.acl.AclResc;
import net.ccfish.common.entity.CodeItem;
import net.ccfish.common.web.BaseModel;
import net.ccfish.jvue.autogen.model.JvueRole;
import net.ccfish.jvue.rest.vm.ReqRoleGrant;
import net.ccfish.jvue.service.JvueRoleService;
import net.ccfish.jvue.service._AbstractService;
import net.ccfish.jvue.service.model.RolePageDetails;

/**
 * Role相关
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@RestController
@RequestMapping("/api/role")
@AclResc(id = 5300)
@Api(tags  = "角色管理")
public class JvueRoleController implements _BaseController<JvueRole, Integer> {

    @Autowired
    private JvueRoleService jvueRoleService;

    /* (non-Javadoc)
     * @see com.hxxt.admin.rest._BaseController#baseService()
     */
    @Override
    public _AbstractService<JvueRole, Integer> baseService() {
        return this.jvueRoleService;
    }
    
    @ApiOperation(value = "更新是否有效")
    @AclResc(id = 11)
    @PatchMapping("/{id}/{enabled}") 
    public BaseModel<JvueRole> patchEnabled(@PathVariable("id") Integer id,
            @PathVariable("enabled") Integer enabled){
        
        JvueRole jvueRole = jvueRoleService.updateEnabled(id, enabled);
        return new BaseModel<JvueRole>().setData(jvueRole);
    }

    @ApiOperation(value = "角色权限")
    @AclResc(id = 12)
    @GetMapping("/ext/{id}/grant") 
    public BaseModel<RolePageDetails<Integer>> getRoleInfo(@PathVariable("id") Integer id){
        
        RolePageDetails<Integer> jvueRole = jvueRoleService.getRoleInfo(id);
        return new BaseModel<RolePageDetails<Integer>>().setData(jvueRole);
    }
    @ApiOperation(value = "权限名字列表")
    @AclResc(id = 13)
    @GetMapping("/ext/names") 
    public BaseModel<List<CodeItem<Integer>>> names(){
        
        List<JvueRole> jvueRoleList = jvueRoleService.getAll();
        List<CodeItem<Integer>> codeList = new ArrayList<>();
        for (JvueRole role: jvueRoleList) {
            CodeItem<Integer> CodeItem = new CodeItem<>();
            CodeItem.setCode(role.getId());
            CodeItem.setName(role.getName());
            codeList.add(CodeItem);
        }
        return BaseModel.ok(codeList);
    }
    
    @ApiOperation(value = "授予角色权限")
    @AclResc(id = 14)
    @PutMapping("/ext/{id}/grant") 
    public BaseModel<Integer> grant(@PathVariable("id") Integer id, @RequestBody ReqRoleGrant roleGrant){
        
        int upd = jvueRoleService.grant(id, roleGrant.getModuleId(), roleGrant.getPageRoles());
        return BaseModel.ok(upd);
    }

}
