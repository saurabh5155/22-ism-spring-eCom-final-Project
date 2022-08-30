package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public interface UserRepository extends CrudRepository<UserBean, Integer> {
	UserBean findByEmail(String email);

	UserBean findByUserId(Integer userId);

	List<UserBean> findAll();
//	UserBean findByMNumber(Long mNumber);
}
