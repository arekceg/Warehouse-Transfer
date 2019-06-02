package com.arek.warehousetransfer.transfer;

import com.arek.warehousetransfer.item.Item;
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

	@ManyToOne
	private Transfer transfer;

	@NotNull
	@OneToOne
	private Item item;

	@PositiveOrZero
	@NotNull
	private int amount;

	public TransferContent(@NotNull Item item, @PositiveOrZero @NotNull int amount) {
		this.item = item;
		this.amount = amount;
	}

	//	public TransferContent(@NotNull Item item, @PositiveOrZero @NotNull int amount) {
//		this.item = item;
//		this.amount = amount;
//	}

//	public static TransferContent of(Item transferItem, int itemAmount){
//		return new TransferContent(transferItem,itemAmount);
//	}

	public static TransferContent empty(){
		return new TransferContent();
	}

	public static TransferContent of(Item itemToTransfer, int itemAmount){
		return new TransferContent(itemToTransfer,itemAmount);
	}
}

