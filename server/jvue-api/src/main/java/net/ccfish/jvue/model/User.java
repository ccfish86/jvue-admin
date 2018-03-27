package net.ccfish.jvue.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(length=64)
	private String email;
	
    @JsonProperty(access = Access.WRITE_ONLY)
	@Column(length=128, columnDefinition="char")
	private String password;

	private byte status;

	@Column(nullable=false, length=64)
	private String username;
	
    @Column(nullable=false, length=64)
    private String nickname;
    
    @Column(nullable=false, length=1)
    private byte superUser;
	
    @JsonProperty(access = Access.READ_ONLY)
    @ManyToMany(targetEntity = JvueRole.class, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    @BatchSize(size = 20)
    private Set<JvueRole> roles = new HashSet<>();

	public User() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// 禁止密码等字段的返回
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public byte getSuperUser() {
        return superUser;
    }

    public void setSuperUser(byte superUser) {
        this.superUser = superUser;
    }

    /**
     * @return the roles
     */
    public Set<Integer> getRoles() {
        return roles.stream().map(role->role.getId()).collect(Collectors.toSet());
    }

    public Set<JvueRole> getAuthorities() {
        return roles;
    }
    
    /**
     * @param roles the roles to set
     */
    public void setRoles(Set<JvueRole> roles) {
        this.roles = roles;
    }
}