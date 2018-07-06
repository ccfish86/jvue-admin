package net.ccfish.jvue.domain.dao;

import java.util.List;

import net.ccfish.jvue.domain.model.JvueDeptNodeItem;
import net.ccfish.jvue.domain.model.JvueExDept;

/**
 * 
 *
 * @author yuangui
 * @version 1.0
 * @since 2018-07-04
 */
public interface JvueExDeptMapper {

	List<JvueExDept> search();
	
	List<JvueDeptNodeItem> findTree();
}
