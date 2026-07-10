package com.ganesh.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ganesh.dto.ProductDto;
import com.ganesh.entity.Product;
import com.ganesh.exception.ProductNotFoundException;
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



	@Override
	public List<Product> findAllProducts() {

		return productRepository.findAll();
	}



	@Override
	public Product findById(Integer id) {

		Optional<Product> byId = productRepository.findById(id);
		
		if (byId.isPresent()) {
			
			Product product = byId.get();
			
			return product;
		}
		
		throw new ProductNotFoundException("Product Not Found With Id = "+id);
	}



	@Override
	public String updateById(Integer id, Product product) {
		
		Optional<Product> byId = productRepository.findById(id);
		
		if (byId.isPresent()) {
			
			Product existing = byId.get();
			
			existing.setName(product.getName());
			existing.setPrice(product.getPrice());
			existing.setDiscount(product.getDiscount());
			existing.setStock(product.getStock());
			existing.setCategory(product.getCategory());
			existing.setBrand(product.getBrand());
			existing.setRating(product.getRating());
			existing.setAvailable(product.getAvailable());
			
			productRepository.save(existing);
			
			return "Product Update Success";
		}
		
		throw new ProductNotFoundException("Product Not Foun With Id = "+id);
	}

}
