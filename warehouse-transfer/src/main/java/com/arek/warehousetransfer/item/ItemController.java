package com.arek.warehousetransfer.item;

import com.arek.warehousetransfer.stock.Stock;
import com.arek.warehousetransfer.stock.StockService;
import com.arek.warehousetransfer.utils.AttributeNames;
import com.arek.warehousetransfer.utils.Mappings;
import com.arek.warehousetransfer.warehouse.Warehouse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@Controller
@AllArgsConstructor
@RequestMapping(Mappings.ITEM)
public class ItemController {

	// == fields ==
	private ItemService itemService;
	private StockService stockService;

	// == get mappings ==
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

	@GetMapping("add-stock")
	public String addStock(Model model) {
		model.addAttribute(AttributeNames.STOCK, Stock.emptyStock());
		return Mappings.STOCK_FORM;
	}

//	@GetMapping("full-stock")
//	@ResponseBody
//	public Map<Item, Map<Warehouse, Integer>> fullStock() {
//		return stockService.getFullStockListByItem();
//	}


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
