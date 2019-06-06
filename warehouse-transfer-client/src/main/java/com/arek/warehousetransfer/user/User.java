package com.arek.warehousetransfer.user;

import com.arek.warehousetransfer.user.Role.Role;
import com.arek.warehousetransfer.warehouse.Warehouse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

//@Entity
@Getter
@Setter
@NoArgsConstructor
//@Table(name = "users")
public class User {

	// == fields ==
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@NotBlank
//	@Column(unique = true, nullable = false)
	private String username;

	@NotBlank
//	@Column(nullable = false)
	private String password;

	private int enabled;

//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
//			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

//	@NotNull
//	@OneToOne(cascade = CascadeType.ALL)
//	@JsonIgnore
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
