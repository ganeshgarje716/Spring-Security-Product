package com.ganesh.service.imp;

import org.springframework.stereotype.Service;

import com.ganesh.dto.ProductDto;
import com.ganesh.entity.Product;
import com.ganesh.repository.ProductRepository;
import com.ganesh.service.ProductService;

@Service
public class ProductServiceImp implements ProductService{
	
	
	private final ProductRepository productRepository;
	
	
	public ProductServiceImp(ProductRepository productRepository) {
		
		this.productRepository=productRepository;
	}
	
	

	@Override
	public ProductDto saveProduct(ProductDto productDto) {
		
		Product product=new Product();
		
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		product.setDiscount(productDto.getDiscount());
		product.setStock(productDto.getStock());
		product.setCategory(productDto.getCategory());
		product.setBrand(productDto.getBrand());
		product.setRating(productDto.getRating());
		product.setAvailable(productDto.getAvailable());
		
		Product saved = productRepository.save(product);
		
		productDto.setId(saved.getId());
		productDto.setName(saved.getName());
		productDto.setPrice(saved.getPrice());
		productDto.setDiscount(saved.getDiscount());
		productDto.setStock(saved.getStock());
		productDto.setCategory(saved.getCategory());
		productDto.setBrand(saved.getBrand());
		productDto.setRating(saved.getRating());
		productDto.setAvailable(saved.getAvailable());
		productDto.setCreatedDate(saved.getCreatedDate());
		
		return productDto;
	}

}
