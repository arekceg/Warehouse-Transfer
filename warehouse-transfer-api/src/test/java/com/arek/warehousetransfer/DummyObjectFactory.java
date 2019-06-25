package com.arek.warehousetransfer;

import com.arek.warehousetransfer.item.Item;
import com.arek.warehousetransfer.stock.Stock;
import com.arek.warehousetransfer.stock.StockType;
import com.arek.warehousetransfer.transfer.Transfer;
import com.arek.warehousetransfer.transfer.TransferContent;
import com.arek.warehousetransfer.user.Role.Role;
import com.arek.warehousetransfer.user.User;
import com.arek.warehousetransfer.warehouse.Warehouse;
import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DummyObjectFactory {

	public static Item dummyItem() {
		Item dummyItem = Item.named("dummyItem");
		dummyItem.setId(1L);
		return dummyItem;
	}


	public static Item dummyItem(Long id, String name) {
		Item dummyItem = Item.named(name);
		dummyItem.setId(id);
		return dummyItem;
	}

	public static Stock dummyStock(StockType stockType) {
		return Stock.of(
				dummyItem(),
				1,
				dummyWarehouse(),
				stockType);
	}

	public static Stock dummyStock(Item item, int amount, Warehouse warehouse, StockType stockType) {
		return Stock.of(
				item,
				amount,
				warehouse,
				stockType);
	}

	public static Warehouse dummyWarehouse() {
		Warehouse dummyWarehouse = Warehouse.empty();
		dummyWarehouse.setId(1L);
		dummyWarehouse.setName("dummyWarehouse");
		dummyWarehouse.setManager(dummyUser());
		dummyWarehouse.setStocks(Lists.newArrayList());
		return dummyWarehouse;
	}


	public static Warehouse dummyWarehouse(Long id, String name, User manager, List<Stock> stockList) {
		Warehouse dummyWarehouse = Warehouse.empty();
		dummyWarehouse.setId(id);
		dummyWarehouse.setName(name);
		dummyWarehouse.setManager(manager);
		dummyWarehouse.setStocks(stockList);
		return dummyWarehouse;
	}

	private static User dummyUser() {
		Warehouse dummyWarehouse = Warehouse.empty();
		dummyWarehouse.setId(1L);
		dummyWarehouse.setName("dummyWarehouse");
		dummyWarehouse.setStocks(Lists.newArrayList());

		Role userRole = new Role();
		userRole.setId(2L);
		userRole.setName("USER_ROLE");

		Set<Role> roles = new HashSet<>();
		roles.add(userRole);

		User dummyUser = User.empty();
		dummyUser.setId(1L);
		dummyUser.setName("dummyUser");
		dummyUser.setRoles(roles);
		dummyUser.setPassword("dummyPassword");
		dummyUser.setEnabled(1);
		dummyUser.setWarehouse(dummyWarehouse);

		return dummyUser;
	}

	private static User dummyUser(Long id,
	                              String name,
	                              Set<Role> roles,
	                              String password,
	                              int enabled,
	                              Warehouse warehouse) {
		User dummyUser = User.empty();
		dummyUser.setId(id);
		dummyUser.setName(name);
		dummyUser.setRoles(roles);
		dummyUser.setPassword(password);
		dummyUser.setEnabled(enabled);
		dummyUser.setWarehouse(warehouse);
		return dummyUser;
	}

	public static Transfer dummyTransfer(){
		return Transfer.emptyTransfer();
	}

	public static List<TransferContent> dummyTransferContentList(){
		//List will contain only two elements for quicker testing
		List<TransferContent> dummyTransferContentList = Lists.newArrayList();
		for (int i = 1; i <= 2; i++) {
			Item tempDummyItem = DummyObjectFactory.dummyItem(
					Integer.toUnsignedLong(i), "dummyItem" + i);
			dummyTransferContentList.add(TransferContent.of(tempDummyItem, i, dummyTransfer()));
		}

		return dummyTransferContentList;
	}


}
