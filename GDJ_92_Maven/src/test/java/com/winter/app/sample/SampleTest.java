package com.winter.app.sample;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SampleTest {

	@Test
	void test() {
		// 0	=>  0
		// 1	=>	1
		// 2	=>	2
		// 3	=>  3
		// 4	=>  10
		// 5	=>  11
		// 6	=>  12
		// 7	=>  13
		// 9	=>  21
		// 10	=>  22
		
		int n = 1;
		
		int result = n/4*10 + n%4;
		System.out.println(result);
	}
	
	@Test
	public void test2() {
		// 편의점 
		int price = 37800;
		int money = 50000;
		//1	, 5
		//
		
		
		int man = (money-price)/10000;
		int ch = (money-price)%10000/1000;
		int bak = (money-price)%10000%1000/100;

		System.out.println("만원" + man + "장");
		System.out.println("천원" + ch + "장");
		System.out.println("백원" + bak + "장");
	}

}
