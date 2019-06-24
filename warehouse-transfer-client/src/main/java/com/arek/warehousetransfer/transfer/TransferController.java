package com.arek.warehousetransfer.transfer;

import com.arek.warehousetransfer.stock.StockService;
import com.arek.warehousetransfer.utils.Mappings;
import com.arek.warehousetransfer.warehouse.Warehouse;
import com.arek.warehousetransfer.warehouse.WarehouseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("transfer")
@AllArgsConstructor
@Slf4j
public final class TransferController {

	private final StockService stockService;
	private final WarehouseService warehouseService;
	private final TransferService transferService;




    // == WORKS ==
	@GetMapping("new")
	public String createNewTransferForm(Model model,
	                                    @SessionAttribute("currentWarehouseId") String id) {
		// == UPDATED ==
		Warehouse sourceWarehouse = warehouseService.findWarehouseById(NumberUtils.toLong(id));
		// == WORKS ==
		model.addAttribute("warehouseStockInfo", stockService.getWarehouseStockInformationByWarehouse(sourceWarehouse));
		model.addAttribute("sourceWarehouse", sourceWarehouse);
		// == WORKS ==
		model.addAttribute("warehouses", warehouseService.findWarehousesWithIdNotEqual(sourceWarehouse.getId()));
		model.addAttribute("transfer", Transfer.emptyTransfer());
		return Mappings.TRANSFER_FORM;
	}

    // == WORKS ==
	@GetMapping("details/{id}")
	public String getTransferDetails(@PathVariable Long id,
	                                 Model model) {
		model.addAttribute("transfer", transferService.findTransferById(id));
		return Mappings.TRANSFER_DETAILS;
	}

    // == WORKS ==
	@PostMapping("accept")
	public String acceptTransfer(@ModelAttribute("transferId") TransferIdWrapper transferIdWrapper) {
		Long transferId = transferIdWrapper.getId();
		transferService.acceptTransfer(transferId);
		return "redirect:/warehouse/";
	}

    // == WORKS ==
	@PostMapping("delete")
	public String deleteTransfer(@ModelAttribute("transferId") TransferIdWrapper transferIdWrapper,
	                             HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		transferService.deleteTransfer(transferIdWrapper.getId());
		return "redirect:" + referer;
	}


    // == WORKS ==
	@PostMapping("new")
	public String createNewTransfer(HttpServletRequest req,
	                                @ModelAttribute @Valid Transfer transfer,
	                                BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/transfer";
		}

		Transfer populatedTransfer = transferService.populateTransferDataFromRequestBody(req,transfer);

		final String uri = Mappings.BACKEND_ADRESS + "/transfer/new";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.postForEntity(
				uri,
				populatedTransfer,
				Transfer.class
		);
		return "redirect:/warehouse/";
	}

}
