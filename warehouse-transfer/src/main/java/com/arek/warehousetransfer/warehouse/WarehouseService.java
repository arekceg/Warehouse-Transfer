package com.arek.warehousetransfer.warehouse;

import com.arek.warehousetransfer.stock.Stock;
import com.arek.warehousetransfer.stock.StockService;
import com.arek.warehousetransfer.stock.StockType;
import com.arek.warehousetransfer.user.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WarehouseService {

	private WarehouseRepository warehouseRepository;

	public List<Warehouse> findAllWarehousesWithoutManagers(){
		return warehouseRepository.findWarehousesByManagerNull();
	}
	public List<Warehouse> findAllWarehouses() {
		return warehouseRepository.findAll();
	}

	public List<Warehouse> findWarehousesWithIdNotEqual(Long id) {
		return warehouseRepository.findWarehousesByIdIsNot(id);
	}

	public void saveWarehouse(Warehouse warehouse){
		warehouseRepository.save(warehouse);
	}
	public Warehouse findWarehouseById(Long id) {
		return warehouseRepository.findById(id).orElse(null);
	}

	public Warehouse findWarehouseByManagerId(Long id){
		return warehouseRepository.findWarehouseByManagerId(id);
	}


}
