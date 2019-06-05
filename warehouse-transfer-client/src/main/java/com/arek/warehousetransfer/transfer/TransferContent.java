package com.arek.warehousetransfer.transfer;

import com.arek.warehousetransfer.item.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TransferContent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	private Transfer transfer;

	@NotNull
	@OneToOne
	private Item item;

	@PositiveOrZero
	@NotNull
	private int amount;

	public TransferContent(@NotNull Item item, @PositiveOrZero @NotNull int amount, Transfer transfer  ) {
		this.item = item;
		this.amount = amount;
		this.transfer = transfer;
	}

	//	public TransferContent(@NotNull Item item, @PositiveOrZero @NotNull int itemStock) {
//		this.item = item;
//		this.itemStock = itemStock;
//	}

//	public static TransferContent of(Item transferItem, int itemAmount){
//		return new TransferContent(transferItem,itemAmount);
//	}

	public static TransferContent empty(){
		return new TransferContent();
	}

	public static TransferContent of(Item itemToTransfer, int itemAmount, Transfer transfer){
		return new TransferContent(itemToTransfer,itemAmount,transfer);
	}
}

