package com.arek.warehousetransfer.item;

import com.arek.warehousetransfer.stock.Stock;
import com.arek.warehousetransfer.stock.StockService;
import com.arek.warehousetransfer.utils.AttributeNames;
import com.arek.warehousetransfer.utils.Mappings;
import com.arek.warehousetransfer.warehouse.Warehouse;
import com.arek.warehousetransfer.warehouse.WarehouseService;
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
	private WarehouseService warehouseService;

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

	@GetMapping("add")
	public String addItem(Model model) {
		Stock emptyStock = Stock.emptyStock();
		emptyStock.setItem(Item.emptyItem());
		model.addAttribute(AttributeNames.STOCK, emptyStock);
		model.addAttribute("warehouses", warehouseService.findAllWarehouses());
		return Mappings.STOCK_FORM;
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

	@PostMapping("add")
	public String saveNewStock(@ModelAttribute @Valid Stock stock) {
		stockService.saveStock(stock);
		return "redirect:/admin/";
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
