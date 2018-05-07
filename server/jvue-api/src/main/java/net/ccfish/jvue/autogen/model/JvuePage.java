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

@Table(name = "jvue.jvue_page")
@ApiModel("JvuePage（画面菜单）")
public class JvuePage extends BaseEntity implements Serializable {
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
     * 模块ID
     */
    @Column(name = "module_id")
    @ApiModelProperty(value ="模块ID",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer moduleId;

    /**
     * 访问路径
     */
    @ApiModelProperty(value ="访问路径",required = false)
    @ColumnType(jdbcType=JdbcType.VARCHAR)
    private String path;

    /**
     * 页面模块
     */
    @ApiModelProperty(value ="页面模块",required = false)
    @ColumnType(jdbcType=JdbcType.VARCHAR)
    private String component;

    /**
     * 画面名
     */
    @ApiModelProperty(value ="画面名",required = false)
    @ColumnType(jdbcType=JdbcType.VARCHAR)
    private String name;

    /**
     * 图标
     */
    @Column(name = "icon_class")
    @ApiModelProperty(value ="图标",required = false)
    @ColumnType(jdbcType=JdbcType.VARCHAR)
    private String iconClass;

    /**
     * 父画面ID
     */
    @Column(name = "parent_id")
    @ApiModelProperty(value ="父画面ID",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer parentId;

    /**
     * 是否有效
     */
    @ApiModelProperty(value ="是否有效",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer enabled;

    /**
     * 菜单里显示
     */
    @Column(name = "show_nav")
    @ApiModelProperty(value ="菜单里显示",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer showNav;

    /**
     * 画面类型
     */
    @ApiModelProperty(value ="画面类型",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer type;

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
     * 获取模块ID
     *
     * @return module_id - 模块ID
     */
    public Integer getModuleId() {
        return moduleId;
    }

    /**
     * 设置模块ID
     *
     * @param moduleId 模块ID
     */
    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * 获取访问路径
     *
     * @return path - 访问路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置访问路径
     *
     * @param path 访问路径
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取页面模块
     *
     * @return component - 页面模块
     */
    public String getComponent() {
        return component;
    }

    /**
     * 设置页面模块
     *
     * @param component 页面模块
     */
    public void setComponent(String component) {
        this.component = component;
    }

    /**
     * 获取画面名
     *
     * @return name - 画面名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置画面名
     *
     * @param name 画面名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取图标
     *
     * @return icon_class - 图标
     */
    public String getIconClass() {
        return iconClass;
    }

    /**
     * 设置图标
     *
     * @param iconClass 图标
     */
    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    /**
     * 获取父画面ID
     *
     * @return parent_id - 父画面ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父画面ID
     *
     * @param parentId 父画面ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    /**
     * 获取菜单里显示
     *
     * @return show_nav - 菜单里显示
     */
    public Integer getShowNav() {
        return showNav;
    }

    /**
     * 设置菜单里显示
     *
     * @param showNav 菜单里显示
     */
    public void setShowNav(Integer showNav) {
        this.showNav = showNav;
    }

    /**
     * 获取画面类型
     *
     * @return type - 画面类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置画面类型
     *
     * @param type 画面类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    public enum FieldEnum {
        ID("id","id"),
		MODULE_ID("moduleId","module_id"),
		PATH("path","path"),
		COMPONENT("component","component"),
		NAME("name","name"),
		ICON_CLASS("iconClass","icon_class"),
		PARENT_ID("parentId","parent_id"),
		ENABLED("enabled","enabled"),
		SHOW_NAV("showNav","show_nav"),
		TYPE("type","type");

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