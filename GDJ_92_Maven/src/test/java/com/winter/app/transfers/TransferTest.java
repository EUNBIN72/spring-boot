package com.winter.app.transfers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.transfer.Pay;
import com.winter.app.transfer.Transfers;

@SpringBootTest
public class TransferTest {
	
	@Autowired
	private Transfers transfers;
	
	@Autowired
	private Pay pay;
	
	@Test
	void test() {
		
		transfers.takeBus();
		
		transfers.takeSubWay();
		
	}

}
