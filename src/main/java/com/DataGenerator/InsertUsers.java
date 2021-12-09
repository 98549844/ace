package com.DataGenerator;

import com.models.entity.dao.Users;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InsertUsers {

	public static List<Users> insertUsers() {
		List<Users> usersList = new ArrayList<>();
		Users u1 = new Users();
		Users u2 = new Users();
		Users u3 = new Users();
		Users u4 = new Users();
		Users u5 = new Users();
		Users u6 = new Users();
		Users u7 = new Users();




		u1.setBirthday(LocalDateTime.now());
		u2.setBirthday(LocalDateTime.now());
		u3.setBirthday(LocalDateTime.now());
		u4.setBirthday(LocalDateTime.now());
		u5.setBirthday(LocalDateTime.now());
		u6.setBirthday(LocalDateTime.now());
		u7.setBirthday(LocalDateTime.now());

		u1.setExpireDate(LocalDateTime.now());
		u2.setExpireDate(LocalDateTime.now());
		u3.setExpireDate(LocalDateTime.now());
		u4.setExpireDate(LocalDateTime.now());
		u5.setExpireDate(LocalDateTime.now());
		u6.setExpireDate(LocalDateTime.now());
		u7.setExpireDate(LocalDateTime.now());

		u1.setCreatedDate(LocalDateTime.now());
		u2.setCreatedDate(LocalDateTime.now());
		u3.setCreatedDate(LocalDateTime.now());
		u4.setCreatedDate(LocalDateTime.now());
		u5.setCreatedDate(LocalDateTime.now());
		u6.setCreatedDate(LocalDateTime.now());
		u7.setCreatedDate(LocalDateTime.now());

		u1.setCreatedBy(1001l);
		u2.setCreatedBy(2001l);
		u3.setCreatedBy(3001l);
		u4.setCreatedBy(4001l);
		u5.setCreatedBy(5001l);
		u6.setCreatedBy(6001l);
		u7.setCreatedBy(7001l);

		u1.setDescription("Administrator");
		u2.setDescription("Information");
		u3.setDescription("Editor");
		u4.setDescription("Disable");
		u5.setDescription("Logger");
		u6.setDescription("Information");
		u7.setDescription("Disable");

		u1.setEmail("garlam_au@qq.com");
		u2.setEmail("peter_wong@qq.com");
		u3.setEmail("mary_leeg@qq.com");
		u4.setEmail("kalam@qq.com");
		u5.setEmail("may_tang@qq.com");
		u6.setEmail("frank_chow@qq.com");
		u7.setEmail("eric_luk@qq.com");

		u1.setGender("M");
		u2.setGender("M");
		u3.setGender("M");
		u4.setGender("M");
		u5.setGender("M");
		u6.setGender("M");
		u7.setGender("M");


		u1.setLastUpdatedBy(1001l);
		u2.setLastUpdatedBy(2001l);
		u3.setLastUpdatedBy(3001l);
		u4.setLastUpdatedBy(4001l);
		u5.setLastUpdatedBy(5001l);
		u6.setLastUpdatedBy(6001l);
		u7.setLastUpdatedBy(7001l);


		u1.setLastUpdateDate(LocalDateTime.now());
		u2.setLastUpdateDate(LocalDateTime.now());
		u3.setLastUpdateDate(LocalDateTime.now());
		u4.setLastUpdateDate(LocalDateTime.now());
		u5.setLastUpdateDate(LocalDateTime.now());
		u6.setLastUpdateDate(LocalDateTime.now());
		u7.setLastUpdateDate(LocalDateTime.now());


		u1.setMobile("55550000");
		u2.setMobile("55550000");
		u3.setMobile("55550000");
		u4.setMobile("55550000");
		u5.setMobile("55550000");
		u6.setMobile("55550000");
		u7.setMobile("55550000");


		u1.setPassword("909394");
		u2.setPassword("909394");
		u3.setPassword("909394");
		u4.setPassword("909394");
		u5.setPassword("909394");
		u6.setPassword("909394");
		u7.setPassword("909394");


		u1.setEnabled(true);
		u2.setEnabled(true);
		u3.setEnabled(true);
		u4.setEnabled(true);
		u5.setEnabled(true);
		u6.setEnabled(true);
		u7.setEnabled(true);



		u1.setUserAccount("garlam");
		u2.setUserAccount("peter");
		u3.setUserAccount("mary");
		u4.setUserAccount("kalam");
		u5.setUserAccount("may");
		u6.setUserAccount("frank");
		u7.setUserAccount("eric");

		u1.setUsername("Garlam Au");
		u2.setUsername("Peter Wong");
		u3.setUsername("Mary Lee");
		u4.setUsername("Ka Lam");
		u5.setUsername("May Tang");
		u6.setUsername("Frank Chow");
		u7.setUsername("Eric Luk");

		usersList.add(u1);
		usersList.add(u2);
		usersList.add(u3);
		usersList.add(u4);
		usersList.add(u5);
		usersList.add(u6);
		usersList.add(u7);

		return usersList;
	}
}
