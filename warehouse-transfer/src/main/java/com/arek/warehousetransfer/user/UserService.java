package com.arek.warehousetransfer.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
	//== fields ==

	private UserRepository userRepository;
}
