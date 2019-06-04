package com.arek.warehousetransfer.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
@SessionAttributes("loggedUser")
public class UserController {

	private UserService userService;


	@GetMapping("login")
	public String loginScreen(Model model) {
		model.addAttribute("userList", userService.findAllUsers());
		model.addAttribute("user", User.empty());
		return "user/login";

	}

	@GetMapping("home/{id}")
	public String homeScreen(@PathVariable Long id) {


		return "user/home";
	}


	@PostMapping("login")
	public String logged(@ModelAttribute User user,
	                     HttpServletRequest request) {
		WebUtils.setSessionAttribute(request, "loggedUser", user);
//		model.addAttribute("loggedUser",user);
		return "redirect:/home";
	}
}
