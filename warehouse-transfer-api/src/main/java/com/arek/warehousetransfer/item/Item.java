package com.arek.warehousetransfer.item;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
public final class Item {
	@NotBlank
	private String name;
	// == fields ==
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Item(@NotBlank String name) {
		this.name = name;
	}

	// == static methods ==
	@Bean
	public static Item emptyItem(){
		return new Item();
	}
	public static Item named(String itemName) {
		return new Item(itemName);
	}
}
