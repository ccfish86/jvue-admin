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

@Table(name = "jvue.jvue_api")
@ApiModel("JvueApi（画面接口）")
public class JvueApi extends BaseEntity implements Serializable {
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
     * 画面ID
     */
    @Column(name = "page_id")
    @ApiModelProperty(value ="画面ID",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer pageId;

    /**
     * 接口编码
     */
    @Column(name = "api_code")
    @ApiModelProperty(value ="接口编码",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer apiCode;

    /**
     * 画面接口名
     */
    @ApiModelProperty(value ="画面接口名",required = false)
    @ColumnType(jdbcType=JdbcType.VARCHAR)
    private String name;

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
     * 获取接口编码
     *
     * @return api_code - 接口编码
     */
    public Integer getApiCode() {
        return apiCode;
    }

    /**
     * 设置接口编码
     *
     * @param apiCode 接口编码
     */
    public void setApiCode(Integer apiCode) {
        this.apiCode = apiCode;
    }

    /**
     * 获取画面接口名
     *
     * @return name - 画面接口名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置画面接口名
     *
     * @param name 画面接口名
     */
    public void setName(String name) {
        this.name = name;
    }

    public enum FieldEnum {
        ID("id","id"),
		PAGE_ID("pageId","page_id"),
		API_CODE("apiCode","api_code"),
		NAME("name","name");

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