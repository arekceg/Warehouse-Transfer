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
	@GetMapping("")
	public String showWarehouseDetails(Model model,
	                                   @AuthenticationPrincipal CurrentUser customUser) {
		Warehouse currentWarehouse= warehouseService.findWarehouseByManager(customUser.getUser());
		Long id = currentWarehouse.getId();
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
	@GetMapping("/history")
	public String showWarehouseTransactionHistory(Model model,
                                                  @SessionAttribute("currentWarehouseId") Long id){
		model.addAttribute("outgoingTransfersHistory", transferService.findAllTransfersBySourceWarehouseId(id));
		model.addAttribute("incomingTransfersHistory", transferService.findAllTransfersByDestinationWarehouseId(id));
		return "warehouse/warehouse-history";
	}
}
