package com.arek.warehousetransfer.warehouse;

import com.arek.warehousetransfer.stock.StockService;
import com.arek.warehousetransfer.transfer.TransferIdWrapper;
import com.arek.warehousetransfer.transfer.TransferService;
import com.arek.warehousetransfer.user.CurrentUser;
import com.arek.warehousetransfer.user.User;
import com.arek.warehousetransfer.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("warehouse")
@SessionAttributes("currentWarehouseId")
public class WarehouseController {

	private WarehouseService warehouseService;
	private StockService stockService;
	private TransferService transferService;
	private UserService userService;

	@GetMapping("")
	public String showWarehouseDetails(Model model,
	                                   @AuthenticationPrincipal CurrentUser customUser) {
		Warehouse currentWarehouse= warehouseService.findWarehouseByManagerId(customUser.getUser().getId());
		Long currentWarehouseId = currentWarehouse.getId();
		model.addAttribute("currentWarehouseId", currentWarehouseId);
		model.addAttribute("transferId", TransferIdWrapper.empty());
		model.addAttribute("warehouseStockInfo", stockService.getWarehouseStockInformationByWarehouse(currentWarehouse));
		model.addAttribute("outgoingTransfers", transferService.findAllUnacceptedTransfersBySourceWarehouseId(currentWarehouseId));
		model.addAttribute("incomingTransfers", transferService.findAllUnacceptedTransfersByDestinationWarehouseId(currentWarehouseId));
		return "warehouse/warehouse-home";
	}

	@GetMapping("history")
	public String showWarehouseTransactionHistory(Model model,
	                                              @AuthenticationPrincipal CurrentUser customUser) {
		Warehouse currentWarehouse= warehouseService.findWarehouseByManagerId(customUser.getUser().getId());
		Long currentWarehouseId = currentWarehouse.getId();
		model.addAttribute("outgoingTransfersHistory", transferService.findAllTransfersBySourceWarehouseId(currentWarehouseId));
		model.addAttribute("incomingTransfersHistory", transferService.findAllTransfersByDestinationWarehouseId(currentWarehouseId));
		return "warehouse/warehouse-history";
	}

	//REST
	@GetMapping("user/{id}")
	public Warehouse getWarehouseByMangerId(@PathVariable Long id){
		return warehouseService.findWarehouseByManagerId(id);
	}
	//REST
	@GetMapping("{id}")
	public Warehouse getWarehouse(@PathVariable Long id){
		return warehouseService.findWarehouseById(id);
	}

	@GetMapping("all")
	public List<Warehouse> getAllWarehouses(){
		return warehouseService.findAllWarehouses();
	}

	@GetMapping("stockinformation/{id}")
	public WarehouseStockInformation getStockInformation(@PathVariable Long id){
		Warehouse warehouse = warehouseService.findWarehouseById(id);
		return stockService.getWarehouseStockInformationByWarehouse(warehouse);
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

	//REST POST
	@PostMapping("add")
	public ResponseEntity addWarehouse(@RequestBody Warehouse warehouse){
		User manager = userService.findUserById(warehouse.getManager().getId());
		manager.setWarehouse(warehouse);
		warehouseService.saveWarehouse(warehouse);
		return new ResponseEntity(HttpStatus.CREATED);
	}
}
