package net.ccfish.jvue.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import net.ccfish.common.entity.CodeItem;

public class JvueDeptNodeItem extends CodeItem<String> {

	@JsonInclude(value = Include.NON_EMPTY)
	private List<JvueDeptNodeItem> childs;

	public List<JvueDeptNodeItem> getChilds() {
		return childs;
	}

	public void setChilds(List<JvueDeptNodeItem> childs) {
		this.childs = childs;
	}
	
}
