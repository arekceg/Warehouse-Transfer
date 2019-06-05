package com.arek.warehousetransfer;

import com.arek.warehousetransfer.user.CurrentUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String test(@AuthenticationPrincipal CurrentUser customUser) {
		if(customUser==null){
			return "redirect:/login";
		}
		if (customUser.getUser().getName().equalsIgnoreCase("admin")) {
			return "redirect:/admin";
		}
		return "redirect:/warehouse/";
	}
}
