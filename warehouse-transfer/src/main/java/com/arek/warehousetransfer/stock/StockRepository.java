package com.arek.warehousetransfer.stock;

import com.arek.warehousetransfer.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

	@Query("SELECT SUM(s.itemStock) FROM Stock s WHERE s.id=?1 AND s.warehouse.id =?2")
	int sumItemStockByItemIdAndWarehouseId(Long itemId, Long warehouseId);

	Stock findStockByItemIdAndWarehouseId(Long itemId, Long warehouseId);
	List<Stock> findByWarehouseId(Long id);
	List<Stock> findByItemId(Long id);
//	Stock findStockByItemIdAndCurrentWarehouseId(Long itemId, Long warehouseId);
}
