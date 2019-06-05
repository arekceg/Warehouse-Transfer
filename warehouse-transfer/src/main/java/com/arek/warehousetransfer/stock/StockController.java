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

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("stock")
public class StockController {

	private StockService stockService;
	private WarehouseService warehouseService;
	private ItemService itemService;

	@GetMapping("add/{choice}")
	public String addNewItemToStock(Model model,
	                                @PathVariable String choice) {
		Stock emptyStock = Stock.emptyStock();
		emptyStock.setItem(Item.emptyItem());
		model.addAttribute(AttributeNames.STOCK, emptyStock);
		model.addAttribute("items", itemService.findAllItems());
		model.addAttribute("warehouses", warehouseService.findAllWarehouses());
		if (choice.equalsIgnoreCase("new")) return Mappings.STOCK_FORM;
		else if (choice.equalsIgnoreCase("existing")) return "stock/existing-item-stock-form";
		return "redirect:/admin/";
	}

	@PostMapping("add/{choice}")
	public String saveNewStock(@ModelAttribute @Valid Stock stock) {
//		stock.setWarehouse(warehouseService.findWarehouseById(stock.getWarehouse().getId()));
//		if (stock.getItem().getId() != null) {
//			stock.setItem(itemService.findItemById(stock.getItem().getId()));
//		}
		stockService.updateStockInWarehouse(stock.getItemStock(), stock.getItem(),
				stock.getWarehouse(), StockType.AVAILABLE, false);
		return "redirect:/admin/";
	}
}
