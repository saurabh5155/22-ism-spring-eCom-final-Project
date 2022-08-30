package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bean.AddressBean;
import com.repository.AddressRepository;

@RestController
public class AddressController {

	@Autowired
	AddressRepository addressRepo;

	@PostMapping("/address")
	public ResponseEntity<?> addaddress(@RequestBody AddressBean addressBean) {
		System.out.println(addressBean.getUsers());
		addressRepo.save(addressBean);
		return ResponseEntity.ok().body(addressBean);
	}

	@GetMapping("/address")
	public ResponseEntity<?> getAllAddress() {
		List<AddressBean> addresses = addressRepo.findAll();
		return ResponseEntity.ok().body(addresses);
	}
	
	@GetMapping("/address/{addressId}")
	public ResponseEntity<?> getAddressById(@PathVariable("addressId") Integer addressId){
		AddressBean address= addressRepo.findByAddressId(addressId);		
		return ResponseEntity.ok().body(address);
	}
	
	@DeleteMapping("/address/{addressId}")
	public ResponseEntity<?> deleteAddressById(@PathVariable("addressId") Integer addressId){
		AddressBean address= addressRepo.findByAddressId(addressId);
		if(address!=null) {
			addressRepo.delete(address);			
			return ResponseEntity.ok().body(address);
		}else {			
			return ResponseEntity.badRequest().body(address);
		}
	}

	@PutMapping("/address")
	public ResponseEntity<?> updateAddress(@RequestBody AddressBean addressBean) {
		System.out.println(addressBean.getUsers());
		addressRepo.save(addressBean);
		return ResponseEntity.ok().body(addressBean);
	}
}
