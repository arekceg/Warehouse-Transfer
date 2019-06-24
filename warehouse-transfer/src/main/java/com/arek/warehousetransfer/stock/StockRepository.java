package com.arek.warehousetransfer.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface StockRepository extends JpaRepository<Stock, Long> {

	@Modifying
	@Query(value = "UPDATE stocks SET itemStock = itemStock+?1 WHERE item_id = ?2" +
			" AND warehouse_id = ?3 AND stockType = ?4", nativeQuery = true)
	void changeStockOfItemInWarehouse(int amount, Long itemId, Long warehouseId, String stockTypeString);

	Stock findStockByItemIdAndWarehouseIdAndStockType(Long id, Long warehouseId, StockType stockType);

	List<Stock> findByWarehouseIdAndStockType(Long id, StockType stockType);

	@Query(nativeQuery = true, value = "SELECT item_id FROM stocks WHERE warehouse_id = ?1")
	Set<Long> findItemsIdsByWarehouseId(Long warehouseId);

}
