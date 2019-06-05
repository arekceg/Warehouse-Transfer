package com.arek.warehousetransfer.transfer;

import com.arek.warehousetransfer.warehouse.Warehouse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "transfers")
@NoArgsConstructor
@Setter
@Getter
public class Transfer {

	// == fields ==
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@OneToOne
	private Warehouse sourceWarehouse;

	@NotNull
	@OneToOne
	private Warehouse destinationWarehouse;

	@NotNull
	private boolean isAccepted;

	@NotNull
	private boolean isChallenged;

	private LocalDate createdDate;
	private LocalDate acceptedDate;
	private LocalDate challengedDate;
	private LocalDate updatedDate;

//	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	private List<TransferContent> transferContents;


//	@OneToMany(mappedBy = "transfer")
//	private List<TempTransferStock> tempTransferStocks;
//
//	@OneToMany
//	private Map<item, Integer> transferContents;


	// == static methods ==
	public static Transfer emptyTransfer() {
		return new Transfer();
	}

	public static Transfer from(Warehouse warehouse){
		Transfer transfer = new Transfer();
		transfer.setSourceWarehouse(warehouse);
		return transfer;
	}

	// == private methods ==
	@PrePersist
	private void prePersist() {
		createdDate = LocalDate.now();
	}

	@PreUpdate
	private void preUpdate() {
		updatedDate = LocalDate.now();
	}

	public boolean isAccepted(){
		return isAccepted;
	}

	public boolean isChallenged(){
		return isChallenged;
	}

	public String getIsAccepted() {
		return isAccepted ? "true" : "false";
	}

	public String getIsChallenged() {
		return isChallenged ? "true" : "false";
	}
}
