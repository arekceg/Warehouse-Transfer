package com.arek.warehousetransfer.item;

import com.arek.warehousetransfer.utils.Mappings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ItemService {


	// == fields ==
//	private ItemRepository itemRepository;

	// == public methods ==
	public List<Item> findAllItems() {
		final String url = Mappings.BACKEND_ADRESS + "/item/all";
		RestTemplate restTemplate = new RestTemplate();
		ItemListWrapper response = restTemplate.getForObject(url, ItemListWrapper.class);
		return response.getItemList();
	}

	public Item findItemById(Long id) {
		final String uri = Mappings.BACKEND_ADRESS+"/item/"+id.toString();
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(uri,Item.class);
	}
}
//
//	public Item findItemById(Long id) {
//		return itemRepository.findById(id).orElse(null);
//	}
//
//	public void saveItem(Item item) {
//		itemRepository.save(item);
//	}
//}
