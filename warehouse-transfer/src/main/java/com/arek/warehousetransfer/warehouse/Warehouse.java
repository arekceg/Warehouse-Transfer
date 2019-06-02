package com.arek.warehousetransfer.warehouse;

import com.arek.warehousetransfer.stock.ReservedStock;
import com.arek.warehousetransfer.stock.Stock;
import com.arek.warehousetransfer.user.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "warehouses")
@Data
public class Warehouse {
	// == fields ==

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@NotNull
	@ManyToOne
	private User manager;

	@NotNull
	@OneToMany(mappedBy = "warehouse")
	private List<Stock> stocks;

	@OneToMany(mappedBy = "warehouse")
	private List<ReservedStock> reservedStocks;
}
