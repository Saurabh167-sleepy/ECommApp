package com.springboot.ecomm.model;

import java.util.List;

import com.springboot.ecomm.model.Manufacturer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
	
	private String product;
	private List<Manufacturer> manufacturers;
	
}