package com.springboot.ecomm.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.springboot.ecomm.model.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "Ecomm")
public class Category {
	
	private String category;
	private List<Product> products;

}