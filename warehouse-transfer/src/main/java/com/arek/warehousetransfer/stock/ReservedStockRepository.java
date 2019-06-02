package com.arek.warehousetransfer.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReservedStockRepository extends JpaRepository<ReservedStock, Long> {

	ReservedStock findByItemIdAndWarehouseId(Long itemId, Long warehouseId);

	@Modifying
	@Query("UPDATE ReservedStock SET amount = amount + ?1 WHERE item_id = ?2 AND warehouse_id = ?3")
	void changeReservedStockOfItemInWarehouse(int amount, Long itemId, Long warehouseId);
}
