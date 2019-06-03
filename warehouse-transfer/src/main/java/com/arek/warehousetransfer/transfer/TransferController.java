package com.arek.warehousetransfer.transfer;

import com.arek.warehousetransfer.stock.Stock;
import com.arek.warehousetransfer.stock.StockService;
import com.arek.warehousetransfer.stock.StockType;
import com.arek.warehousetransfer.utils.Mappings;
import com.arek.warehousetransfer.warehouse.Warehouse;
import com.arek.warehousetransfer.warehouse.WarehouseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("transfer")
@AllArgsConstructor
@Slf4j
public final class TransferController {

	private final StockService stockService;
	private final WarehouseService warehouseService;
	private final TransferService transferService;

	@GetMapping("")
	public String showAllTransfers(Model model) {
		model.addAttribute("transfers", transferService.findAllTransfers());
		return Mappings.TRANSFER_LIST;
	}

	@GetMapping("{sourceId}")
	public String createNewTransferForm(@PathVariable Long sourceId,
	                                    Model model) {
		Warehouse sourceWarehouse = warehouseService.findWarehouseById(sourceId);
		List<Stock> availableStock = stockService.getFullStockListByWarehouseIdAndStockType(sourceId, StockType.AVAILABLE);
		List<Stock> reservedStock = stockService.getFullStockListByWarehouseIdAndStockType(sourceId, StockType.RESERVED);
		List<Stock> totalStock = stockService.calculateTotalStock(availableStock,reservedStock);
//		WarehouseStockInformation stockInformation = stockService.getWarehouseStockInformationByWarehouse(sourceWarehouse);
		model.addAttribute("warehouseStockInfo", stockService.getWarehouseStockInformationByWarehouse(sourceWarehouse));
		model.addAttribute("sourceWarehouse", sourceWarehouse);
//		model.addAttribute("availableStock", availableStock);
//		model.addAttribute("reservedStock", reservedStock);
//		model.addAttribute("totalStock", totalStock);
		model.addAttribute("warehouses", warehouseService.findWarehousesWithIdNotEqual(sourceId));
		model.addAttribute("transfer", Transfer.emptyTransfer());
		return Mappings.TRANSFER_FORM;
	}

	@GetMapping("details/{id}")
	public String getTransferDetails(@PathVariable Long id,
	                                 Model model) {
		model.addAttribute("transfer", transferService.findTransferById(id));
		return Mappings.TRANSFER_DETAILS;
	}

//	@GetMapping("test")
//	@ResponseBody
//	public String test(){
//	}

	@PostMapping(value = "{sourceId}")
	public String createNewTransfer(HttpServletRequest req,
	                                @ModelAttribute @Valid Transfer transfer,
	                                BindingResult result,
	                                @PathVariable Long sourceId) {
		if (result.hasErrors()) {
			return "redirect:/transfer/"+sourceId.toString();
		}
		transferService.saveTransfer(transferService.populateTransferDataFromRequestBody(req, transfer));
		stockService.updateReservedStockFromTransferData(transfer);
		return "redirect:/" + Mappings.TRANSFER + "/";
	}

}
