package net.ccfish.jvue.autogen.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.ccfish.common.entity.BaseEntity;
import tk.mybatis.mapper.annotation.ColumnType;

@Table(name = "jvue_role_api")
@ApiModel("JvueRoleApi（角色对应接口权限）")
public class JvueRoleApi extends BaseEntity implements Serializable {
    /**
     * 角色ID
     */
    @Id
    @Column(name = "role_id", updatable = false)
    @ApiModelProperty(value ="角色ID",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer roleId;

    /**
     * 接口ID
     */
    @Id
    @Column(name = "api_id", updatable = false)
    @ApiModelProperty(value ="接口ID",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer apiId;

    /**
     * ID
     */
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
     * 获取接口ID
     *
     * @return api_id - 接口ID
     */
    public Integer getApiId() {
        return apiId;
    }

    /**
     * 设置接口ID
     *
     * @param apiId 接口ID
     */
    public void setApiId(Integer apiId) {
        this.apiId = apiId;
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
		API_ID("apiId","api_id"),
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