package com.arek.warehousetransfer.transfer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;

@Getter
@Setter
@NoArgsConstructor
public class TransferAndRequest {

	private Transfer transfer;
	private HttpServletRequest req;

	public static TransferAndRequest empty(){
		return new TransferAndRequest();
	}
}
