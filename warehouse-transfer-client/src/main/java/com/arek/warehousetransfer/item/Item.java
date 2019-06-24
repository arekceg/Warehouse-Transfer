package com.arek.warehousetransfer.item;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public final class Item {
	@NotBlank
	private String name;
	// == fields ==
	private Long id;

	private Item(@NotBlank String name) {
		this.name = name;
	}

	// == static methods ==
	@Bean
	public static Item emptyItem() {
		return new Item();
	}

	public static Item named(String itemName) {
		return new Item(itemName);
	}
}
