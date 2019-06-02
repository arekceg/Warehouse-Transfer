package com.arek.warehousetransfer.warehouse;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WarehouseService {

	private WarehouseRepository warehouseRepository;

	public List<Warehouse> findAllWarehouses(){
		return warehouseRepository.findAll();
	}
	public List<Warehouse> findWarehousesWithIdNotEqual(Long id){
		return warehouseRepository.findWarehousesByIdIsNot(id);
	}
	public Warehouse findWarehouseById(Long id){
		return warehouseRepository.findById(id).orElse(null);
	}
}
