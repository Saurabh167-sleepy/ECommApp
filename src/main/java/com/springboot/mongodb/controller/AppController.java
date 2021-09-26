package com.springboot.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecomm.model.Category;
import com.springboot.ecomm.model.Product;
import com.springboot.mongodb.dto.RequestDTO;
import com.springboot.mongodb.service.AppService;

@RestController
public class AppController {
	
	@Autowired
	private AppService as;
	
	// enters data into DB as Document representing Category Object
	@PostMapping(path = "/createDoc")
	public ResponseEntity<HttpStatus> createDocument(@RequestBody List<RequestDTO> rdto){
		return as.createDocument(rdto);
	}
	
	// fetches all Category Document present in Collection
	@GetMapping(path = "/fetchAllDocs")
	public ResponseEntity<List<Category>> fetchAllDocs(){
		return as.fetchAllDocs();
	}
	
	// fetches all Category Documents having category present in API Call's path variable
    @GetMapping(path = "/getCategory/{category}")
	public ResponseEntity<Category> fetchACategory(@PathVariable String category){
	return as.fetchACategory(category);
	}	
	
	// fetches data for particular product
    // UNDER FIRST CATEGORY WHERE IT'S FOUND IN COLLECTION, hence it returns 'Category' object
	@GetMapping(path = "/getProduct/{productName}")
	public ResponseEntity<Category> fetchAProduct(@PathVariable String productName){
	return as.fetchAProduct(productName);
	}
}
