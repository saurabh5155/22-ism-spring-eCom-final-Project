package com.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bean.AddressBean;

@Repository
public interface AddressRepository extends CrudRepository<AddressBean, Integer>{
	AddressBean findByAddressId(Integer addressId);
	List<AddressBean> findAll();
}
