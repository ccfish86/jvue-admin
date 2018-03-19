package net.ccfish.jvue.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the jvue_module database table.
 * 
 */
@Entity
@Table(name="jvue_module")
@NamedQuery(name="JvueModule.findAll", query="SELECT j FROM JvueModule j")
public class JvueModule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false)
	private byte enabled = 0;

	@Column(nullable=false, length=64)
	private String name;

	public JvueModule() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}