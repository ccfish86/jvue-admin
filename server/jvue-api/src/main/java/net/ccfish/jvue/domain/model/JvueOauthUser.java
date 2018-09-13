package net.ccfish.jvue.domain.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.*;

@Table(name = "jvue_oauth_user")
@ApiModel("JvueOauthUser（）")
public class JvueOauthUser implements Serializable {
	@Id
	@GeneratedValue(generator = "JDBC")
	@Column(name = "id", insertable = false, updatable = false)
	@ApiModelProperty(value = "", required = false)
	private Long id;

	@Column(name = "user_id")
	@ApiModelProperty(value = "", required = false)
	private Long userId;

	/**
	 * 用户类型
	 */
	@Column(name = "account_type")
	@ApiModelProperty(value = "用户类型", required = false)
	private String accountType;

	/**
	 * 账号
	 */
	@Column(name = "account_id")
	@ApiModelProperty(value = "账号", required = false)
	private String accountId;

	private static final long serialVersionUID = 1L;

	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return user_id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取用户类型
	 *
	 * @return account_type - 用户类型
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * 设置用户类型
	 *
	 * @param accountType 用户类型
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * 获取账号
	 *
	 * @return account_id - 账号
	 */
	public String getAccountId() {
		return accountId;
	}

	/**
	 * 设置账号
	 *
	 * @param accountId 账号
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public enum FieldEnum {
		ID("id", "id"), USER_ID("userId", "user_id"), ACCOUNT_TYPE("accountType", "account_type"),
		ACCOUNT_ID("accountId", "account_id");

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