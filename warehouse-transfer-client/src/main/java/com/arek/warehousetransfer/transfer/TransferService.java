package com.arek.warehousetransfer.transfer;

import com.arek.warehousetransfer.item.Item;
import com.arek.warehousetransfer.item.ItemService;
import com.arek.warehousetransfer.utils.Mappings;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TransferService {

	// == fields ==
//	private TransferRepository transferRepository;
	private ItemService itemService;
//	private WarehouseRepository warehouseRepository;
//	private StockService stockService;

	// == public methods ==

	//	public void deleteTransfer(Transfer transfer){
//		transferRepository.delete(transfer);
//	}
	public List<Transfer> findAllTransfers() {
		final String uri = Mappings.BACKEND_ADRESS + "/transfer/all";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Transfer>> response = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Transfer>>() {
				}
		);
		return response.getBody();
	}

	public List<Transfer> findAllTransfersBySourceWarehouseId(Long id) {
		final String uri = Mappings.BACKEND_ADRESS + "/transfer/list/outgoing/" + id.toString() + "/all";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Transfer>> response = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Transfer>>() {
				}
		);
		return response.getBody();
	}

	public List<Transfer> findAllTransfersByDestinationWarehouseId(Long id) {
		final String uri = Mappings.BACKEND_ADRESS + "/transfer/list/incoming/" + id.toString() + "/all";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Transfer>> response = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Transfer>>() {
				}
		);
		return response.getBody();
	}

	public List<Transfer> findAllUnacceptedTransfersBySourceWarehouseId(Long id) {
		final String uri = Mappings.BACKEND_ADRESS + "/transfer/list/outgoing/" + id.toString() + "/unaccepted";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Transfer>> response = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Transfer>>() {
				}
		);
		return response.getBody();
	}

	public List<Transfer> findAllUnacceptedTransfersByDestinationWarehouseId(Long id) {
		final String uri = Mappings.BACKEND_ADRESS + "/transfer/list/incoming/" + id.toString() + "/unaccepted";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Transfer>> response = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<Transfer>>() {
				}
		);
		return response.getBody();
	}

//	public void saveTransfer(Transfer transfer) {
//		transferRepository.save(transfer);
//	}

	public Transfer populateTransferDataFromRequestBody(HttpServletRequest req, Transfer transfer) {
		List<TransferContent> transferContents = new ArrayList<>();
		Enumeration<String> paramNames = req.getParameterNames();

		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			if (NumberUtils.isParsable(paramName)) {
				Item itemToAdd = itemService.findItemById(NumberUtils.toLong(paramName));
				int itemAmount = NumberUtils.toInt(req.getParameter(paramName));
				if (itemAmount > 0) {
					TransferContent transferContent = TransferContent.of(itemToAdd, itemAmount, transfer);
					transferContents.add(transferContent);
				}
			}
		}

		transfer.setTransferContents(transferContents);
		return transfer;
	}

//	public void setTransferToAccepted(Long id) {
//		transferRepository.setTransferToAccepted(id, LocalDate.now());
//	}

	public Transfer findTransferById(Long id) {
		final String uri = Mappings.BACKEND_ADRESS + "/transfer/" + id.toString();
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(
				uri,
				Transfer.class
		);
	}

	public void deleteTransfer(Long id) {
		final String uri = Mappings.BACKEND_ADRESS + "/transfer/" + id + "/delete";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(uri);
	}

	public void acceptTransfer(Long id) {
		final String uri = Mappings.BACKEND_ADRESS + "/transfer/" + id + "/accept";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(uri, Transfer.class);
	}
//		3.    Add that reserved stock to destination warehouse as available stock
//		          * find corresponding stock in destination warehouse and increase it's available stock
//		          * create a new stock in destination table if necessary

}
