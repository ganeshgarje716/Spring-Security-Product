package com.ganesh.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ganesh.dto.ProductDto;
import com.ganesh.entity.Product;
import com.ganesh.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductRestController {
	
	
	private final ProductService productService;
	
	
	public ProductRestController(ProductService productService) {
		
		this.productService=productService;
	}
	
	
	
	@PostMapping("/save")
	public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto) {
		
		ProductDto saved = productService.saveProduct(productDto);
		
		return new ResponseEntity<ProductDto>(saved, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> findAllProducts() {
		
		List<Product> allProducts = productService.findAllProducts();
		
		return new ResponseEntity<List<Product>>(allProducts, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/byid/{id}")
	public ResponseEntity<Product> findById(@PathVariable Integer id) {
		
		Product byId = productService.findById(id);
		
		return new ResponseEntity<Product>(byId, HttpStatus.OK);
	}
	
	
	
	@PutMapping("/update")
	public ResponseEntity<String> updateById(@PathVariable Integer id, @RequestBody Product product) {
		
		String updateById = productService.updateById(id, product);
		
		return new ResponseEntity<String>(updateById, HttpStatus.OK);
	}

}
