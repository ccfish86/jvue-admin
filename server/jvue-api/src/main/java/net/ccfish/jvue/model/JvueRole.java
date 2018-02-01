package net.ccfish.jvue.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the jvue_role database table.
 * 
 */
@Entity
@Table(name="jvue_role")
@NamedQuery(name="JvueRole.findAll", query="SELECT j FROM JvueRole j")
public class JvueRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=64)
	private String name;

	public JvueRole() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}