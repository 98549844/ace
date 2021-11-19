package com.service;

import com.controller.LoginController;
import com.dao.UserRolesDao;
import com.dao.UsersDao;
import com.models.entity.dao.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.BadCredentialsException;
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
	public UserDetails loadUserByUsername(String param) throws UsernameNotFoundException {
		//Users user = usersDao.findUsersByUserNameLike("%" + userName + "%");
		String[] sp = param.split(",");
		String userName = sp[0];
		String password = sp[1];
		UserDetails u = new User(userName, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		return u;

	}


	public Users getUserByUserName(Users param) {
		Users user = usersDao.findUsersByUserNameLike("%" + param.getUserName() + "%");
		if (NullUtil.isNull(user) || NullUtil.isNull(user.getUserId())) {
			////抛出异常，会根据配置跳到登录失败页面
			throw new UsernameNotFoundException("找不到该账户信息！");
		}
		String sp = user.getUserName() + "," + user.getPassword();
		UserDetails userDetails = this.loadUserByUsername(sp);
		boolean matches = passwordEncoder.matches(param.getPassword(), userDetails.getPassword());
		log.info("Match result: {}",matches);
		if (!matches) {
			throw new BadCredentialsException("密码不正确");
		}
		return user;
	}


	public boolean save(Users users) {
		try {
			users.setPassword(passwordEncoder.encode(users.getPassword()));
			usersDao.save(users);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		return true;
	}


	public boolean validate(Users users) {
		boolean validate = false;
		if (NullUtil.isNotNull(users)) {
			if (NullUtil.isNull(users.getEmail())) {
				validate = true;
			} else if (NullUtil.isNull(users.getUserName())) {
				validate = true;
			} else if (NullUtil.isNull(users.getPassword())) {
				validate = true;
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

	public List<Users> getUsers(Users users) {
		List<Users> list = null;
		try {
			Specification<Users> sp = toPredicate(users);
			list = usersDao.findAll(sp);
		} catch (Exception e) {
			log.error(e);
		}
		return list;
	}


	public Optional<Users> getUsersById(long id) {
		return usersDao.findById(id);
	}


	public boolean saveAll(Iterable<Users> usersIterable) {
		try {
			usersDao.saveAll(usersIterable);
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
			List<Users> list = getUsers(users);
			for (Users user : list) {
				delete(user.getUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
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
				Predicate predicate = criteriaBuilder.like(root.get("createdBy"), "%" + users.getCreatedBy().toLowerCase() + "%");
				predicatesList.add(predicate);
			}
			if (users.getLastUpdatedBy() != null) {
				Predicate predicate = criteriaBuilder.like(root.get("lastUpdatedBy"), "%" + users.getLastUpdatedBy().toLowerCase() + "%");
				predicatesList.add(predicate);
			}
			return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
		};
	}


}

