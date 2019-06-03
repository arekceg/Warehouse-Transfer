package com.arek.warehousetransfer.transfer;

import com.arek.warehousetransfer.item.Item;
import com.arek.warehousetransfer.item.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TransferService {

	// == fields ==
	private TransferRepository transferRepository;
	private ItemService itemService;

	// == public methods ==
	public List<Transfer> findAllTransfers() {
		return transferRepository.findAll();
	}

	public void saveTransfer(Transfer transfer) {
		transferRepository.save(transfer);
	}

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


	public Transfer findTransferById(Long id) {
		return transferRepository.findById(id).orElse(null);
	}
}
