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


	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);
		String pw = passwordEncoder.encode("909394");
		System.out.println(pw);

	}


}
