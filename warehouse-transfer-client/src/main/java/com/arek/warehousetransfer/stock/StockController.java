package com.arek.warehousetransfer.stock;

import com.arek.warehousetransfer.item.Item;
import com.arek.warehousetransfer.item.ItemService;
import com.arek.warehousetransfer.utils.AttributeNames;
import com.arek.warehousetransfer.utils.Mappings;
import com.arek.warehousetransfer.warehouse.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("stock")
public class StockController {

	private StockService stockService;
	private WarehouseService warehouseService;
	private ItemService itemService;

    // == WORKS ==
	@GetMapping("add/{choice}")
	public String addNewItemToStock(Model model,
	                                @PathVariable String choice) {
		Stock emptyStock = Stock.emptyStock();
		emptyStock.setItem(Item.emptyItem());
		model.addAttribute(AttributeNames.STOCK, emptyStock);
		// == WORKS ==
		model.addAttribute("items", itemService.findAllItems());
		// == WORKS ==
		model.addAttribute("warehouses", warehouseService.findAllWarehouses());
		if (choice.equalsIgnoreCase("new")) return Mappings.STOCK_FORM;
		else if (choice.equalsIgnoreCase("existing")) return "stock/existing-item-stock-form";
		return "redirect:/admin/";
	}

    // == WORKS ==
	@PostMapping("add/{choice}")
	public String saveNewStock(@ModelAttribute @Valid Stock stock) {
		StockAndWarehouseWrapper wrapper = StockAndWarehouseWrapper.empty();
		wrapper.setStock(stock);
		wrapper.setWarehouse(stock.getWarehouse());
		final String uri = Mappings.BACKEND_ADRESS + "/stock/add";

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(uri, wrapper);
		return "redirect:/admin/";
	}
}
