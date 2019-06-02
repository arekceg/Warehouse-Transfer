package com.arek.warehousetransfer.user;

import com.arek.warehousetransfer.validators.AdminOrManager;
import com.arek.warehousetransfer.warehouse.Warehouse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
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

	@NotNull /*used instead of @NotEmpty because this list can be empty, can't be null*/
	@OneToMany(mappedBy = "manager")
	private List<Warehouse> warehouses;

	@NotNull
	private boolean isAdmin;

}
