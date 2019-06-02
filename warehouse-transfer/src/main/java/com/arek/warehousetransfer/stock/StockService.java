package com.arek.warehousetransfer.stock;

import com.arek.warehousetransfer.item.Item;
import com.arek.warehousetransfer.item.ItemRepository;
import com.arek.warehousetransfer.warehouse.Warehouse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StockService {

	// == fields ==

	private StockRepository stockRepository;
	private ItemRepository itemRepository;

	// == public methods ==

	public List<Stock> findAllStocks() {
		return stockRepository.findAll();
	}

	public int sumItemStockByItemIdAndWarehouseId(Long itemId, Long warehouseId) {
		return stockRepository.sumItemStockByItemIdAndWarehouseId(itemId, warehouseId);
	}

	public List<Stock> findStockByWarehouseId(Long id) {
		return stockRepository.findByCurrentWarehouseId(id);
	}

	public Map<Item, Integer> getStockMapFromWarehouse(Long id) {
		return findStockByWarehouseId(id).stream()
				.collect(Collectors.toMap(Stock::getItem, Stock::getItemStock));
	}

	public Map<Warehouse, Integer> getItemStockMapByItemId(Long id) {
		return stockRepository.findByItemId(id).stream()
				.collect(Collectors.toMap(Stock::getCurrentWarehouse, Stock::getItemStock));
	}

	public Map<Item, Map<Warehouse, Integer>> getFullStockListByItem() {
		itemRepository.findAll();
		List<Item> allItems = itemRepository.findAll();
		return allItems.stream()
				.collect(Collectors.toMap(i -> i, i -> getItemStockMapByItemId(i.getId())));
//				.map(i -> getItemStockMapByItemId(i.getId()))
//				.collect(Collectors.toMap(s->s.pu);

	}



}
