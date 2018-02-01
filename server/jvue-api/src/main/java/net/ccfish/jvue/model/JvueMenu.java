package net.ccfish.jvue.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the jvue_menu database table.
 * 
 */
@Entity
@Table(name="jvue_menu")
@NamedQuery(name="JvueMenu.findAll", query="SELECT j FROM JvueMenu j")
public class JvueMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=64)
	private String component;

	private byte enabled;

	@Column(name="icon_class", length=64)
	private String iconClass;

	@Column(name="module_id", nullable=false)
	private Integer moduleId;

	@Column(length=64)
	private String name;

	@Column(length=64)
	private String path;

	private byte type;

	//bi-directional many-to-one association to JvueMenu
	@ManyToOne
	@JoinColumn(name="parent_id")
	private JvueMenu jvueMenu;

	//bi-directional many-to-one association to JvueMenu
	@OneToMany(mappedBy="jvueMenu")
	private List<JvueMenu> jvueMenus;

	public JvueMenu() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComponent() {
		return this.component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public String getIconClass() {
		return this.iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public Integer getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public byte getType() {
		return this.type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public JvueMenu getJvueMenu() {
		return this.jvueMenu;
	}

	public void setJvueMenu(JvueMenu jvueMenu) {
		this.jvueMenu = jvueMenu;
	}

	public List<JvueMenu> getJvueMenus() {
		return this.jvueMenus;
	}

	public void setJvueMenus(List<JvueMenu> jvueMenus) {
		this.jvueMenus = jvueMenus;
	}

	public JvueMenu addJvueMenus(JvueMenu jvueMenus) {
		getJvueMenus().add(jvueMenus);
		jvueMenus.setJvueMenu(this);

		return jvueMenus;
	}

	public JvueMenu removeJvueMenus(JvueMenu jvueMenus) {
		getJvueMenus().remove(jvueMenus);
		jvueMenus.setJvueMenu(null);

		return jvueMenus;
	}

}