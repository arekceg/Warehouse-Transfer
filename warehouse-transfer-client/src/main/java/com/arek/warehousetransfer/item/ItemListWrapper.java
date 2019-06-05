package com.arek.warehousetransfer.item;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ItemListWrapper implements Serializable {
	private List<Item> itemList;

	public static ItemListWrapper of(List<Item> itemList){
		ItemListWrapper itemListWrapper = new ItemListWrapper();
		itemListWrapper.setItemList(itemList);
		return itemListWrapper;
	}
}
