package net.ccfish.jvue.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the jvue_segment database table.
 * 
 */
@Entity
@Table(name="jvue_segment")
@NamedQuery(name="JvueSegment.findAll", query="SELECT j FROM JvueSegment j")
public class JvueSegment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(name="menu_id", nullable=false)
	private Integer menuId;
	
    @Column(name="segment_id", nullable=false)
    private Integer segmentId;

	@Column(length=64)
	private String name;

	public JvueSegment() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(Integer segmentId) {
        this.segmentId = segmentId;
    }

    public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}