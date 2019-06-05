package com.arek.warehousetransfer.warehouse;

import com.arek.warehousetransfer.stock.Stock;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WarehouseStockInformation {
	private Warehouse warehouse;
	private List<Stock> availableStock;
	private List<Stock> reservedStock;
	private List<Stock> totalStock;


	public WarehouseStockInformation() {
	}

	private WarehouseStockInformation(Warehouse warehouse, List<Stock> availableStock, List<Stock> reservedStock, List<Stock> totalStock) {
		this.warehouse = warehouse;
		this.availableStock = availableStock;
		this.reservedStock = reservedStock;
		this.totalStock = totalStock;
	}

	public static WarehouseStockInformation of(Warehouse warehouse, List<Stock> availableStock,
	                                           List<Stock> reservedStock, List<Stock> totalStock) {
		return new WarehouseStockInformation(warehouse, availableStock, reservedStock, totalStock);
	}
}
