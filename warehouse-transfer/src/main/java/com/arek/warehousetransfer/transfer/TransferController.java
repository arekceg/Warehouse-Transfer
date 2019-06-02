package com.arek.warehousetransfer.transfer;

import com.arek.warehousetransfer.stock.StockService;
import com.arek.warehousetransfer.utils.Mappings;
import com.arek.warehousetransfer.warehouse.WarehouseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

	@GetMapping("{sourceId}/{destinationId}")
	public String createNewTransferForm(@PathVariable Long sourceId,
	                                    @PathVariable Long destinationId,
	                                    Model model) {
		model.addAttribute("sourceWarehouse", warehouseService.findWarehouseById(sourceId));
		model.addAttribute("stockList", stockService.findStockByWarehouseId(sourceId));
		model.addAttribute("warehouses", warehouseService.findWarehousesWithIdNotEqual(sourceId));
		model.addAttribute("transfer", Transfer.emptyTransfer());
		return Mappings.TRANSFER_FORM;
	}

	@GetMapping("details/{id}")
	public String getTransferDetails(@PathVariable Long id,
	                                 Model model){
		model.addAttribute("transfer", transferService.findTransferById(id));
		return Mappings.TRANSFER_DETAILS;
	}


	@PostMapping("{sourceId}/{destinationId}")
	public String createNewTransfer(HttpServletRequest req,
	                                @ModelAttribute @Valid Transfer transfer,
	                                BindingResult result) {
		if (result.hasErrors()) {
			return Mappings.TRANSFER_FORM;
		}
		transferService.saveTransfer(transferService.populateTransferDataFromRequestParameters(req, transfer));
		return "redirect:/" + Mappings.TRANSFER + "/";
	}


}
