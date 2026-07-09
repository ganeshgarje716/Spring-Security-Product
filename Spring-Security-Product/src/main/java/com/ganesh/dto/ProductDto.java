package com.ganesh.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDto {
	
	
	
	private Integer id;
	
	@NotNull(message = "Name Is Required")
	private String name;
	
	@NotNull(message = "Price Is Required")
	private Double price;
	
	@NotNull(message = "Discount Is Required")
	private Double discount;
	
	@NotNull(message = "Stock Is Required")
	private Integer stock;
	
	@NotNull(message = "Category Is Required")
	private String category;
	
	@NotNull(message = "Brand Is Required")
	private String brand;
	
	@NotNull(message = "Rating Is Required")
	private Double rating;
	
	@NotNull(message = "Available Is Required")
	private Boolean available;
	
	private LocalDate createdDate;

}
