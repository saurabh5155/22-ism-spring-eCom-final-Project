package com.bean;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "address")
public class AddressBean {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	private String houseDetail;
	private String city;
	private String state;
	private Integer addressCode;
	
	@ManyToOne
	@JoinColumn(name = "userId",nullable = false)
	private UserBean users;
	
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getHouseDetail() {
		return houseDetail;
	}
	public void setHouseDetail(String houseDetail) {
		this.houseDetail = houseDetail;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(Integer addressCode) {
		this.addressCode = addressCode;
	}
	public UserBean getUsers() {
		return users;
	}
	public void setUsers(UserBean users) {
		this.users = users;
	}
	
}
