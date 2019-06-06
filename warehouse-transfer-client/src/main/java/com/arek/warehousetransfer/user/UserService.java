package com.arek.warehousetransfer.user;

import com.arek.warehousetransfer.utils.Mappings;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
	//== fields ==

//	private final UserRepository userRepository;
//	private final RoleRepository roleRepository;
//	private final BCryptPasswordEncoder passwordEncoder;
//
//	public List<User> findAllUsers(){
//		return userRepository.findAll();
//	}

	public List<User> findUsersWithoutWarehouses() {
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

	public User findByUserLogin(String username) {
		final String uri = Mappings.BACKEND_ADRESS + "/user/" + username;
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(uri, User.class);
//		return userRepository.findByUsername(username);
	}
//
//	public void saveUser(User user) {
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		user.setEnabled(1);
//		Role userRole = roleRepository.findFirstByName("ROLE_USER");
//		user.setRoles(new HashSet<>(Lists.newArrayList(userRole)));
//		userRepository.save(user);
//	}
}
