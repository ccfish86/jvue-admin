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

@Table(name = "jvue_role")
@ApiModel("JvueRole（角色）")
public class JvueRole extends BaseEntity implements Serializable {
    /**
     * ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id", insertable = false, updatable = false)
    @ApiModelProperty(value ="ID",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer id;

    /**
     * 角色名
     */
    @ApiModelProperty(value ="角色名",required = false)
    @ColumnType(jdbcType=JdbcType.VARCHAR)
    private String name;

    /**
     * 是否启用
     */
    @ApiModelProperty(value ="是否启用",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer enabled;

    private static final long serialVersionUID = 1L;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取角色名
     *
     * @return name - 角色名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名
     *
     * @param name 角色名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取是否启用
     *
     * @return enabled - 是否启用
     */
    public Integer getEnabled() {
        return enabled;
    }

    /**
     * 设置是否启用
     *
     * @param enabled 是否启用
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public enum FieldEnum {
        ID("id","id"),
		NAME("name","name"),
		ENABLED("enabled","enabled");

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