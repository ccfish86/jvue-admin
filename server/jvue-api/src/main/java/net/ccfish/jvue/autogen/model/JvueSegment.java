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

@Table(name = "jvue.jvue_segment")
@ApiModel("JvueSegment（画面片断）")
public class JvueSegment extends BaseEntity implements Serializable {
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
     * 片段ID，画面内唯一
     */
    @Column(name = "segment_code")
    @ApiModelProperty(value ="片段ID，画面内唯一",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer segmentCode;

    /**
     * 画面片段名
     */
    @ApiModelProperty(value ="画面片段名",required = false)
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
     * 获取片段ID，画面内唯一
     *
     * @return segment_code - 片段ID，画面内唯一
     */
    public Integer getSegmentCode() {
        return segmentCode;
    }

    /**
     * 设置片段ID，画面内唯一
     *
     * @param segmentCode 片段ID，画面内唯一
     */
    public void setSegmentCode(Integer segmentCode) {
        this.segmentCode = segmentCode;
    }

    /**
     * 获取画面片段名
     *
     * @return name - 画面片段名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置画面片段名
     *
     * @param name 画面片段名
     */
    public void setName(String name) {
        this.name = name;
    }

    public enum FieldEnum {
        ID("id","id"),
		PAGE_ID("pageId","page_id"),
		SEGMENT_CODE("segmentCode","segment_code"),
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