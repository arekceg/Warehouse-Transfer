package com.arek.warehousetransfer.user;

import com.arek.warehousetransfer.user.Role.Role;
import com.arek.warehousetransfer.user.Role.RoleRepository;
import com.arek.warehousetransfer.utils.Mappings;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {
	//== fields ==

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public List<User> findAllUsers(){
		return userRepository.findAll();
	}

	public List<User> findUsersWithoutWarehouses(){
		final String url = Mappings.BACKEND_ADRESS + "/user/allnowarehouse";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<User>> response = restTemplate.exchange(
				url,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<User>>() {
				}
		);
		return response.getBody();
	}

	public User findByUserLogin(String username){
		return userRepository.findByUsername(username);
	}

	public void saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(1);
		Role userRole = roleRepository.findFirstByName("ROLE_USER");
		user.setRoles(new HashSet<>(Lists.newArrayList(userRole)));
		userRepository.save(user);
	}
}
