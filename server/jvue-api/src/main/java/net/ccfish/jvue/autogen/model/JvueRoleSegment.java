package net.ccfish.jvue.autogen.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.ccfish.common.entity.BaseEntity;
import tk.mybatis.mapper.annotation.ColumnType;

@Table(name = "jvue.jvue_role_segment")
@ApiModel("JvueRoleSegment（角色对应画面片段）")
public class JvueRoleSegment extends BaseEntity implements Serializable {
    /**
     * 角色ID
     */
    @Id
    @Column(name = "role_id", updatable = false)
    @ApiModelProperty(value ="角色ID",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer roleId;

    /**
     * 画面片段ID
     */
    @Id
    @Column(name = "segment_id", updatable = false)
    @ApiModelProperty(value ="画面片段ID",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer segmentId;

    /**
     * ID
     */
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id", insertable = false, updatable = false)
    @ApiModelProperty(value ="ID",required = false)
    @ColumnType(jdbcType=JdbcType.BIGINT)
    private Long id;

    private static final long serialVersionUID = 1L;

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取画面片段ID
     *
     * @return segment_id - 画面片段ID
     */
    public Integer getSegmentId() {
        return segmentId;
    }

    /**
     * 设置画面片段ID
     *
     * @param segmentId 画面片段ID
     */
    public void setSegmentId(Integer segmentId) {
        this.segmentId = segmentId;
    }

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    public enum FieldEnum {
        ROLE_ID("roleId","role_id"),
		SEGMENT_ID("segmentId","segment_id"),
		ID("id","id");

        private String javaFieldName;

        private String dbFieldName;

        FieldEnum(String javaFieldName, String dbFieldName) {
            this.javaFieldName = javaFieldName;
            this.dbFieldName = dbFieldName;
        }

        public String javaFieldName() {
            return javaFieldName;
        }

        public String dbFieldName() {
            return dbFieldName;
        }
    }
}