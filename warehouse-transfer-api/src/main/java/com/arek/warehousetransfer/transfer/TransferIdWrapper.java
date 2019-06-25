package com.arek.warehousetransfer.transfer;

import lombok.Data;

@Data
public class TransferIdWrapper {
	private Long id;

	public static TransferIdWrapper empty(){
		return new TransferIdWrapper();
	}
}

