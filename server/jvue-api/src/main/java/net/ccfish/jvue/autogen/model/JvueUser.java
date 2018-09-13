package net.ccfish.jvue.autogen.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.ccfish.common.entity.BaseEntity;
import tk.mybatis.mapper.annotation.ColumnType;

@Table(name = "jvue_user")
@ApiModel("JvueUser（用户）")
public class JvueUser extends BaseEntity implements Serializable {
    /**
     * 用户ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    @Column(name = "id", insertable = false, updatable = false)
    @ApiModelProperty(value ="用户ID",required = false)
    @ColumnType(jdbcType=JdbcType.BIGINT)
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value ="用户名",required = false)
    @ColumnType(jdbcType=JdbcType.VARCHAR)
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value ="密码",required = false)
    @ColumnType(jdbcType=JdbcType.VARCHAR)
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty(value ="邮箱",required = false)
    @ColumnType(jdbcType=JdbcType.VARCHAR)
    private String email;

    /**
     * 部门编码
     */
    @Column(name = "dept_code")
    @ApiModelProperty(value ="部门编码",required = false)
    private String deptCode;
    /**
     * 状态
     */
    @ApiModelProperty(value ="状态",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer status;

    /**
     * 昵称
     */
    @ApiModelProperty(value ="昵称",required = false)
    @ColumnType(jdbcType=JdbcType.VARCHAR)
    private String nickname;

    /**
     * 超级用户
     */
    @Column(name = "super_user")
    @ApiModelProperty(value ="超级用户",required = false)
    @ColumnType(jdbcType=JdbcType.INTEGER)
    private Integer superUser;

    private static final long serialVersionUID = 1L;

    /**
     * 获取用户ID
     *
     * @return id - 用户ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置用户ID
     *
     * @param id 用户ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取部门编码
     *
     * @return dept_code - 部门编码
     */
    public String getDeptCode() {
        return deptCode;
    }

    /**
     * 设置部门编码
     *
     * @param deptCode 部门编码
     */
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
    
    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取超级用户
     *
     * @return super_user - 超级用户
     */
    public Integer getSuperUser() {
        return superUser;
    }

    /**
     * 设置超级用户
     *
     * @param superUser 超级用户
     */
    public void setSuperUser(Integer superUser) {
        this.superUser = superUser;
    }

    public enum FieldEnum {
        ID("id","id"),
		USERNAME("username","username"),
		PASSWORD("password","password"),
		EMAIL("email","email"),
		STATUS("status","status"),
		NICKNAME("nickname","nickname"),
		SUPER_USER("superUser","super_user");

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