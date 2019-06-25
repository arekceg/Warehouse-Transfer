package com.arek.warehousetransfer.transfer;

import com.arek.warehousetransfer.stock.StockService;
import com.arek.warehousetransfer.warehouse.Warehouse;
import com.arek.warehousetransfer.warehouse.WarehouseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("transfer")
@AllArgsConstructor
@Slf4j
public final class TransferController {

	private final StockService stockService;
	private final WarehouseService warehouseService;
	private final TransferService transferService;

	//REST UPDATED
	@GetMapping("list/outgoing/{id}/unaccepted")
	public List<Transfer> getUnacceptedOutgoingTransfers(@PathVariable Long id) {
		return transferService.findAllUnacceptedTransfersBySourceWarehouseId(id);
	}

	//REST UPDATED
	@GetMapping("list/incoming/{id}/unaccepted")
	public List<Transfer> getUnacceptedIncomingTransfers(@PathVariable Long id) {
		return transferService.findAllUnacceptedTransfersByDestinationWarehouseId(id);
	}

	//REST UPDATED
	@GetMapping("list/outgoing/{id}/all")
	public List<Transfer> showAllOutgoingTransfers(@PathVariable Long id) {
		return transferService.findAllTransfersBySourceWarehouseId(id);
	}

	//REST UPDATED
	@GetMapping("list/incoming/{id}/all")
	public List<Transfer> showAllIncomingTransfers(@PathVariable Long id) {
		return transferService.findAllTransfersByDestinationWarehouseId(id);
	}

	//REST UPDATED
	@GetMapping("{id}")
	public Transfer findTransferById(@PathVariable Long id) {
		return transferService.findTransferById(id);
	}

	//REST UPDATED
	@GetMapping("all")
	public List<Transfer> getAllTransfers() {
		return transferService.findAllTransfers();
	}

	//REST
	@PostMapping("accept")
	public String acceptTransfer(@ModelAttribute("transferId") TransferIdWrapper transferIdWrapper) {
		Long transferId = transferIdWrapper.getId();
		return "redirect:/warehouse/";
	}

	//REST
	@PostMapping("delete")
	public String deleteTransfer(@ModelAttribute("transferId") TransferIdWrapper transferIdWrapper,
	                             HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		transferService.deleteTransfer(transferIdWrapper.getId());
		return "redirect:" + referer;
	}


	// REST UPDATED
	@PostMapping("new")
	public ResponseEntity<String> createNewTransfer(@RequestBody Transfer transfer) {

		stockService.updateReservedStockFromTransferData(transfer);
		transferService.saveTransfer(transfer);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}


	@PutMapping("{id}/accept")
	public ResponseEntity<String> acceptTransfer(@PathVariable Long id) {
		transferService.acceptTransfer(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@DeleteMapping("{id}/delete")
	public ResponseEntity<String> deleteTransfer(@PathVariable Long id) {
		transferService.deleteTransfer(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
