package com.arek.warehousetransfer.user;

import com.arek.warehousetransfer.user.Role.Role;
import com.arek.warehousetransfer.warehouse.Warehouse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class User {

	// == fields ==
	private Long id;

	private String name;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	private int enabled;

	private Set<Role> roles;

	private Warehouse warehouse;

	public User(@NotBlank String username, @NotBlank String password, Set<Role> roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public static User empty() {
		return new User();
	}

}
