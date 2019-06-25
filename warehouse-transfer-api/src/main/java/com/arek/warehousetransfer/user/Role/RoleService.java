package com.arek.warehousetransfer.user.Role;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RoleService {

	private RoleRepository roleRepository;

	public Set<Role> findAllRoles(){
		return roleRepository.findAllRoles();
	}

}
