package com.arek.warehousetransfer.transfer;

import com.arek.warehousetransfer.item.Item;
import com.arek.warehousetransfer.item.ItemRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

	public Transfer populateTransferDataFromRequestParameters(HttpServletRequest req, Transfer transfer) {
//		try {
		List<TransferContent> transferContents = new ArrayList<>();
		Enumeration<String> paramNames = req.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			if (NumberUtils.isParsable(paramName)) {
				Item itemToAdd = itemRepository.findById(NumberUtils.toLong(paramName)).orElse(null);
				int itemAmount = NumberUtils.toInt(req.getParameter(paramName));
				TransferContent transferContent = TransferContent.empty();
				transferContent.setItem(itemToAdd);
				transferContent.setAmount(itemAmount);
				transferContents.add(transferContent);
			}
		}
		transfer.setTransferContents(transferContents);
		return transfer;
//		req.getParameterNames().asIterator().forEachRemaining(s -> {
//			if (NumberUtils.isParsable(s)) {
//				log.info("=========================" + s + " : " + req.getParameter(s));
//				Item itemToAdd = itemRepository.findById(NumberUtils.toLong(s)).orElse(null);
//				int itemAmount = NumberUtils.toInt(req.getParameter(s));
//				TransferContent transferContent = TransferContent.empty();
//				transferContent.setItem(itemToAdd);
//				transferContent.setAmount(itemAmount);
//				transfer.getTransferContents().add(transferContent);
//			}
//		});
//		return transfer;
//
//			});
//		} catch (NullPointerException e) {
//			log.info("Iterator empty: " + e.getMessage());
//		}
	}


	public Transfer findTransferById(Long id) {
		return transferRepository.findById(id).orElse(null);
	}
}
