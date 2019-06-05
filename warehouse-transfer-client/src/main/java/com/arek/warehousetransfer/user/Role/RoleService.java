package com.arek.warehousetransfer.user.Role;

import com.arek.warehousetransfer.utils.Mappings;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class RoleService {

//	private RoleRepository roleRepository;

	public Set<Role> findAllRoles() {
		final String url = Mappings.BACKEND_ADRESS + "/roles/all";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Set<Role>> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Set<Role>>() {
				}
		);
		return response.getBody();
	}

}
