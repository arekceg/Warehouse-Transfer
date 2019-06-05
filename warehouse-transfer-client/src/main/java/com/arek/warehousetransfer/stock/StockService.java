package com.arek.warehousetransfer.stock;

import com.arek.warehousetransfer.item.Item;
import com.arek.warehousetransfer.item.ItemService;
import com.arek.warehousetransfer.transfer.Transfer;
import com.arek.warehousetransfer.warehouse.Warehouse;
import com.arek.warehousetransfer.warehouse.WarehouseService;
import com.arek.warehousetransfer.warehouse.WarehouseStockInformation;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class StockService {

	// == fields ==

	private StockRepository stockRepository;
	private ItemService itemService;
	private WarehouseService warehouseService;

	// == public methods ==
	// == general methods for working with stock ==

	public List<Stock> calculateTotalStock(List<Stock> availableStockList, List<Stock> reservedStockList) {
		List<Stock> totalStockList = new ArrayList<>();
		for (int i = 0; i < availableStockList.size(); i++) {
			Stock availableStock = availableStockList.get(i);
			Stock reservedStock = reservedStockList.get(i);
			totalStockList.add(Stock.of(availableStock.getItem(),
					availableStock.getItemStock() + reservedStock.getItemStock(),
					availableStock.getWarehouse(), StockType.TOTAL));
		}
		return totalStockList;
	}

	public void saveStock(Stock stock) {
		List<Stock> stockListOfWarehouse = stock.getWarehouse().getStocks();
		stockRepository.save(stock);
	}


	public List<Stock> findAllStocks() {
		return stockRepository.findAll();
	}

	public int sumItemStockByItemIdAndWarehouseId(Long itemId, Long warehouseId, StockType stockType) {
		return stockRepository.sumItemStockByItemIdAndWarehouseId(itemId, warehouseId, stockType);
	}


//	public Stock findStockByItemIdAndWarehouseId(Long itemId, Long warehouseId, StockType stockType) {
//		return stockRepository.findStockByItemIdAndWarehouseIdAndStockType(itemId, warehouseId, stockType);
//	}

	public List<Stock> findStockByWarehouseId(Long id, StockType stockType) {
		return stockRepository.findByWarehouseIdAndStockType(id, stockType);
	}

//	public Map<Item, Integer> getStockMapFromWarehouse(Long id) {
//		return findStockByWarehouseId(id, StockType.AVAILABLE).stream()
//				.collect(Collectors.toMap(Stock::getItem, Stock::getItemStock));
//	}
//
//	public Map<Warehouse, Integer> getItemStockMapByItemId(Long id) {
//		return stockRepository.findByItemId(id).stream()
//				.collect(Collectors.toMap(Stock::getWarehouse, Stock::getItemStock));
//	}
//
//	public Map<Item, Map<Warehouse, Integer>> getFullStockListByItem() {
//		itemService.findAllItems();
//		List<Item> allItems = itemService.findAllItems();
//		return allItems.stream()
//				.collect(Collectors.toMap(i -> i, i -> getItemStockMapByItemId(i.getId())));
//
//	}

	// == methods for working with reserved and available stock ==



	public void updateStockInWarehouse(int amount, Item item, Warehouse warehouse, StockType stockType, boolean creatingTransfer) {
//		Stock stock = Stock.of(item,amount,warehouse,stockType);
		if (stockRepository.findStockByItemIdAndWarehouseIdAndStockType(item.getId(), warehouse.getId(), stockType) != null) {
			stockRepository.changeStockOfItemInWarehouse(amount, item.getId(), warehouse.getId(), stockType.toString());
		} else {
			stockRepository.save(Stock.of(item, amount, warehouse, stockType));
		}
		if (creatingTransfer) {
			stockRepository.changeStockOfItemInWarehouse(-amount, item.getId(), warehouse.getId(), StockType.AVAILABLE.toString());
		}
	}


	public void updateReservedStockFromTransferData(Transfer transfer) {
		transfer.getTransferContents().forEach(t -> {
			updateStockInWarehouse(t.getAmount(), t.getItem(), transfer.getSourceWarehouse(),StockType.RESERVED, true);
		});

	}

	public List<Long> listUniqueItemsIdInWarehouse(Long warehouseId) {
		return Lists.newArrayList(stockRepository.findItemsIdsByWarehouseId(warehouseId).stream()
				.sorted()
				.collect(Collectors.toList()));
	}

	// returns a list of stock where every item in warehouse is included
	// even if it's available or reserved stock is 0
	public List<Stock> getFullStockListByWarehouseIdAndStockType(Long warehouseId, StockType stockType) {
		// unique ItemID list is created by converting a Set of itemIDs to a list
		List<Long> uniqueItemIdList = listUniqueItemsIdInWarehouse(warehouseId);
		List<Stock> stockList = findStockByWarehouseId(warehouseId, stockType);
		List<Stock> resultList = Lists.newArrayList();
		uniqueItemIdList.forEach(id -> {
			resultList.add(Stock.of(itemService.findItemById(id),
					0, warehouseService.findWarehouseById(warehouseId), stockType));
		});
		//check if unique ItemID has some stock assigned to it
		//if so, add to result list
		for (int i = 0; i < resultList.size(); i++) {
			Long itemId = resultList.get(i).getItem().getId();
			Stock foundStock = findStockFromListByItemId(stockList, itemId);
			if (foundStock != null) resultList.set(i, foundStock);
		}
		return resultList;
	}

	private Stock findStockFromListByItemId(List<Stock> stockList, Long id) {
		return stockList.stream()
				.filter(stock -> stock.getItem().getId().equals(id))
				.findFirst().orElse(null);
	}

//	public List<Stock> getTotal(Long itemId, Long warehouseId){
//		return stockRepository.getTotalStockByItemIdAndWarehouseId();
//	}

	public WarehouseStockInformation getWarehouseStockInformationByWarehouse(Warehouse warehouse) {
		List<Stock> availableStock = getFullStockListByWarehouseIdAndStockType(warehouse.getId(), StockType.AVAILABLE);
		List<Stock> reservedStock = getFullStockListByWarehouseIdAndStockType(warehouse.getId(), StockType.RESERVED);
		List<Stock> totalStock = calculateTotalStock(availableStock, reservedStock);
		return WarehouseStockInformation.of(warehouse, availableStock, reservedStock, totalStock);

	}
}
