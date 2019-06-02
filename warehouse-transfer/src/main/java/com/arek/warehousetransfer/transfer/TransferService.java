package com.arek.warehousetransfer.transfer;

import com.arek.warehousetransfer.item.Item;
import com.arek.warehousetransfer.item.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class TransferService {

	// == fields ==
	private TransferRepository transferRepository;
	private ItemRepository itemRepository;

	// == public methods ==
	public List<Transfer> findAllTransfers() {
		return transferRepository.findAll();
	}

	public void saveTransfer(Transfer transfer) {
		transferRepository.save(transfer);
	}

	public Transfer populateTransferDataFromRequestMap(Map<String,String> requestMap, Transfer transfer) {
		List<TransferContent> transferContents = new ArrayList<>();
		requestMap.forEach((k,v)->{
			if(NumberUtils.isParsable(k)){
				Item itemToAdd = itemRepository.findById(NumberUtils.toLong(k)).orElse(null);
				int itemAmount = NumberUtils.toInt(v);
				TransferContent transferContent = TransferContent.of(itemToAdd, itemAmount);
				transferContents.add(transferContent);
			}
		});
		transfer.setTransferContents(transferContents);
		return transfer;
//		Enumeration<String> paramNames = req.getParameterNames();
//		while (paramNames.hasMoreElements()) {
//			String paramName = paramNames.nextElement();
//			if (NumberUtils.isParsable(paramName)) {
//				Item itemToAdd = itemRepository.findById(NumberUtils.toLong(paramName)).orElse(null);
//				int itemAmount = NumberUtils.toInt(req.getParameter(paramName));
//				TransferContent transferContent = TransferContent.of(itemToAdd, itemAmount);
//				transferContents.add(transferContent);
//			}
//		}
	}


	public Transfer findTransferById(Long id) {
		return transferRepository.findById(id).orElse(null);
	}
}
