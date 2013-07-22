package net.mothman.persistence.model.user;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "account")
@NamedQuery(name = "account.byUsername", query = "from Account a where a.username = :username")
@XmlRootElement
public class Account implements Serializable {
	
	private static final long serialVersionUID = -4758199195921007926L;

	public static final Account ACCOUNT = new Account("anonymous"); /* ;) */

	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private boolean enabled;
	private Set<Role> roles = new HashSet<Role>();

	public Account() {}

	public Account(String username) {
		this.username = username;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(Long id) {
		this.id = id;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Transient
	public String getFullName() {
		return firstName + " " + lastName;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "enabled")
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@XmlTransient
	@ManyToMany(fetch = FetchType.EAGER) // EAGER
	@JoinTable(name = "account_role", 
		joinColumns = { @JoinColumn(name = "account_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "role_id") }
	)
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String toString() {
		return username;
	}
}
