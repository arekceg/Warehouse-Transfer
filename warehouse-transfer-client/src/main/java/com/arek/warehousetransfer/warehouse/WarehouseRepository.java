//package com.arek.warehousetransfer.warehouse;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
//public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {
//
////	@Query("SELECT w FROM Warehouse w WHERE w.id != ?1")
////	List<Warehouse> findAllWarehousesWithIdNotEqual(Long id);
//
//	List<Warehouse> findWarehousesByIdIsNot(Long id);
//	Warehouse findWarehouseByManagerId(Long id);
//	List<Warehouse> findWarehousesByManagerNull();
//}
