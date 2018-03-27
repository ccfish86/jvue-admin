package net.ccfish.jvue.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the jvue_role database table.
 * 
 */
@Entity
@Table(name="jvue_role_api")
@NamedQuery(name="JvueRoleApi.findAll", query="SELECT j FROM JvueRoleApi j")
public class JvueRoleApi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@ManyToOne
    @JoinColumn(name="role_id")
	private JvueRole role;
	
    @ManyToOne
    @JoinColumn(name="api_id")
    private JvueApi api;

	public JvueRoleApi() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public JvueRole getRole() {
        return role;
    }

    public void setRole(JvueRole role) {
        this.role = role;
    }

    public JvueApi getApi() {
        return api;
    }

    public void setApi(JvueApi api) {
        this.api = api;
    }

}