package net.ccfish.jvue.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the jvue_api database table.
 * 
 */
@Entity
@Table(name="jvue_api")
@NamedQuery(name="JvueApi.findAll", query="SELECT j FROM JvueApi j")
public class JvueApi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="api_id", nullable=false)
	private Integer apiId;

	@Column(name="menu_id", nullable=false)
	private Integer menuId;

	@Column(nullable=false, length=64)
	private String name;

	public JvueApi() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}