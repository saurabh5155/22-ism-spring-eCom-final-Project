package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.LoginBean;
import com.bean.ResponseBean;
import com.bean.RoleBean;
import com.bean.UserBean;
import com.repository.RoleRepository;
import com.repository.UserRepository;

@RestController
public class SessionController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	BCryptPasswordEncoder bcrypt;

	@PostMapping("/signup")
	public ResponseEntity<?> addUser(@RequestBody UserBean userBean) {
		ResponseBean<UserBean> res = new ResponseBean<>();
		try {
			UserBean userEmail = userRepo.findByEmail(userBean.getEmail());
//			Long mNumber = userBean.getmNumber();
//			UserBean userMNumber = userRepo.findByMNumber(mNumber);

			if (userEmail == null) {
				RoleBean role = roleRepo.findByRoleName("user");
				userBean.setRoles(role);
				userBean.setIsActive(true);
				userBean.setPassword(bcrypt.encode(userBean.getPassword()));
				userRepo.save(userBean);

				res.setData(userBean);
				res.setMsg("User Added");
				res.setCode(1);

				return ResponseEntity.ok().body(res);
			} else {

				res.setData(userBean);
				res.setMsg("Email Already Exits");
				res.setCode(-1);
				return ResponseEntity.badRequest().body(res);
			}
		} catch (Exception e) {
			res.setData(userBean);
			res.setMsg("SWW");
			res.setCode(-1);
			System.out.println("Some Exception");
			return ResponseEntity.badRequest().body(res);
		}

	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginBean loginBean) {
		ResponseBean<UserBean> resUser = new ResponseBean<>();
		UserBean user = userRepo.findByEmail(loginBean.getEmail());
		if (user != null) {
			if (user.getEmail().equals(loginBean.getEmail())
					&& bcrypt.matches(loginBean.getPassword(), user.getPassword())) {
				resUser.setData(user);
				resUser.setMsg("Login Done");
				resUser.setCode(1);
				return ResponseEntity.ok().body(resUser);
			} else {
				resUser.setData(user);
				resUser.setMsg("Invalid Email or Password");
				resUser.setCode(-1);
				return ResponseEntity.ok().body(resUser);
			}
		} else {
			resUser.setData(user);
			resUser.setMsg("Please signup first");
			resUser.setCode(-1);
			return ResponseEntity.ok().body(resUser);
		}
	}
}
