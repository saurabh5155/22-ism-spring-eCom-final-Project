package com.seed;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bean.RoleBean;
import com.repository.RoleRepository;

@Component
public class RoleSeed {
	
	@Autowired
	RoleRepository roleRepo;
	
	@PostConstruct
	void addRole() {
		RoleBean roleUser =roleRepo.findByRoleName("user");
		RoleBean roleAdmin = roleRepo.findByRoleName("admin");
		RoleBean roleVendor = roleRepo.findByRoleName("vendor");
		
		
		if(roleUser== null) {
			RoleBean roleBean= new RoleBean();
			roleBean.setRoleName("user");
			roleRepo.save(roleBean);
		} else {
			System.out.println("User In Alreadey in Database");
		}
		
		if(roleAdmin== null) {
			RoleBean roleBean= new RoleBean();
			roleBean.setRoleName("admin");
			roleRepo.save(roleBean);
		}else {
			System.out.println("Admin In Alreadey in Database");
			
		}
		
		if(roleVendor== null) {
			RoleBean roleBean= new RoleBean();
			roleBean.setRoleName("vendor");
			roleRepo.save(roleBean);
		}else {
			System.out.println("Vendor In Alreadey in Database");			
		}
	}
}
