package com.arek.warehousetransfer.transfer;

import com.arek.warehousetransfer.DummyObjectFactory;
import com.arek.warehousetransfer.stock.Stock;
import com.arek.warehousetransfer.stock.StockService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

public class TransferServiceTest {
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@Mock
	StockService stockService;
	@Mock
	TransferRepository transferRepository;

	@InjectMocks
	TransferService transferService;

	@Test
	public void deleteTransferTest() {
		//given
		List<TransferContent> dummyTransferContentList = DummyObjectFactory.dummyTransferContentList();
		Transfer dummyTransfer = DummyObjectFactory.dummyTransfer();
		dummyTransfer.setTransferContents(dummyTransferContentList);

		given(transferRepository.findById(anyLong()))
				.willReturn(Optional.of(dummyTransfer));

		ArgumentCaptor<Stock> stockArgumentCaptor = ArgumentCaptor.forClass(Stock.class);
		willDoNothing().given(stockService).updateStockInWarehouse(
				stockArgumentCaptor.capture(),anyBoolean());

		willDoNothing().given(transferRepository).delete(
				any(Transfer.class));

		willDoNothing().given(transferRepository).setTransferToAccepted(
				anyLong(),
				any(LocalDate.class)
		);

		//when
		transferService.deleteTransfer(1L);

		//then
        //ensure expected number of method calls
		then(stockService).should(atMost(4)).updateStockInWarehouse(
				any(Stock.class),
				anyBoolean());

		//compare method parameters with dummy transfer contents
		assertEquals(stockArgumentCaptor.getAllValues().get(0).getItem(),
				dummyTransferContentList.get(0).getItem());
		assertEquals(stockArgumentCaptor.getAllValues().get(2).getItem(),
				dummyTransferContentList.get(1).getItem());

		//compare items on subsequent method calls
		assertEquals(stockArgumentCaptor.getAllValues().get(0).getItem(),
				stockArgumentCaptor.getAllValues().get(1).getItem());
		assertEquals(stockArgumentCaptor.getAllValues().get(2).getItem(),
				stockArgumentCaptor.getAllValues().get(3).getItem());

		//compare item amounts on subsequent method calls
		assertEquals(stockArgumentCaptor.getAllValues().get(0).getItemStock(),
				-stockArgumentCaptor.getAllValues().get(1).getItemStock());
		assertEquals(stockArgumentCaptor.getAllValues().get(2).getItemStock(),
				-stockArgumentCaptor.getAllValues().get(3).getItemStock());

		//ensure that the delete() method is called
		then(transferRepository).should().delete(dummyTransfer);
	}

}