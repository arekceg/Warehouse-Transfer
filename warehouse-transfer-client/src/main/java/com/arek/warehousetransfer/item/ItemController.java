package com.arek.warehousetransfer.item;

import com.arek.warehousetransfer.stock.StockService;
import com.arek.warehousetransfer.utils.AttributeNames;
import com.arek.warehousetransfer.utils.Mappings;
import com.arek.warehousetransfer.warehouse.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping(Mappings.ITEM)
public class ItemController {

	// == fields ==
	private ItemService itemService;
	private StockService stockService;
	private WarehouseService warehouseService;

	// == get mappings ==
	// == REST COMMUNICATION ==
	@GetMapping("all-test")
	@ResponseBody
	public List<Item> getItemsFromApi(){
		final String url = "http://localhost:8081/item/all";
		RestTemplate restTemplate = new RestTemplate();
		ItemListWrapper response = restTemplate.getForObject(url, ItemListWrapper.class);
//		return response.getItemsList();
//		ResponseEntity<List<Item>> response = restTemplate.exchange(
//				"http://localhost:8081/item/all",
//				HttpMethod.GET,
//				null,
//				new ParameterizedTypeReference<List<Item>>(){});
		return response.getItemList();
	}

	@GetMapping("")
//	@ResponseBody
	public String getAllItems(Model model) {
		model.addAttribute(AttributeNames.ITEMS, itemService.findAllItems());
		model.addAttribute(AttributeNames.STOCKS, stockService.findAllStocks());
		model.addAttribute(AttributeNames.ITEM, Item.emptyItem());
		return Mappings.ITEM_LIST;
	}

	@GetMapping("edit/{id}")
	public String editItem(Model model, @PathVariable Long id) {
		model.addAttribute(AttributeNames.ITEM, itemService.findItemById(id));
		return Mappings.ITEM_FORM;
	}




	// == post mappings ==
	@PostMapping("")
	public String saveItem(Model model,
	                       @ModelAttribute @Valid Item item,
	                       BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute(AttributeNames.ITEMS, itemService.findAllItems());
			return Mappings.ITEM_LIST;
		}
		itemService.saveItem(item);
		return "redirect:/" + Mappings.ITEM + "/";
	}



	@PostMapping("edit/{id}")
	public String updateItem(@ModelAttribute @Valid Item item,
	                         BindingResult result) {
		if (result.hasErrors()) {
			return Mappings.ITEM_FORM;
		}
		itemService.saveItem(item);
		return "redirect:/" + Mappings.ITEM + "/";
	}
}
