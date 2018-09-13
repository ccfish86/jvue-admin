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

@Table(name = "jvue_role_page")
@ApiModel("JvueRolePage（角色对应画面）")
public class JvueRolePage extends BaseEntity implements Serializable {
    /**
     * 角色ID
     */
    @Id
    @Column(name = "role_id", updatable = false)
    @ApiModelProperty(value ="角色ID",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer roleId;

    /**
     * 画面ID
     */
    @Id
    @Column(name = "page_id", updatable = false)
    @ApiModelProperty(value ="画面ID",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer pageId;

    /**
     * 角色对应画面
     */
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id", insertable = false, updatable = false)
    @ApiModelProperty(value ="角色对应画面",required = false)
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
     * 获取画面ID
     *
     * @return page_id - 画面ID
     */
    public Integer getPageId() {
        return pageId;
    }

    /**
     * 设置画面ID
     *
     * @param pageId 画面ID
     */
    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    /**
     * 获取角色对应画面
     *
     * @return id - 角色对应画面
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置角色对应画面
     *
     * @param id 角色对应画面
     */
    public void setId(Long id) {
        this.id = id;
    }

    public enum FieldEnum {
        ROLE_ID("roleId","role_id"),
		PAGE_ID("pageId","page_id"),
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