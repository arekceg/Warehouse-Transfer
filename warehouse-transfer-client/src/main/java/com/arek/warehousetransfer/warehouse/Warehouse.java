package com.arek.warehousetransfer.warehouse;

import com.arek.warehousetransfer.stock.Stock;
import com.arek.warehousetransfer.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Warehouse {
	// == fields ==

	private Long id;

	@NotBlank
	private String name;

	@NotNull
	private User manager;

	@JsonIgnore
	private List<Stock> stocks;

	@JsonIgnore
	private String warehouseAndManager;

	public static Warehouse empty() {
		return new Warehouse();
	}

	public String getWarehouseAndManager() {
		return name + " : " + manager.getName();
	}
}
