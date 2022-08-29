package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.RoleBean;
import com.repository.RoleRepository;

@RestController
public class RoleController {
	
	@Autowired
	RoleRepository roleRepo;
	
	@PostMapping("/roles")
	public ResponseEntity<?> addRole(@RequestBody RoleBean roleBean){
		roleRepo.save(roleBean);
		return ResponseEntity.ok().body(roleBean);
	}
	
	@GetMapping("/roles")
	public ResponseEntity<?> getAllRoles(){
		List<RoleBean> roles = roleRepo.findAll();
		return ResponseEntity.ok().body(roles);
	}
	
	@GetMapping("/roles/{roleId}")
	public ResponseEntity<?> getRoleById(@PathVariable("roleId") Integer roleId){
		RoleBean role = roleRepo.findByRoleId(roleId);
		return ResponseEntity.ok().body(role);
	}
	
	@DeleteMapping("/roles/{roleId}")
	public ResponseEntity<?> deleteRole(@PathVariable("roleId") Integer roleId){
		RoleBean role = roleRepo.findByRoleId(roleId);
		if(role==null) {			
			return ResponseEntity.ok().body(role);
		}else {
			roleRepo.delete(role);
			return ResponseEntity.ok().body(role);
		}
	}
	
	@PutMapping("/roles")
	public ResponseEntity<?> updateRole(@RequestBody RoleBean roleBean){
		RoleBean role = roleRepo.findByRoleId(roleBean.getRoleId());
		if(role==null) {			
			return ResponseEntity.ok().body(role);
		}else {
			roleRepo.save(roleBean);
			return ResponseEntity.ok().body(role);
		}
	}
}