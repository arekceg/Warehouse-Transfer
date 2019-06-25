package com.arek.warehousetransfer.warehouse;

import com.arek.warehousetransfer.stock.StockService;
import com.arek.warehousetransfer.transfer.TransferService;
import com.arek.warehousetransfer.user.User;
import com.arek.warehousetransfer.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("warehouse")
@SessionAttributes("currentWarehouseId")
public class WarehouseController {

	private WarehouseService warehouseService;
	private StockService stockService;
	private UserService userService;

	//REST
	@GetMapping("user/{id}")
	public Warehouse getWarehouseByMangerId(@PathVariable Long id) {
		return warehouseService.findWarehouseByManagerId(id);
	}
	//REST
	@GetMapping(value = "{id}")
	public ResponseEntity<Warehouse> getWarehouse(@PathVariable Long id) {
		Warehouse foundWarehouse = warehouseService.findWarehouseById(id);
		if(foundWarehouse!=null) {
			return new ResponseEntity<>(foundWarehouse, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	//REST
	@GetMapping("stockinformation/{id}")
	public WarehouseStockInformation getStockInformation(@PathVariable Long id) {
		Warehouse warehouse = warehouseService.findWarehouseById(id);
		return stockService.getWarehouseStockInformationByWarehouse(warehouse);
	}

	//	//REST
	@GetMapping("all")
	public List<Warehouse> getAllWarehouses(){
		return warehouseService.findAllWarehouses();
	}

	//REST POST
	@PostMapping("add")
	public ResponseEntity<Warehouse> addWarehouse(@RequestBody Warehouse warehouse) {
		User manager = userService.findUserById(warehouse.getManager().getId());
		manager.setWarehouse(warehouse);
		warehouseService.saveWarehouse(warehouse);
		return new ResponseEntity<>(warehouse, HttpStatus.CREATED);
	}
}
