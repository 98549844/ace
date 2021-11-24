package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class Ace {
	private static Logger log = LogManager.getLogger(Ace.class.getName());




	public static void main(String[] args) {

		//进行密码对比第一个参数为未加密密码,第二个参数为加密后数据库中的密码
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
		for (int i = 0; i < 20 ; i++) {
			String password = passwordEncoder.encode("909394");
			System.out.println(password.length()+"   "+password);

		}
		boolean b = passwordEncoder.matches("909394", "$2a$10$5LY2SS1ctXx9pHxtExySn.uqY5Ok3VujLoG2QsCDnsmcIxAQxp6VG");
		System.out.println(b);
	}


}
