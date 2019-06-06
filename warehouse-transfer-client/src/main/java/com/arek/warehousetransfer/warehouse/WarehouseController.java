package com.arek.warehousetransfer.warehouse;

import com.arek.warehousetransfer.stock.StockService;
import com.arek.warehousetransfer.transfer.TransferIdWrapper;
import com.arek.warehousetransfer.transfer.TransferService;
import com.arek.warehousetransfer.user.CurrentUser;
import com.arek.warehousetransfer.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("warehouse")
@SessionAttributes("currentWarehouseId")
public class WarehouseController {

	private WarehouseService warehouseService;
	private StockService stockService;
	private TransferService transferService;

	// == WORKS ==
	@GetMapping("") // ID FOR DEBUG PURPOSES
	public String showWarehouseDetails(Model model,
	                                   @AuthenticationPrincipal CurrentUser customUser) {
//                                       @PathVariable Long id){
		Warehouse currentWarehouse= warehouseService.findWarehouseByManager(customUser.getUser());
		Long id = currentWarehouse.getId();
//		Warehouse currentWarehouse= warehouseService.findWarehouseById(id);
		model.addAttribute("currentWarehouseId", id);
		model.addAttribute("transferId", TransferIdWrapper.empty());
		// == WORKS ==
		model.addAttribute("warehouseStockInfo", stockService.getWarehouseStockInformationByWarehouse(currentWarehouse));
        // == WORKS ==
		model.addAttribute("outgoingTransfers", transferService.findAllUnacceptedTransfersBySourceWarehouseId(id));
        // == WORKS ==
		model.addAttribute("incomingTransfers", transferService.findAllUnacceptedTransfersByDestinationWarehouseId(id));
		return "warehouse/warehouse-home";
	}

    // == WORKS ==
	@GetMapping("/history") // ID FOR DEBUG PURPOSES
	public String showWarehouseTransactionHistory(Model model,
//	                                              @AuthenticationPrincipal CurrentUser customUser) {
                                                  @SessionAttribute("currentWarehouseId") Long id){
		model.addAttribute("outgoingTransfersHistory", transferService.findAllTransfersBySourceWarehouseId(id));
		model.addAttribute("incomingTransfersHistory", transferService.findAllTransfersByDestinationWarehouseId(id));
		return "warehouse/warehouse-history";
	}
//	@GetMapping("{id}")
//	public String showWarehouseDetails(Model model,
//	                                   @PathVariable Long id) {
//		Warehouse sourceWarehouse = warehouseService.findWarehouseById(id);
//		model.addAttribute("currentWarehouseId", sourceWarehouse.getId());
//		model.addAttribute("transferId", TransferIdWrapper.empty());
//		model.addAttribute("warehouseStockInfo", stockService.getWarehouseStockInformationByWarehouse(sourceWarehouse));
//		model.addAttribute("outgoingTransfers", transferService.findAllUnacceptedTransfersBySourceWarehouseId(id));
//		model.addAttribute("incomingTransfers", transferService.findAllUnacceptedTransfersByDestinationWarehouseId(id));
//		return "warehouse/warehouse-home";
//	}
//
//	@GetMapping("{id}/history")
//	public String showWarehouseTransactionHistory(Model model,
//	                                              @PathVariable Long id) {
//		model.addAttribute("outgoingTransfersHistory", transferService.findAllTransfersBySourceWarehouseId(id));
//		model.addAttribute("incomingTransfersHistory", transferService.findAllTransfersByDestinationWarehouseId(id));
//		return "warehouse/warehouse-history";
//	}
//
//	@PostMapping("add")
//	public String addWarehouse(@ModelAttribute("warehouse") Warehouse warehouse){
//		User manager = warehouse.getManager();
//		manager.setWarehouse(warehouse);
//		warehouseService.saveWarehouse(warehouse);
//		return "redirect:/admin/";
//	}
}
