package com.arek.warehousetransfer.transfer;

import com.arek.warehousetransfer.item.Item;
import com.arek.warehousetransfer.item.ItemService;
import com.arek.warehousetransfer.stock.Stock;
import com.arek.warehousetransfer.stock.StockService;
import com.arek.warehousetransfer.stock.StockType;
import com.arek.warehousetransfer.warehouse.Warehouse;
import com.arek.warehousetransfer.warehouse.WarehouseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TransferService {

	// == fields ==
	private TransferRepository transferRepository;
	private ItemService itemService;
	private WarehouseRepository warehouseRepository;
	private StockService stockService;

	// == public methods ==

	public List<Transfer> findAllTransfers() {
		return transferRepository.findAll();
	}

	public List<Transfer> findAllTransfersBySourceWarehouseId(Long id) {
		return transferRepository.findTransfersBySourceWarehouseId(id);
	}

	public List<Transfer> findAllUnacceptedTransfersBySourceWarehouseId(Long id) {
		return transferRepository.findTransfersBySourceWarehouseIdAndIsAccepted(id, false);
	}

	public List<Transfer> findAllTransfersByDestinationWarehouseId(Long id) {
		return transferRepository.findTransfersByDestinationWarehouseId(id);
	}

	public List<Transfer> findAllUnacceptedTransfersByDestinationWarehouseId(Long id) {
		return transferRepository.findTransfersByDestinationWarehouseIdAndIsAccepted(id, false);
	}

	public void saveTransfer(Transfer transfer) {
		transferRepository.save(transfer);
	}

	public void setTransferToAccepted(Long id) {
		transferRepository.setTransferToAccepted(id, LocalDate.now());
	}

	public Transfer findTransferById(Long id) {
		return transferRepository.findById(id).orElse(null);
	}

	public void deleteTransfer(Long id) {
		Transfer transfer = findTransferById(id);
		Warehouse sourceWarehouse = transfer.getSourceWarehouse();
		//1.    Get transfer content
		List<TransferContent> transferContents = transfer.getTransferContents();
		transferContents.forEach(tc -> {
			Item transferItem = tc.getItem();
			int itemAmount = tc.getAmount();
			//2.    Remove reserved stock from source warehouse
			Stock reservedStockToRemove = Stock.of(transferItem, -itemAmount, sourceWarehouse, StockType.RESERVED);
			stockService.updateStockInWarehouse(reservedStockToRemove, false);
			Stock availableStockToFree = Stock.of(transferItem, itemAmount, sourceWarehouse, StockType.AVAILABLE);
			stockService.updateStockInWarehouse(availableStockToFree, false);
		});
		transferRepository.delete(transfer);
	}

	public void acceptTransfer(Long id) {
		Transfer transfer = findTransferById(id);
		if (!transfer.isAccepted()) {
			Warehouse sourceWarehouse = transfer.getSourceWarehouse();
			Warehouse destinationWarehouse = transfer.getDestinationWarehouse();
			//1.    Get transfer content
			List<TransferContent> transferContents = transfer.getTransferContents();
			transferContents.forEach(tc -> {
				Item transferItem = tc.getItem();
				int itemAmount = tc.getAmount();
				//2.    Remove reserved stock from source warehouse
				Stock reservedStockToRemove = Stock.of(transferItem, -itemAmount, sourceWarehouse, StockType.RESERVED);
				stockService.updateStockInWarehouse(reservedStockToRemove, false);
				Stock availableStockToAddToDestinationWarehouse = Stock.of(transferItem, itemAmount, destinationWarehouse, StockType.AVAILABLE);
				stockService.updateStockInWarehouse(availableStockToAddToDestinationWarehouse, false);
			});
			log.info("==================================");
			log.info("Transfer accepted  {}", transfer.getId());
			setTransferToAccepted(id);
		}
//		3.    Add that reserved stock to destination warehouse as available stock
//		          * find corresponding stock in destination warehouse and increase it's available stock
//		          * create a new stock in destination table if necessary

	}
}
