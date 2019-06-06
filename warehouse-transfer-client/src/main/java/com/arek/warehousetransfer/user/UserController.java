package com.arek.warehousetransfer.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

	private UserService userService;
//
//	@PostMapping("add")
//	public String addUser(@ModelAttribute("user") User user){
//		userService.saveUser(user);
//		return "redirect:/admin/";
//	}
}
