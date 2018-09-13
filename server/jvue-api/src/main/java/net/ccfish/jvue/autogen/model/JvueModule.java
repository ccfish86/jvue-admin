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

@Table(name = "jvue_module")
@ApiModel("JvueModule（模块）")
public class JvueModule extends BaseEntity implements Serializable {
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
     * 模块名
     */
    @ApiModelProperty(value ="模块名",required = false)
    @ColumnType(jdbcType=JdbcType.VARCHAR)
    private String name;

    /**
     * 是否有效
     */
    @ApiModelProperty(value ="是否有效",required = false)
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
     * 获取模块名
     *
     * @return name - 模块名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置模块名
     *
     * @param name 模块名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取是否有效
     *
     * @return enabled - 是否有效
     */
    public Integer getEnabled() {
        return enabled;
    }

    /**
     * 设置是否有效
     *
     * @param enabled 是否有效
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