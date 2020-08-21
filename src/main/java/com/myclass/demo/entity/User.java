package com.myclass.demo.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
@ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	private String password;
	private String fullname;
	private String phone;
	private String address;
	private String avatar;
	@Column(name = "role_id")
	private int roleId;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Role role;
}
