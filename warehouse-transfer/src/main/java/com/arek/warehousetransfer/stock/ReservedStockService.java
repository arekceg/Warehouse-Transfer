package com.arek.warehousetransfer.stock;

import com.arek.warehousetransfer.item.ItemService;
import com.arek.warehousetransfer.transfer.Transfer;
import com.arek.warehousetransfer.warehouse.Warehouse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReservedStockService {

	// == fields ==
	private ReservedStockRepository reservedStockRepository;
	private ItemService itemService;

	// == public methods ==
	public ReservedStock findReservedStockByItemIdAndWarehouseId(Long itemId, Long warehouseId) {
		return reservedStockRepository.findByItemIdAndWarehouseId(itemId, warehouseId);
	}

	public void reserveStock(ReservedStock reservedStock) {
		reservedStockRepository.save(reservedStock);
	}


	public void updateReservedStock(int amount, Long itemId, Long warehouseId) {
		if (findReservedStockByItemIdAndWarehouseId(itemId, warehouseId) != null) {
			reservedStockRepository.changeReservedStockOfItemInWarehouse(amount, itemId, warehouseId);
		} else {
			reserveStock(ReservedStock.of(itemService.findItemById(itemId), amount));
		}
	}

	public void updateReservedStockFromTransferData(Transfer transfer){
		Long sourceWarehouseId = transfer.getSourceWarehouse().getId();
        transfer.getTransferContents().forEach(t -> {
        	updateReservedStock(t.getAmount(),t.getItem().getId(),sourceWarehouseId);
        });

	}

}
