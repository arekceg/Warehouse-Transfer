package com.arek.warehousetransfer.user;

import com.arek.warehousetransfer.transfer.TransferIdWrapper;
import com.arek.warehousetransfer.transfer.TransferService;
import com.arek.warehousetransfer.warehouse.Warehouse;
import com.arek.warehousetransfer.warehouse.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("admin")
public class AdminController {

	private WarehouseService warehouseService;
	private TransferService transferService;

	@GetMapping("")
	public String adminPanel(){
		return "admin/admin-home";
	}

	@GetMapping("add-item")
	public String addItem(){
		return "redirect:/item/add/";
	}

	@GetMapping("all-transactions")
	public String allTransfers(Model model){
		model.addAttribute("transferId", TransferIdWrapper.empty());
		model.addAttribute("transfers", transferService.findAllTransfers());
		return "admin/admin-all-transfers";
	}
}
