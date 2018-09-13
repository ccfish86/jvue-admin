package net.ccfish.jvue.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import net.ccfish.jvue.autogen.model.JvueDept;

public class JvueExDept extends JvueDept {

	@JsonInclude(value = Include.NON_EMPTY)
	private List<JvueExDept> childs;

	public List<JvueExDept> getChilds() {
		return childs;
	}

	public void setChilds(List<JvueExDept> childs) {
		this.childs = childs;
	}
}
