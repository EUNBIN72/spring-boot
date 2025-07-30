package com.winter.app.factory;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component
//@Primary
public class GunArm implements Arm {
	
	@Override
	public void attack() {
		System.out.println("gun");
	}
	

}
