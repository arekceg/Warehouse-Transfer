package com.arek.warehousetransfer.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {


	List<Warehouse> findWarehousesByIdIsNot(Long id);
	Warehouse findWarehouseByManagerId(Long id);
	List<Warehouse> findWarehousesByManagerNull();
}
