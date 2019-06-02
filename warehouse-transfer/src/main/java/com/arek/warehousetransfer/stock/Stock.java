package com.arek.warehousetransfer.stock;

import com.arek.warehousetransfer.item.Item;
import com.arek.warehousetransfer.warehouse.Warehouse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "stocks")
@Setter
@Getter
@NoArgsConstructor
public class Stock {

	// == fields ==

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@OneToOne
	private Item item;

	@NotNull
	@PositiveOrZero
	@Column(nullable = false)
	private int itemStock;

	//	@NotNull
	@ManyToOne
	private Warehouse currentWarehouse;

	// == constructor ==

	private Stock(@NotNull Item item, @NotBlank @PositiveOrZero int itemStock,
	              @NotNull Warehouse warehouse) {
		this.item = item;
		this.itemStock = itemStock;
		this.currentWarehouse = warehouse;
	}

	// == static methods ==

	public static Stock emptyStock() {
		return new Stock();
	}

	public static Stock of(Item item, int stock, Warehouse warehouse) {
		return new Stock(item, stock, warehouse);
	}


	public String toString() {
		return "Stock(item=" + this.getItem() + ", itemStock=" + this.getItemStock()+"\n";
	}
}
