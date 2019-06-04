package com.arek.warehousetransfer.user;

import com.arek.warehousetransfer.warehouse.Warehouse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

	// == fields ==
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@NotBlank
	@Column(unique = true, nullable = false)
	private String login;

	@NotBlank
	@Column(nullable = false)
	private String password;

//	@NotNull
	@OneToOne
	private Warehouse warehouse;

	@NotNull
	private boolean isAdmin;

	public static User empty() {
		return new User();
	}
}
