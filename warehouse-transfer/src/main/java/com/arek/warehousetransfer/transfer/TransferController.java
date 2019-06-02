package com.arek.warehousetransfer.transfer;

import com.arek.warehousetransfer.stock.ReservedStockService;
import com.arek.warehousetransfer.stock.StockService;
import com.arek.warehousetransfer.utils.Mappings;
import com.arek.warehousetransfer.warehouse.WarehouseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("transfer")
@AllArgsConstructor
@Slf4j
public final class TransferController {

	private final StockService stockService;
	private final WarehouseService warehouseService;
	private final TransferService transferService;
	private final ReservedStockService reservedStockService;


	@GetMapping("")
	public String showAllTransfers(Model model) {
		model.addAttribute("transfers", transferService.findAllTransfers());
		return Mappings.TRANSFER_LIST;
	}

	@GetMapping("{sourceId}")
	public String createNewTransferForm(@PathVariable Long sourceId,
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


	@PostMapping(
			value = "{sourceId}",
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String createNewTransfer(@RequestBody HashMap<String,String> formMap,
	                                @ModelAttribute @Valid Transfer transfer,
	                                BindingResult result) {
		if (result.hasErrors()) {
			return Mappings.TRANSFER_FORM;
		}
//		log.info("==================================");
//		formMap.forEach((k,v)-> log.info(k + " : " + v));
		transferService.saveTransfer(transferService.populateTransferDataFromRequestMap(formMap, transfer));
		reservedStockService.updateReservedStockFromTransferData(transfer);
		return "redirect:/" + Mappings.TRANSFER + "/";
	}


}
