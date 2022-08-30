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
import com.bean.SubCategoryBean;
import com.repository.CategoryRepository;
import com.repository.SubCategoryRepository;

@RestController
public class SubCategoryController {

	@Autowired
	SubCategoryRepository subCatRepo;
	
	@Autowired
	CategoryRepository catgoryRepo;

	@PostMapping("/subCategory")
	public ResponseEntity<?> addSubCat(@RequestBody SubCategoryBean subCategoryBean){
		subCatRepo.save(subCategoryBean);
		return ResponseEntity.ok().body(subCategoryBean);
	}
	
	@GetMapping("/subCategory")
	public ResponseEntity<?> getAllSubCat(){
		List<SubCategoryBean> subCategory = subCatRepo.findAll();
		return ResponseEntity.ok().body(subCategory);
	}
	
	@GetMapping("/subCategory/{subCategoryId}")
	public ResponseEntity<?> getRoleById(@PathVariable("subCategoryId") Integer subCategoryId){
		SubCategoryBean subCategoryBean = subCatRepo.findBySubCategoryId(subCategoryId);
		if(subCategoryBean==null) {
			return ResponseEntity.badRequest().body(subCategoryBean);
		}else {
			return ResponseEntity.ok().body(subCategoryBean);
		}
	}
	
	@GetMapping("/subCategoryFromCategoryId/{categoryId}")
	public ResponseEntity<?> getSubCategoryByCategoryId(@PathVariable("categoryId") Integer categoryId){
		CategoryBean categoryBean = catgoryRepo.findByCategoryId(categoryId); 
		if(categoryBean!=null) {			
			List<SubCategoryBean> subCategoryBean = subCatRepo.findByCategory(categoryBean);
			if(subCategoryBean==null) {
				return ResponseEntity.badRequest().body(subCategoryBean);
			}else {
				return ResponseEntity.ok().body(subCategoryBean);
			}
		}else {
			return ResponseEntity.ok().body(categoryBean);
		}
		
	}
	
	@DeleteMapping("/subCategory/{subCategoryId}")
	public ResponseEntity<?> deleteRoleById(@PathVariable("subCategoryId") Integer subCategoryId){
		SubCategoryBean subCategoryBean = subCatRepo.findBySubCategoryId(subCategoryId);
		if(subCategoryBean==null) {
			return ResponseEntity.badRequest().body(subCategoryBean);
		}else {
			subCatRepo.delete(subCategoryBean);
			return ResponseEntity.ok().body(subCategoryBean);
		}
	}
	
	@PutMapping("/subCategory")
	public ResponseEntity<?> updateSubCat(@RequestBody SubCategoryBean subCategoryBean){
		subCatRepo.save(subCategoryBean);
		return ResponseEntity.ok().body(subCategoryBean);
	}
}
