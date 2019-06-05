package com.arek.warehousetransfer.transfer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer,Long> {

	List<Transfer> findTransfersBySourceWarehouseId(Long id);
	List<Transfer> findTransfersBySourceWarehouseIdAndIsAccepted(Long id, boolean isAccepted);

	List<Transfer> findTransfersByDestinationWarehouseId(Long id);
	List<Transfer> findTransfersByDestinationWarehouseIdAndIsAccepted(Long id, boolean isAccepted);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE transfers SET isAccepted = 1, acceptedDate = ?2  WHERE id = ?1")
	void setTransferToAccepted(Long id, LocalDate localDate);

}
