package com.arek.warehousetransfer.warehouse;

import com.arek.warehousetransfer.DummyObjectFactory;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class WarehouseControllerTest {
	private static Warehouse dummyWarehouse;
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();
	@InjectMocks
	WarehouseController warehouseController;
	@Mock
	WarehouseService warehouseService;

	@BeforeClass
	public static void setUp() {
	}


	@Test
	public void getWarehouseByIdTest() {
		//given
		dummyWarehouse = DummyObjectFactory.dummyWarehouse();

		//when
		when(warehouseService.findWarehouseById(anyLong()))
				.thenReturn(dummyWarehouse);

		//then
		Warehouse foundWarehouse = warehouseController.getWarehouse(1L);
		assertNotNull(foundWarehouse);
		assertThat(foundWarehouse.getId(), is(equalTo(1L)));
		assertThat(foundWarehouse.getName(), allOf(
				notNullValue(),
				is(equalTo(dummyWarehouse.getName()))
		));
	}

	@Test
	public void getStockInformation() {
	}
}