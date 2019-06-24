package com.arek.warehousetransfer.stock;

import com.arek.warehousetransfer.item.ItemService;
import com.arek.warehousetransfer.utils.Mappings;
import com.arek.warehousetransfer.warehouse.Warehouse;
import com.arek.warehousetransfer.warehouse.WarehouseService;
import com.arek.warehousetransfer.warehouse.WarehouseStockInformation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StockService {

	// == fields ==

	// == public methods ==

	public WarehouseStockInformation getWarehouseStockInformationByWarehouse(Warehouse warehouse) {
		final String uri = Mappings.BACKEND_ADRESS + "/warehouse/stockinformation/" + warehouse.getId().toString();
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(uri, WarehouseStockInformation.class);
	}
}
