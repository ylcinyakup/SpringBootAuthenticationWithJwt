package com.example.demo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "USER", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
		@UniqueConstraint(columnNames = { "email" }) })
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id", columnDefinition = "int(11)")
	private Long userId;

	@NotBlank
	@Size(max = 40)
	@Column(name = "NAME")
	private String name;

	@NotBlank
	@Size(max = 15)
	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@NaturalId
	@NotBlank
	@Size(max = 40)
	@Email
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ENABLED")
	@Type(type = "yes_no")
	private boolean enabled = false;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID") })
	private List<Role> roles = new ArrayList<Role>();

	public User(String name, String username, String email, String password) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

}
