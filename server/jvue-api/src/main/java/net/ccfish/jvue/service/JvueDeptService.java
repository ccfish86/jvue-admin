package net.ccfish.jvue.service;

import java.util.List;

import net.ccfish.jvue.autogen.model.JvueDept;
import net.ccfish.jvue.domain.model.JvueDeptNodeItem;
import net.ccfish.jvue.domain.model.JvueExDept;

public interface JvueDeptService {

	int update(Integer id, JvueDept data);

	int delete(Integer id);

	List<JvueExDept> parents();

	List<JvueExDept> search(int page, int pageSize, String orderBy);

	int add(JvueDept dept);

	List<JvueDeptNodeItem> getTree();

}
