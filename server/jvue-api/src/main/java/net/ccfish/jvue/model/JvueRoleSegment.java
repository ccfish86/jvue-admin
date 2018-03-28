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
@Table(name="jvue_role_segment")
@NamedQuery(name="JvueRoleSegment.findAll", query="SELECT j FROM JvueRoleSegment j")
public class JvueRoleSegment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@ManyToOne
    @JoinColumn(name="role_id")
	private JvueRole role;
	
    @ManyToOne
    @JoinColumn(name="segment_id")
    private JvueSegment segment;

	public JvueRoleSegment() {
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

    public JvueSegment getSegment() {
        return segment;
    }

    public void setSegment(JvueSegment segment) {
        this.segment = segment;
    }

}