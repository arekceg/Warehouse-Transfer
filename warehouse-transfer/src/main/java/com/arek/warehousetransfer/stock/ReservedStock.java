package com.arek.warehousetransfer.stock;

import com.arek.warehousetransfer.item.Item;
import com.arek.warehousetransfer.warehouse.Warehouse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ReservedStock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@OneToOne
	private Item item;

	@NotNull
	@PositiveOrZero
	private int amount;

	@ManyToOne
	private Warehouse warehouse;

	private ReservedStock(@NotNull Item item, @NotNull @PositiveOrZero int amount) {
		this.item = item;
		this.amount = amount;
	}

	public static ReservedStock of(Item reservedItem, int reservedAmount){
		return new ReservedStock(reservedItem,reservedAmount);
	}
}
