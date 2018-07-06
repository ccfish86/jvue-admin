package net.ccfish.jvue.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.ccfish.common.OrderByUtils;
import net.ccfish.common.acl.AclResc;
import net.ccfish.common.entity.CodeItem;
import net.ccfish.common.web.BaseModel;
import net.ccfish.common.web.PageParam;
import net.ccfish.common.web.PagedModel;
import net.ccfish.jvue.autogen.model.JvueDept;
import net.ccfish.jvue.domain.model.JvueDeptNodeItem;
import net.ccfish.jvue.domain.model.JvueExDept;
import net.ccfish.jvue.service.JvueDeptService;

/**
 * 部门
 *
 * @author yuangui
 * @version 1.0
 * @since 2018-07-04
 */
@RestController
@RequestMapping("dept")
@AclResc(id = 4200)
@Api(tags  = "部门管理")
public class JvueDeptController {
	
    @Autowired
    private JvueDeptService deptService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("")
	@AclResc(id = 1)
	@ApiOperation(value = "列表")
	public PagedModel<JvueExDept> list(@ModelAttribute PageParam pageParam) {
		String orderBy = OrderByUtils.toString(pageParam.getSort(), pageParam.getDirection());
		List<JvueExDept> result = deptService.search(pageParam.getPage(), 
				pageParam.getPageSize(), orderBy);
		return PagedModel.from((Page<JvueExDept>) result);
	}
	

    @PostMapping("")
    @AclResc(id = 3)
    @ApiOperation(value = "追加")
    public  BaseModel<JvueDept> add(@RequestBody JvueDept dept) {
        deptService.add(dept);
        return new BaseModel<JvueDept>().setData(dept);
    }
    
    @PutMapping("{id}")
    @AclResc(id = 4)
    @ApiOperation(value = "更新")
    public  BaseModel<Integer> update(@PathVariable("id") Integer id, @RequestBody JvueDept data) {
    	deptService.update(id, data);
        return new BaseModel<Integer>().setData(id);
    }
    
    @DeleteMapping("{id}")
    @AclResc(id = 5)
    @ApiOperation(value = "删除")
    public  BaseModel<Integer> delete(@PathVariable("id") Integer id) {
    	deptService.delete(id);
        return new BaseModel<Integer>().setData(id);
    }
    
	@ApiOperation(value = "父部门列表")
	@AclResc(id = 12)
	@GetMapping("/ext/parentCodes")
	public BaseModel<List<CodeItem<String>>> parentCodes() {
		List<JvueExDept> depts = deptService.parents();
		List<CodeItem<String>> codeList = new ArrayList<>();
		for (JvueExDept dept : depts) {
			CodeItem<String> codeItem = new CodeItem<>();
			codeItem.setCode(dept.getCode());
			codeItem.setName(dept.getName());
			codeList.add(codeItem);
			for (JvueExDept cdept : dept.getChilds()) {
				CodeItem<String> cCodeItem = new CodeItem<>();
				cCodeItem.setCode(cdept.getCode());
				cCodeItem.setName(dept.getName() + cdept.getName());
				codeList.add(cCodeItem);
			}
		}
		return BaseModel.ok(codeList);
	}

	@ApiOperation(value = "部门树")
	@AclResc(id = 13)
	@GetMapping("/ext/tree")
	public BaseModel<List<JvueDeptNodeItem>> getDeptTree() {
		List<JvueDeptNodeItem> depts = deptService.getTree();
		return BaseModel.ok(depts);
	}
}
