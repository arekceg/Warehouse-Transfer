package com.arek.warehousetransfer.transfer;

import com.arek.warehousetransfer.warehouse.Warehouse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class Transfer {

	// == fields ==
	private Long id;

	@NotNull
	private Warehouse sourceWarehouse;

	@NotNull
	private Warehouse destinationWarehouse;

	@NotNull
	private boolean isAccepted;

	@NotNull
	private boolean isChallenged;

	private LocalDate createdDate;
	private LocalDate acceptedDate;
	private LocalDate challengedDate;
	private LocalDate updatedDate;

	private List<TransferContent> transferContents;

	// == static methods ==
	public static Transfer emptyTransfer() {
		return new Transfer();
	}

	public static Transfer from(Warehouse warehouse) {
		Transfer transfer = new Transfer();
		transfer.setSourceWarehouse(warehouse);
		return transfer;
	}

	// == private methods ==

	public String getIsAccepted() {
		return isAccepted ? "true" : "false";
	}

	public String getIsChallenged() {
		return isChallenged ? "true" : "false";
	}
}
