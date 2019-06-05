package com.arek.warehousetransfer.stock;

import com.arek.warehousetransfer.warehouse.Warehouse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockAndWarehouseWrapper {
	private Stock stock;
	private Warehouse warehouse;

	public static StockAndWarehouseWrapper empty() {
		return new StockAndWarehouseWrapper();
	}
}
