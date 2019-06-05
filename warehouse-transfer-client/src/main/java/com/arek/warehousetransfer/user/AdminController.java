package com.arek.warehousetransfer.user;

import com.arek.warehousetransfer.transfer.TransferIdWrapper;
import com.arek.warehousetransfer.transfer.TransferService;
import com.arek.warehousetransfer.user.Role.RoleService;
import com.arek.warehousetransfer.warehouse.Warehouse;
import com.arek.warehousetransfer.warehouse.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("admin")
public class AdminController {

	private WarehouseService warehouseService;
	private TransferService transferService;
	private RoleService roleService;
	private UserService userService;

	@GetMapping("")
	public String adminPanel(){
		return "admin/admin-home";
	}

	@GetMapping("add-item/{choice}")
	public String addItem(@PathVariable String choice) {
		return "redirect:/stock/add/"+choice;
	}

	@GetMapping("all-transactions")
	public String allTransfers(Model model){
		model.addAttribute("transferId", TransferIdWrapper.empty());
		model.addAttribute("transfers", transferService.findAllTransfers());
		return "admin/admin-all-transfers";
	}

	@GetMapping("add-user")
	public String addUser(User user,
	                      Model model){
		model.addAttribute("roles", roleService.findAllRoles());
		model.addAttribute("user", User.empty());
		return "admin/admin-add-user";
	}

	@GetMapping("add-warehouse")
	public String addWarehouseForm(Model model){
		model.addAttribute("warehouse", Warehouse.empty());
		model.addAttribute("users",userService.findUsersWithoutWarehouses());
		return "admin/admin-add-warehouse";
	}
}
