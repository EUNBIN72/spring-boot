package com.winter.app.lamda;


import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LamdaTest {

	@Test
	void test() {
		int n1 = 10;
		int n2 = 10;
		TestFunction testFunction = (int a, int b)-> a+b;
		int result = testFunction.f1(n1, n2);
		
		// 내장
		Consumer<Integer> con = (n)-> System.out.println(n);
		
		con.accept(3);
		
		System.out.println(result);
	}

}
