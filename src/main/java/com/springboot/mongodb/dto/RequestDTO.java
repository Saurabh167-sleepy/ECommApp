package com.springboot.mongodb.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO {
	
	private String category;	
	private String product;
	private List<String> manufacturers;

}
