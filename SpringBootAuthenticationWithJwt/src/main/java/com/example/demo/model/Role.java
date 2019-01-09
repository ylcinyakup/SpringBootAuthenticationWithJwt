package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Role implements GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2463023937327545296L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(name="name", length = 60 ,columnDefinition="ENUM('ROLE_ADMIN','ROLE_USER')" )
    private RoleName name;

	@Override
	public String getAuthority() {
		return name.toString();
	}

}
