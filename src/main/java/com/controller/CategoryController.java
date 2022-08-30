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

import com.bean.CategoryBean;
import com.repository.CategoryRepository;

@RestController
public class CategoryController {

	@Autowired
	CategoryRepository catgoryRepo;

	@PostMapping("/category")
	public ResponseEntity<?> addCategory(@RequestBody CategoryBean categoryBean){
		catgoryRepo.save(categoryBean);
		return ResponseEntity.ok().body(categoryBean);
	}
	
	@GetMapping("/category")
	public ResponseEntity<?> getAllRoles(){
		List<CategoryBean> category = catgoryRepo.findAll();
		return ResponseEntity.ok().body(category);
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<?> getRoleById(@PathVariable("categoryId") Integer categoryId){
		CategoryBean categoryBean = catgoryRepo.findByCategoryId(categoryId);
		if(categoryBean==null) {
			return ResponseEntity.badRequest().body(categoryBean);
		}else {
			return ResponseEntity.ok().body(categoryBean);
		}
	}
	
	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<?> deleteRoleById(@PathVariable("categoryId") Integer categoryId){
		CategoryBean categoryBean = catgoryRepo.findByCategoryId(categoryId);
		if(categoryBean==null) {
			return ResponseEntity.badRequest().body(categoryBean);
		}else {
			catgoryRepo.delete(categoryBean);
			return ResponseEntity.ok().body("Category Deleted");
		}
	}
	
	@PutMapping("/category")
	public ResponseEntity<?> updateCategory(@RequestBody CategoryBean categoryBean){
		catgoryRepo.save(categoryBean);
		return ResponseEntity.ok().body(categoryBean);
	}
}
