package com.arek.warehousetransfer.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
	//== fields ==

	private UserRepository userRepository;

	public List<User> findAllUsers(){
		return userRepository.findAll();
	}
}
