package com.arek.warehousetransfer.user;

import com.arek.warehousetransfer.transfer.TransferIdWrapper;
import com.arek.warehousetransfer.transfer.TransferService;
import com.arek.warehousetransfer.user.Role.RoleService;
import com.arek.warehousetransfer.utils.Mappings;
import com.arek.warehousetransfer.warehouse.Warehouse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@AllArgsConstructor
@RequestMapping("admin")
@Slf4j
public class AdminController {

	//	private WarehouseService warehouseService;
	private TransferService transferService;
	private RoleService roleService;
	private UserService userService;

	@GetMapping("")
	public String adminPanel() {
		return "admin/admin-home";
	}

    // == WORKS ==
	@GetMapping("add-item/{choice}")
	public String addItem(@PathVariable String choice) {
		return "redirect:/stock/add/" + choice;
	}

	@GetMapping("all-transactions")
	public String allTransfers(Model model) {
		model.addAttribute("transferId", TransferIdWrapper.empty());
		model.addAttribute("transfers", transferService.findAllTransfers());
		return "admin/admin-all-transfers";
	}

	// === WORKS ===
	@GetMapping("add-user")
	public String addUser(User user,
	                      Model model) {
		model.addAttribute("roles", roleService.findAllRoles());
		model.addAttribute("user", User.empty());
		return "admin/admin-add-user";
	}

	// === WORKS ===
	@GetMapping("add-warehouse")
	public String addWarehouseForm(Model model) {
		model.addAttribute("warehouse", Warehouse.empty());
		model.addAttribute("users", userService.findUsersWithoutWarehouses());
		return "admin/admin-add-warehouse";
	}

	// === WORKS ===
	@PostMapping("add-user")
	public String addUserPost(@ModelAttribute("user") User user) {
		final String uri = Mappings.BACKEND_ADRESS + "/user/add";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(
				uri,
				user,
				User.class
		);
		return "redirect:/admin/";
	}

    // == WORKS ==
	@PostMapping("add-warehouse")
	public String addWarehouse(@ModelAttribute("warehouse") Warehouse warehouse){
		final String uri = Mappings.BACKEND_ADRESS+"/warehouse/add";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForObject(
				uri,
				warehouse,
				Warehouse.class
		);
		return "redirect:/admin/";
	}
}
