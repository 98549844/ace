package com.service;

import com.dao.UserRolesDao;
import com.dao.UsersDao;
import com.models.entity.dao.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import util.*;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Classname: usersService
 * @Date: 1/7/2021 10:00 下午
 * @Author: garlam
 * @Description:
 */

@Service
public class UsersService implements UserDetailsService {
	private static Logger log = LogManager.getLogger(UsersService.class.getName());

	private UsersDao usersDao;
	private UserRolesDao userRolesDao;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UsersService(UsersDao usersDao, UserRolesDao userRolesDao, PasswordEncoder passwordEncoder) {
		this.usersDao = usersDao;
		this.userRolesDao = userRolesDao;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {
		log.info("***UsersService.loadUserByUsername {}", userAccount);
		UserDetails u;
		if ("admin".equals(userAccount)) {
			List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
			u = new User("admin", passwordEncoder.encode("admin"), auth);
		} else {
			Users user = usersDao.findByUserAccount(userAccount);
			String encode = passwordEncoder.encode(user.getPassword());
			u = new User(user.getUserName(), encode, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		}
		return u;
	}


	public Users findByUserAccount(Users param) {
		//	Users user = usersDao.findUsersByUserNameLike("%" + param.getUserName() + "%");
		Users user = usersDao.findByUserAccount(param.getUserAccount());
		if (NullUtil.isNull(user) || NullUtil.isNull(user.getUserId())) {
			////抛出异常，会根据配置跳到登录失败页面
			throw new UsernameNotFoundException("找不到该账户信息！");
		}
		String sp = user.getUserName() + "," + user.getPassword();
		//   UserDetails userDetails = this.loadUserByUsername(sp);
		boolean matches = passwordEncoder.matches(param.getPassword(), user.getPassword());
		log.info("Match result: {}", matches);
		if (!matches) {
			throw new BadCredentialsException("密码不正确");
		}
		return user;
	}


	public boolean save(Users users) {
		try {
//            if (!users.getPassword().contains("$2a$10$") && users.getPassword().length() != 60) {
//                String encode = passwordEncoder.encode(users.getPassword());
//                users.setPassword(encode);
//            }
			usersDao.save(users);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}


	public boolean validate(Users users) {
		boolean validate = true;
		if (NullUtil.isNotNull(users)) {
			if (NullUtil.isNull(users.getEmail())) {
				validate = false;
			} else if (NullUtil.isNull(users.getUserAccount().trim())) {
				validate = false;
			} else if (NullUtil.isNull(users.getPassword())) {
				validate = false;
			}
		}
		return validate;
	}


	/**
	 * @return all result
	 */
	public List<Users> getAll() {
		List<Users> list = null;
		try {
			list = usersDao.findAll();
		} catch (Exception e) {
			log.error(e);
		}
		return list;
	}

	public Integer countByUserAccountOrEmail(Users users) {
		Integer count = 0;
		try {
			count = usersDao.countByUserAccountOrEmail(users.getUserAccount(), users.getEmail());
		} catch (Exception e) {
			log.error(e);
		}
		return count;
	}


	public Optional<Users> getUsersById(long id) {
		return usersDao.findById(id);
	}


	public boolean saveAll(List<Users> usersIterable) {
		//   passwordEncoder.encode(usersIterable.iterator().next().getPassword());

		List<Users> usersList = new ArrayList<>();
		for (Users u : usersIterable) {
			String encode = passwordEncoder.encode(u.getPassword());
			u.setPassword(encode);
			usersList.add(u);
		}


		try {
			usersDao.saveAll(usersList);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}

	public boolean update(Users users) {
		try {
			if (users != null) {
				usersDao.update(users);
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}

		return true;
	}

	public boolean delete(Users users) {
		try {
			usersDao.delete(users);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(Long id) {
		try {
			usersDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteAll() {
		try {
			usersDao.deleteAll();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	private Specification<Users> toPredicate(Users users) {
		return (Specification<Users>) (root, query, criteriaBuilder) -> {
			List<Predicate> predicatesList = new ArrayList<>();
			if (users.getUserId() != null) {
				Predicate predicate = criteriaBuilder.equal(root.get("id"), users.getUserId());
				predicatesList.add(predicate);
			}
			if (users.getPassword() != null) {
				Predicate predicate = criteriaBuilder.equal(root.get("password"), users.getPassword());
				predicatesList.add(predicate);
			}
			if (users.getUserName() != null) {
				Predicate predicate = criteriaBuilder.like(root.get("userName"), "%" + users.getUserName().toLowerCase() + "%");
				predicatesList.add(predicate);
			}
			if (users.getStatus() != null) {
				Predicate predicate = criteriaBuilder.equal(root.get("status"), users.getStatus());
				predicatesList.add(predicate);
			}
			if (users.getEmail() != null) {
				Predicate predicate = criteriaBuilder.like(root.get("email"), "%" + users.getEmail().toLowerCase() + "%");
				predicatesList.add(predicate);
			}
			if (users.getMobile() != null) {
				Predicate predicate = criteriaBuilder.like(root.get("mobile"), "%" + users.getMobile() + "%");
				predicatesList.add(predicate);
			}
			if (users.getDomain() != null) {
				Predicate predicate = criteriaBuilder.like(root.get("domain"), "%" + users.getDomain().toLowerCase() + "%");
				predicatesList.add(predicate);
			}
			if (users.getIp() != null) {
				Predicate predicate = criteriaBuilder.like(root.get("ip"), "%" + users.getIp().toLowerCase() + "%");
				predicatesList.add(predicate);
			}
			if (users.getHostName() != null) {
				Predicate predicate = criteriaBuilder.like(root.get("hostName"), "%" + users.getHostName().toLowerCase() + "%");
				predicatesList.add(predicate);
			}
			if (users.getCreatedBy() != null) {
				Predicate predicate = criteriaBuilder.like(root.get("createdBy"), "%" + users.getCreatedBy() + "%");
				predicatesList.add(predicate);
			}
			if (users.getLastUpdatedBy() != null) {
				Predicate predicate = criteriaBuilder.like(root.get("lastUpdatedBy"), "%" + users.getLastUpdatedBy() + "%");
				predicatesList.add(predicate);
			}
			return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
		};
	}


}

