package com.springboot.mongodb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.ecomm.model.Category;
import com.springboot.ecomm.model.Manufacturer;
import com.springboot.ecomm.model.Product;
import com.springboot.mongodb.dto.RequestDTO;
import com.springboot.mongodb.repo.CategoryRepo;

@Service
public class AppService {
	
	@Autowired
	private CategoryRepo cr;
	
	public ResponseEntity<HttpStatus> createDocument(List<RequestDTO> lrdto){
		Optional<List<RequestDTO>> optDTO = Optional.ofNullable(lrdto);
		if(!optDTO.isPresent()) {
			return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
		}
		for(RequestDTO rdto : lrdto) {
			Category c = new Category();
			Product p =new Product();
			List<Category> csl = new ArrayList();
			List<Product> ps = new ArrayList();
			List<Manufacturer> ms = new ArrayList();
			
			for(String manufacturer : rdto.getManufacturers()) {
				Manufacturer m = new Manufacturer();
				m.setManufacturer(manufacturer);
				ms.add(m);
			}
			
			p.setProduct(rdto.getProduct());
			p.setManufacturers(ms);
			
			c.setCategory(rdto.getCategory());
			ps.add(p);
			c.setProducts(ps);
			
			csl.add(c);
			cr.save(c);
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}
	
	public ResponseEntity<List<Category>> fetchAllDocs(){
		if(Optional.ofNullable(cr.findAll()).isPresent()) {
			return new ResponseEntity<List<Category>>(cr.findAll(),HttpStatus.OK);			
		}
		return new ResponseEntity<List<Category>>(HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<Category> fetchACategory(String category){
		List<Category> allCategories = cr.findAll();
		if(!Optional.ofNullable(allCategories).isPresent()) {
			return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);			
		}
		boolean categoryFound = false;
		List<Product> plist = new ArrayList();
		Category reqCategory = new Category();
		
		reqCategory.setCategory(category);		
	    for(Category c : allCategories) {
	    	if(c.getCategory().equals(category)) {
	    		categoryFound = true;
	    	plist.addAll(c.getProducts());
	    	}
	    }
	    if(!categoryFound) {
	    	return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
	    }
	    reqCategory.setProducts(plist);
	    return new ResponseEntity<Category>(reqCategory,HttpStatus.OK);
	    }
	
	public ResponseEntity<Category> fetchAProduct(String product){
		List<Category> allCategories = cr.findAll();
		if(!Optional.ofNullable(allCategories).isPresent()) {
			return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);			
		}
		boolean productFound = false;
		List<Manufacturer> manufacturers = new ArrayList();
		List<Product> products = new ArrayList();
		List<String> tempManufacturer = new ArrayList();
		Category c = new Category();
		Product reqProduct = new Product();	    
		reqProduct.setProduct(product);
	    for(Category category : allCategories) {
	   		List<Product> prods = category.getProducts();
    		for(Product p : prods) {
    			if(p.getProduct().equals(product)) {
    				productFound = true;
    				c.setCategory(category.getCategory());
    				for(Manufacturer m : p.getManufacturers()) {
    					if(!tempManufacturer.contains(m.getManufacturer())) {
    						tempManufacturer.add(m.getManufacturer());	
    					}
    				}
		    	}	    			
	    	}
	    }
	    
	    if(!productFound) {
	    	return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
	    }
	    
	    for(String manufacturer : tempManufacturer) {
	    	Manufacturer m =new Manufacturer();
	    	m.setManufacturer(manufacturer);
	    	manufacturers.add(m);
	    }
	    reqProduct.setManufacturers(manufacturers);
	    products.add(reqProduct);
	    c.setProducts(products);
	    return new ResponseEntity<Category>(c,HttpStatus.OK);
	}

}
