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
	@GetMapping("list/outgoing/{id}")
	public String showAllOutgoingTransfers(Model model,
	                                       @PathVariable Long id) {
		model.addAttribute("transfersType", "outgoing");
		model.addAttribute("transfers", transferService.findAllTransfersBySourceWarehouseId(id));
		return Mappings.TRANSFER_LIST;
	}

	@GetMapping("list/incoming/{id}")
	public String showAllIncomingTransfers(Model model,
	                                       @PathVariable Long id) {

		model.addAttribute("transfersType", "incoming");
		model.addAttribute("transfers", transferService.findAllUnacceptedTransfersByDestinationWarehouseId(id));
		return Mappings.TRANSFER_LIST;
	}

//	@GetMapping("list/all/{id}")
//	public String showAllTransfers(Model model,
//	                               @PathVariable Long id) {
//		Warehouse sourceWarehouse = warehouseService.findWarehouseById(id);
//		model.addAttribute("warehouseStockInfo", stockService.getWarehouseStockInformationByWarehouse(sourceWarehouse));
//		model.addAttribute("outgoingTransfersHistory", transferService.findAllTransfersBySourceWarehouseId(id));
//		model.addAttribute("incomingTransfersHistory", transferService.findAllUnacceptedTransfersByDestinationWarehouseId(id));
//		return "transfer/transfer-all";
//	}

	@GetMapping("new")
	public String createNewTransferForm(Model model,
	                                    @SessionAttribute("currentWarehouseId") String id) {
		Warehouse sourceWarehouse = warehouseService.findWarehouseById(NumberUtils.toLong(id));
		model.addAttribute("warehouseStockInfo", stockService.getWarehouseStockInformationByWarehouse(sourceWarehouse));
		model.addAttribute("sourceWarehouse", sourceWarehouse);
		model.addAttribute("warehouses", warehouseService.findWarehousesWithIdNotEqual(sourceWarehouse.getId()));
		model.addAttribute("transfer", Transfer.emptyTransfer());
		return Mappings.TRANSFER_FORM;
	}

	@GetMapping("details/{id}")
	public String getTransferDetails(@PathVariable Long id,
	                                 Model model) {
		model.addAttribute("transfer", transferService.findTransferById(id));
		return Mappings.TRANSFER_DETAILS;
	}

	@PostMapping("accept")
	public String acceptTransfer(@ModelAttribute("transferId") TransferIdWrapper transferIdWrapper) {
		Long transferId = transferIdWrapper.getId();
		Warehouse destinationWarehouse = transferService.findTransferById(transferId).getDestinationWarehouse();
		transferService.acceptTransfer(transferId);
		return "redirect:/warehouse/";
	}

	@PostMapping("delete")
	public String deleteTransfer(@ModelAttribute("transferId") TransferIdWrapper transferIdWrapper,
	                             HttpServletRequest request){
		String referer = request.getHeader("Referer");
		transferService.deleteTransfer(transferIdWrapper.getId());
		return "redirect:"+referer;
	}


//	@GetMapping("test")
//	@ResponseBody
//	public String test(){
//	}

	@PostMapping("new")
	public String createNewTransfer(HttpServletRequest req,
	                                @ModelAttribute @Valid Transfer transfer,
	                                BindingResult result){
		if (result.hasErrors()) {
			return "redirect:/transfer";
		}
		transferService.saveTransfer(transferService.populateTransferDataFromRequestBody(req, transfer));
		stockService.updateReservedStockFromTransferData(transfer);
		return "redirect:/warehouse/";
	}

}
