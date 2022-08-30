package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.RoleBean;
import com.bean.UserBean;
import com.repository.RoleRepository;
import com.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	BCryptPasswordEncoder bcrypt;

	@GetMapping("/users")
	public ResponseEntity<?> getAllUsers() {
		ResponseBean<UserBean> res = new ResponseBean<>();
		List<UserBean> users = userRepo.findAll();
		res.setListData(users);
		res.setMsg("User found successfull");
		res.setCode(1);
		return ResponseEntity.ok().body(res);
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable("userId") int userId) {
		ResponseBean<UserBean> res = new ResponseBean<>();
		UserBean user = userRepo.findByUserId(userId);
		if (user == null) {
			res.setData(user);
			res.setMsg("User not found ");
			res.setCode(-1);
			return ResponseEntity.badRequest().body(res);
		} else {

			res.setData(user);
			res.setMsg("User found successfull");
			res.setCode(1);
			return ResponseEntity.ok().body(res);
		}
	}

	@DeleteMapping("/users/{userId}")
	public ResponseEntity<?> deleteUserById(@PathVariable("userId") int userId) {
		ResponseBean<UserBean> res = new ResponseBean<>();
		UserBean user = userRepo.findByUserId(userId);
		if (user == null) {
			res.setData(user);
			res.setMsg("User not found ");
			res.setCode(-1);
			return ResponseEntity.badRequest().body(res);
		} else {
			res.setData(user);
			res.setMsg("User Deleted");
			res.setCode(1);
			userRepo.delete(user);
			return ResponseEntity.ok().body(res);
		}
	}

	@PutMapping("/users")
	public ResponseEntity<?> updateUser(@RequestBody UserBean userBean) {
		ResponseBean<UserBean> res = new ResponseBean<>();
		try {
			RoleBean role = roleRepo.findByRoleName("user");
			userBean.setRoles(role);
			userBean.setIsActive(true);
			userBean.setPassword(bcrypt.encode(userBean.getPassword()));
			userRepo.save(userBean);

			res.setData(userBean);
			res.setMsg("User Added");
			res.setCode(1);

			return ResponseEntity.ok().body(res);

		} catch (Exception e) {
			res.setData(userBean);
			res.setMsg("SWW");
			res.setCode(-1);
			System.out.println("Some Exception");
			return ResponseEntity.badRequest().body(res);
		}

	}

}
