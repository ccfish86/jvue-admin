package net.ccfish.jvue.autogen.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;

@Table(name = "jvue_dept")
@ApiModel("JvueDept（）")
public class JvueDept implements Serializable {
    /**
     * 部门表ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id", insertable = false, updatable = false)
    @ApiModelProperty(value ="部门表ID",required = false)
    private Integer id;

    /**
     * 部门编码
     */
    @ApiModelProperty(value ="部门编码",required = false)
    private String code;

    /**
     * 部门名
     */
    @ApiModelProperty(value ="部门名",required = false)
    private String name;

    /**
     * 父部门编码
     */
    @Column(name = "parent_code")
    @ApiModelProperty(value ="父部门编码",required = false)
    private String parentCode;

    /**
     * 级别
     */
    @ApiModelProperty(value ="级别",required = false)
    private Integer level;

    private static final long serialVersionUID = 1L;

    /**
     * 获取部门表ID
     *
     * @return id - 部门表ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置部门表ID
     *
     * @param id 部门表ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取部门编码
     *
     * @return code - 部门编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置部门编码
     *
     * @param code 部门编码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取部门名
     *
     * @return name - 部门名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置部门名
     *
     * @param name 部门名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取父部门编码
     *
     * @return parent_code - 父部门编码
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * 设置父部门编码
     *
     * @param parentCode 父部门编码
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    /**
     * 获取级别
     *
     * @return level - 级别
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置级别
     *
     * @param level 级别
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    public enum FieldEnum {
        ID("id","id"),
		CODE("code","code"),
		NAME("name","name"),
		PARENT_CODE("parentCode","parent_code"),
		LEVEL("level","level");

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