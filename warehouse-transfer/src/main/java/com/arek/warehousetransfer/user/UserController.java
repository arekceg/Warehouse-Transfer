package com.arek.warehousetransfer.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

	private UserService userService;

	@GetMapping("allnowarehouse")
	public List<User> getUsersWithoutWarehouses(){
		return userService.findUsersWithoutWarehouses();
	}

	@PostMapping("add")
	public ResponseEntity<User> addUser(@RequestBody User user){
		userService.saveUser(user);
		return new ResponseEntity(user,HttpStatus.CREATED);
	}
}
