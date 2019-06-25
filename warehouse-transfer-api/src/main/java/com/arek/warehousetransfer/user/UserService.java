package com.arek.warehousetransfer.user;

import com.arek.warehousetransfer.user.Role.Role;
import com.arek.warehousetransfer.user.Role.RoleRepository;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
	//== fields ==

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public User findUserById(Long id){
		return userRepository.findById(id).orElse(null);
	}

	public List<User> findAllUsers(){
		return userRepository.findAll();
	}

	public List<User> findUsersWithoutWarehouses(){
		return userRepository.findAllByWarehouseNull();
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
