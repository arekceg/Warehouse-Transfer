package com.arek.warehousetransfer.stock;

import com.arek.warehousetransfer.DummyObjectFactory;
import com.arek.warehousetransfer.item.Item;
import com.arek.warehousetransfer.item.ItemRepository;
import com.arek.warehousetransfer.item.ItemService;
import com.arek.warehousetransfer.transfer.Transfer;
import com.arek.warehousetransfer.transfer.TransferContent;
import com.arek.warehousetransfer.warehouse.Warehouse;
import com.arek.warehousetransfer.warehouse.WarehouseRepository;
import com.arek.warehousetransfer.warehouse.WarehouseService;
import com.google.common.collect.Lists;
import org.assertj.core.util.Sets;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;

public class StockServiceTest {

	private static List<Stock> dummyAvailableStockList;
	private static List<Stock> dummyReservedStockList;
	private static List<Stock> dummyTotalStockList;
	private static Warehouse dummyWarehouse;
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	@InjectMocks
	StockService stockService;
	@Mock
	WarehouseService warehouseService;
	@Mock
	StockRepository stockRepository;
	@Mock
	WarehouseRepository warehouseRepository;
	@Mock
	ItemRepository itemRepository;
	@Mock
	ItemService itemService;

	@BeforeClass
	public static void setUp() {
		dummyWarehouse = DummyObjectFactory.dummyWarehouse();
		dummyAvailableStockList = Lists.newArrayList();
		dummyReservedStockList = Lists.newArrayList();
		dummyTotalStockList = Lists.newArrayList();

		for (int i = 1; i <= 5; i++) {
			Item dummyItem = DummyObjectFactory.dummyItem(Integer.toUnsignedLong(i), "dummyItem" + i);

			Stock dummyAvailableStock = DummyObjectFactory.dummyStock(dummyItem, i, Warehouse.empty(), StockType.AVAILABLE);
			Stock dummyReservedStock = DummyObjectFactory.dummyStock(dummyItem, i - 1, Warehouse.empty(), StockType.RESERVED);
			Stock dummyTotalStock = DummyObjectFactory.dummyStock(dummyItem, i - 1 + i, Warehouse.empty(), StockType.TOTAL);

			dummyAvailableStockList.add(dummyAvailableStock);
			dummyReservedStockList.add(dummyReservedStock);
			dummyTotalStockList.add(dummyTotalStock);
		}

		dummyWarehouse.setStocks(dummyAvailableStockList);
	}

	@Test
	public void findStockByItemIdAndWarehouseIdTest() {
		//given
		Item dummyItem = Item.named("dummyItem");
		dummyItem.setId(1L);

		Warehouse dummyWarehouse = Warehouse.empty();
		dummyWarehouse.setId(2L);

		Stock stubStock = Stock.of(
				dummyItem,
				1,
				dummyWarehouse,
				StockType.AVAILABLE
		);
		when(stockRepository.findStockByItemIdAndWarehouseIdAndStockType(
				anyLong(),
				anyLong(),
				any(StockType.class)
		)).thenReturn(stubStock);

		//when
		Stock stock = stockService.findStockByItemIdAndWarehouseId(1L, 2L, StockType.AVAILABLE);

		//then
		assertNotNull(stock);
		assertThat(stock.getItem().getId(), is(equalTo(1L)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void findStockByItemIdAndWarehouseIdTest_stockNotFound() {
		//given
		//when
		when(stockRepository.findStockByItemIdAndWarehouseIdAndStockType(
				anyLong(),
				anyLong(),
				any(StockType.class)
		)).thenReturn(null);
		//then
		stockService.findStockByItemIdAndWarehouseId(1L, 2L, StockType.AVAILABLE);
	}

	@Test
	public void calculateTotalStockTest() {
		//given

		//when
		List<Stock> totalStockList = stockService.calculateTotalStock(dummyAvailableStockList, dummyReservedStockList);

		//then
		assertEquals(dummyTotalStockList.size(), totalStockList.size());
		for (int i = 0; i < totalStockList.size(); i++) {
			assertEquals(dummyTotalStockList.get(i).getItem().getId(), totalStockList.get(i).getItem().getId());
			assertEquals(dummyTotalStockList.get(i).getItemStock(), totalStockList.get(i).getItemStock());
		}
	}

	@Test
	public void updateStockInWarehouseTest_stockNotFoundInWarehouse() {
		//given
		Stock dummyStock = DummyObjectFactory.dummyStock(StockType.AVAILABLE);
		Warehouse dummyWarehouse = dummyStock.getWarehouse();

		given(stockRepository.findStockByItemIdAndWarehouseIdAndStockType(
				anyLong(),
				anyLong(),
				any(StockType.class)
		)).willReturn(null);

		given(warehouseService.findWarehouseById(anyLong())).willReturn(dummyWarehouse);

		given(stockRepository.save(any(Stock.class))).will(invocation ->
				invocation.getArgument(0));

		willDoNothing().given(itemService).saveItem(any(Item.class));

		//when
		stockService.updateStockInWarehouse(dummyStock, false);

		//then
		then(itemService).should().saveItem(dummyStock.getItem());
		then(stockRepository).should().save(any(Stock.class));
	}

	@Test
	public void updateStockInWarehouseTest_shouldUpdateStockInWarehouse() {
		//given
		Stock dummyStock = DummyObjectFactory.dummyStock(StockType.AVAILABLE);
		Warehouse dummyWarehouse = dummyStock.getWarehouse();
		dummyWarehouse.getStocks().add(dummyStock);

		given(stockRepository.findStockByItemIdAndWarehouseIdAndStockType(
				anyLong(),
				anyLong(),
				any(StockType.class)
		)).willReturn(dummyStock);

		//when
		stockService.updateStockInWarehouse(dummyStock, false);

		//then
		then(stockRepository).should().changeStockOfItemInWarehouse(
				dummyStock.getItemStock(),
				dummyStock.getItem().getId(),
				dummyStock.getWarehouse().getId(),
				dummyStock.getStockType().toString()
		);
	}

	@Test
	public void listUniqueItemsIdInWarehouseTest_shouldReturnSortedList() {
		//given
		List<Long> dummyIdList = Arrays.asList(1L, 5L, 10L, 2L, 3L, 4L, 12L);
		Set<Long> dummyItemIdSet = Sets.newHashSet(dummyIdList);
		Collections.sort(dummyIdList);

		given(stockRepository.findItemsIdsByWarehouseId(anyLong()))
				.willReturn(dummyItemIdSet);

		//when
		List<Long> idList = stockService.listUniqueItemsIdInWarehouse(1L);

		//then
		for (int i = 0; i < idList.size(); i++) {
			assertEquals(idList.get(i), dummyIdList.get(i));
		}
	}


	@Test
	public void getFullStockListByWarehouseIdAndStockTypeTest_testingOnDummyAvailableStockList() {
		//dummy stock list defined at the top
		//given
		List<Long> dummyIdList = Arrays.asList(1L, 5L, 10L, 2L, 3L, 4L, 12L);
		Set<Long> dummyItemIdSet = Sets.newHashSet(dummyIdList);
		//expected dummy total Stock list should look like this:
		//  ItemID  amount
		//  1L      1
		//  2L      2
		//  3L      3
		//  4L      4
		//  5L      5
		//  10L     0
		//  12L     0
		List<Stock> dummyTotalStockList = Lists.newArrayList(dummyAvailableStockList);

		Stock dummyStockForId12 = DummyObjectFactory.dummyStock(
				DummyObjectFactory.dummyItem(12L, null),
				0,
				null,
				StockType.AVAILABLE
		);

		Stock dummyStockForId10 = DummyObjectFactory.dummyStock(
				DummyObjectFactory.dummyItem(10L, null),
				0,
				null,
				StockType.AVAILABLE
		);

		dummyTotalStockList.add(dummyStockForId10);
		dummyTotalStockList.add(dummyStockForId12);


		given(stockRepository.findByWarehouseIdAndStockType(anyLong(), any(StockType.class)))
				.willReturn(dummyAvailableStockList);

		given(stockRepository.findItemsIdsByWarehouseId(anyLong()))
				.willReturn(dummyItemIdSet);

		given(itemService.findItemById(anyLong())).will(invocation ->
				DummyObjectFactory.dummyItem(invocation.getArgument(0), null));

		given(warehouseService.findWarehouseById(anyLong())).willReturn(dummyWarehouse);

		//when
		List<Stock> totalStockList = stockService
				.getFullStockListByWarehouseIdAndStockType(1L, StockType.AVAILABLE);

		//then
		assertThat(totalStockList.size(), is(equalTo(dummyTotalStockList.size())));
		for (int i = 0; i < totalStockList.size(); i++) {
			assertEquals(totalStockList.get(i).getItem().getId(),
					dummyTotalStockList.get(i).getItem().getId());
			assertThat(totalStockList.get(i).getItemStock(), is(equalTo(
					dummyTotalStockList.get(i).getItemStock()
			)));
		}
	}

	@Test
	public void findStockFromListByItemIdTest() {
		//given
		Long itemId = 2L;
		Item dummyItem = DummyObjectFactory.dummyItem(itemId, "dummyItem");

		//when
		Stock foundStock = stockService.findStockFromListByItemId(dummyAvailableStockList, itemId);

		//then
		assertThat(foundStock.getItemStock(), is(equalTo(2)));
		assertEquals(foundStock.getItem().getId(), dummyItem.getId());
	}

	@Test
	public void findStockFromListByItemIdTest_itemNotFoundShouldReturnNull() {
		//given
		Long itemId = 10L;

		//when
		Stock foundStock = stockService.findStockFromListByItemId(dummyAvailableStockList, itemId);

		//then
		assertNull(foundStock);
	}

	@Test
	public void updateReservedStockFromTransferDataTest() {
		//given
		Stock dummyStock = DummyObjectFactory.dummyStock(StockType.RESERVED);
		Transfer dummyTransfer = DummyObjectFactory.dummyTransfer();
		dummyTransfer.setSourceWarehouse(dummyWarehouse);

		List<TransferContent> dummyTransferContentList = Lists.newArrayList();
		for (int i = 1; i <= 2; i++) {
			Item tempDummyItem = DummyObjectFactory.dummyItem(
					Integer.toUnsignedLong(i), "dummyItem" + i);
			dummyTransferContentList.add(TransferContent.of(tempDummyItem, i, dummyTransfer));
		}

		dummyTransfer.setTransferContents(dummyTransferContentList);

		given(stockRepository.findStockByItemIdAndWarehouseIdAndStockType(
				anyLong(),
				anyLong(),
				any(StockType.class)
		)).willReturn(null, dummyStock);

		willDoNothing().given(stockRepository).changeStockOfItemInWarehouse(
				anyInt(),
				anyLong(),
				anyLong(),
                anyString()
				);

		given(warehouseService.findWarehouseById(anyLong())).willReturn(dummyWarehouse);

		ArgumentCaptor<Stock> stockArgumentCaptor = ArgumentCaptor.forClass(Stock.class);
		given(stockRepository.save(stockArgumentCaptor.capture())).will(invocation ->
				invocation.getArgument(0));

		willDoNothing().given(itemService).saveItem(any(Item.class));

		//when
		stockService.updateReservedStockFromTransferData(dummyTransfer);
		List<Stock> capturesStockList = stockArgumentCaptor.getAllValues();

		//then
		assertThat(capturesStockList.size(), is(equalTo(1)));
		assertEquals(capturesStockList.get(0).getItem(), dummyTransferContentList.get(0).getItem());
	}
}