package com.arek.warehousetransfer.stock;

import com.arek.warehousetransfer.item.Item;
import com.arek.warehousetransfer.warehouse.Warehouse;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class StockServiceTest {

	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@InjectMocks
	StockService stockService;

	@Mock
	StockRepository stockRepository;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(StockServiceTest.class);
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

		//when
		when(stockRepository.findStockByItemIdAndWarehouseIdAndStockType(
				anyLong(),
				anyLong(),
				any(StockType.class)
		)).thenReturn(stubStock);

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
}