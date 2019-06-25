package com.arek.warehousetransfer.item;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {


	// == fields ==
	private ItemRepository itemRepository;

	// == public methods ==
	public List<Item> findAllItems(){
		return itemRepository.findAll();
	}

	public Item findItemById(Long id){
		return itemRepository.findById(id).orElse(null);
	}

	public void saveItem(Item item){
		itemRepository.save(item);
	}
}
