package com;

import com.google.gson.Gson;
import com.models.entity.dao.Menu;
import net.sf.jasperreports.engine.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Ace {
	private static Logger log = LogManager.getLogger(Ace.class.getName());


//	public static void main(String[] args) {
//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
//		String pw = passwordEncoder.encode("909394");
//		System.out.println(pw);
//
//	}


	private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	//加密,密码
	public static String encodePassword(String password){
		return bCryptPasswordEncoder.encode(password);
	}

	public static void main(String[] args) {
		String str = "909394";
		String password = encodePassword(str);
		System.out.println(password);
		//进行密码对比第一个参数为未加密密码,第二个参数为加密后数据库中的密码
		boolean b = bCryptPasswordEncoder.matches("909394", "$2a$11$7nuTCVhHRtpctpBam50Yr.2Breu0upOHSYiWCwwr1.E/efMMJQNLG");
		System.out.println(b);
	}


}
