package net.ccfish.jvue.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.github.pagehelper.PageHelper;

import net.ccfish.jvue.autogen.dao.JvueDeptMapper;
import net.ccfish.jvue.autogen.model.JvueDept;
import net.ccfish.jvue.domain.dao.JvueExDeptMapper;
import net.ccfish.jvue.domain.model.JvueDeptNodeItem;
import net.ccfish.jvue.domain.model.JvueExDept;
import net.ccfish.jvue.service.JvueDeptService;

@Service
@Transactional
public class JvueDeptServiceImpl implements JvueDeptService {

	@Autowired
	private JvueDeptMapper deptMapper;
	
	@Autowired
	private JvueExDeptMapper exDeptMapper;

	@Override
	public int update(Integer id, JvueDept data) {
		JvueDept dept = deptMapper.selectByPrimaryKey(id);
		Assert.notNull(dept, "请选择部门");
		
		data.setId(id);
		if (dept.getCode().length() < 3) {
			dept.setCode(StringUtils.leftPad(dept.getCode(), 3, '0'));
		}
		
		// TODO 部门更新时，子部门或者对应的用户也需要做对应的处理
		
		return deptMapper.updateByPrimaryKeySelective(data);
	}

	@Override
	public int delete(Integer id) {
		JvueDept dept = deptMapper.selectByPrimaryKey(id);
		Assert.notNull(dept, "请选择部门");
		
		JvueDept record = new JvueDept();
		record.setParentCode(dept.getCode());
		
		int child = deptMapper.selectCount(record);
		
		Assert.state(child == 0, "不能删除包含子部门的部门");
		return deptMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<JvueExDept> parents() {
		return exDeptMapper.search();
	}

	@Override
	public List<JvueExDept> search(int page, int pageSize, String orderBy) {
		PageHelper.startPage(page, pageSize, orderBy);
		return exDeptMapper.search();
	}

	@Override
	public int add(JvueDept dept) {
		if (dept.getLevel() == null) {
			int level = 1;
			if (!StringUtils.isEmpty(dept.getParentCode())) {
				JvueDept record = new JvueDept();
				record.setCode(dept.getParentCode());
				JvueDept pdept = deptMapper.selectOne(record);
				if (pdept != null) {
					level = pdept.getLevel() + 1;
				}
			} 
			dept.setLevel(level);
		}
		if (dept.getCode().length() < 3) {
			dept.setCode(StringUtils.leftPad(dept.getCode(), 3, '0'));
		}
		return deptMapper.insertSelective(dept);
	}

	@Override
	public List<JvueDeptNodeItem> getTree() {
		return exDeptMapper.findTree();
	}

}
