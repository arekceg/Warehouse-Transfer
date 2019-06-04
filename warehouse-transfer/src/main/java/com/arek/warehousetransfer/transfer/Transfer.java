package com.arek.warehousetransfer.transfer;

import com.arek.warehousetransfer.item.Item;
import com.arek.warehousetransfer.stock.Stock;
import com.arek.warehousetransfer.warehouse.Warehouse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
		return isAccepted ? "yes" : "no";
	}

	public String getIsChallenged() {
		return isChallenged ? "yes" : "no";
	}
}
