package com.arek.warehousetransfer.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface StockRepository extends JpaRepository<Stock, Long> {

	@Query("SELECT SUM(s.itemStock) FROM Stock s WHERE s.id=?1 AND s.warehouse.id =?2 AND s.stockType = ?3")
	int sumItemStockByItemIdAndWarehouseId(Long itemId, Long warehouseId, StockType stockType);

	@Modifying
	@Query(value = "UPDATE stocks SET itemStock = itemStock+?1 WHERE item_id = ?2" +
			" AND warehouse_id = ?3 AND stockType = ?4", nativeQuery = true)
	void changeStockOfItemInWarehouse(int amount, Long itemId, Long warehouseId, String stockTypeString);

	@Query("SELECT s FROM Stock s WHERE s.item.id =?1 AND s.warehouse.id=?2")
	List<Stock> getTotalStockByItemIdAndWarehouseId(Long itemId, Long warehouseId);


	Stock findStockByItemIdAndWarehouseIdAndStockType(Long id, Long warehouseId, StockType stockType);

	List<Stock> findByWarehouseIdAndStockType(Long id, StockType stockType);

	List<Stock> findByItemId(Long id);

	@Query(nativeQuery = true, value = "SELECT item_id FROM stocks WHERE warehouse_id = ?1")
	Set<Long> findItemsIdsByWarehouseId(Long warehouseId);



//	Stock findStockByItemIdAndCurrentWarehouseId(Long itemId, Long warehouseId);
}
