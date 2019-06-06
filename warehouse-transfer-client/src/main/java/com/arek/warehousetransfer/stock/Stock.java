package com.arek.warehousetransfer.stock;

import com.arek.warehousetransfer.item.Item;
import com.arek.warehousetransfer.warehouse.Warehouse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

//@Entity
//@Table(name = "stocks")
@Setter
@Getter
@NoArgsConstructor
public class Stock {

	// == fields ==

	//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
//	@OneToOne(cascade = CascadeType.PERSIST)
	private Item item;

	@NotNull
	@PositiveOrZero
//	@Column(nullable = false)
	private int itemStock;

	@NotNull
//	@ManyToOne
	@JsonIgnore
	private Warehouse warehouse;

	@NotNull
//	@Enumerated(EnumType.STRING)
	private StockType stockType;


	// == constructor ==

	private Stock(@NotNull Item item, @NotBlank @PositiveOrZero int itemStock,
	              @NotNull Warehouse warehouse, @NotNull StockType stockType) {
		this.item = item;
		this.itemStock = itemStock;
		this.warehouse = warehouse;
		this.stockType = stockType;
	}

	// == static methods ==

	public static Stock emptyStock() {
		return new Stock();
	}

	public static Stock of(Item stockItem, int itemAmount, Warehouse currentWarehouse, StockType stockType) {
		return new Stock(stockItem, itemAmount, currentWarehouse, stockType);
	}

	// == public methods ==
	public String toString() {
		return "Stock(item=" + this.getItem() + ", itemStock=" + this.getItemStock() + "\n";
	}

	//	@Enumerated(EnumType.STRING)
	public StockType getStockType() {
		return stockType;
	}
}
