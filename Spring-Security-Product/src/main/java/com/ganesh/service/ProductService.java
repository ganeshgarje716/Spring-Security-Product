package com.ganesh.service;

import java.util.List;

import com.ganesh.dto.ProductDto;
import com.ganesh.entity.Product;

public interface ProductService {
	
	
	ProductDto saveProduct(ProductDto productDto);
	
	List<Product> findAllProducts();
	
	Product findById(Integer id);

}
