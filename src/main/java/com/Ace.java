package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class Ace {
	private static Logger log = LogManager.getLogger(Ace.class.getName());




	public static void main(String[] args) {
		//进行密码对比第一个参数为未加密密码,第二个参数为加密后数据库中的密码
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String str = "909394";
		for (int i = 0; i < 10 ; i++) {
			String password = passwordEncoder.encode(str);
			System.out.println(password);

		}
		boolean b = passwordEncoder.matches("909394", "$2a$08$yN1CpSMd1axnrqDVQXWYxulE1dFt5L3KnoRr9PzbUWj0tQ4Uihtm2");
		System.out.println(b);
	}


}
