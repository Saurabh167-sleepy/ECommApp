package com.springboot.mongodb.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.springboot.ecomm.model.Category;

@Repository
public interface CategoryRepo extends MongoRepository<Category,String>{

}
