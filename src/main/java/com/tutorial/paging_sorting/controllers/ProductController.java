package com.tutorial.paging_sorting.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.paging_sorting.dto.ApiResponse;
import com.tutorial.paging_sorting.dto.Search;
import com.tutorial.paging_sorting.entities.Product;
import com.tutorial.paging_sorting.repositories.ProductRepo;
import com.tutorial.paging_sorting.services.ProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepo productRepo;
	
	@GetMapping("/products")
	public ApiResponse<Product> getAllProducts(){
		List<Product> products = productService.getAllProducts(); 
		return new ApiResponse(products.size(), products);
	}
	
	@PostMapping("/products")
	public ApiResponse<Product> searchProductsByName(@RequestBody Search search){
		List<Product> products = productRepo.findByNameContaining(search.getName()); 
		return new ApiResponse(products.size(),products);
	}
	
	@PostMapping("/products/pagination/{offset}/{pageSize}")
	public ResponseEntity<?> findProductsByNameWithPaginationCara1(@RequestBody Search search, @PathVariable int offset, @PathVariable int pageSize){				
		 Pageable paging = PageRequest.of(offset, pageSize);
		Page<Product> findProducts = productService.findProductsByNameWithPagination(search.getName(), search.getQuality(), paging);
		return new ResponseEntity<>(findProducts,HttpStatus.OK);
	}	
	
	@GetMapping("/products/pagination/{offset}/{pageSize}/search")
	public ResponseEntity<?> findProductsByNameWithPaginationCara2(@RequestParam("name") String name, @RequestParam("quality") int quality, @PathVariable int offset, @PathVariable int pageSize){				
		 Pageable paging = PageRequest.of(offset, pageSize);
		Page<Product> findProducts = productService.findProductsByNameWithPagination(name, quality, paging);
		return new ResponseEntity<>(findProducts,HttpStatus.OK);
	}	

	@GetMapping("/products/{field}")
	private ApiResponse<List<Product>> getProductsWithSortCara1(@PathVariable("field") String field) {
        List<Product> allProducts = productService.getProductWithSorting(field);
        return new ApiResponse<>(allProducts.size(), allProducts);
    }
	
	@GetMapping("/products/sorting")
	private ApiResponse<List<Product>> getProductsWithSortCara2(@RequestParam("orderBy") String field) {
        List<Product> allProducts = productService.getProductWithSorting(field);
        return new ApiResponse<>(allProducts.size(), allProducts);
    }
	
	@GetMapping("/products/pagination/{offset}/{pageSize}")
    private ResponseEntity<Page<Product>> getProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Product> productsWithPagination = productService.findProductsWithPagination(offset, pageSize);
        return new ResponseEntity<Page<Product>>(productsWithPagination,HttpStatus.OK);
    }
	
	@GetMapping("/products/paginationAndSort/{offset}/{pageSize}/{field}")
    private ApiResponse<Page<Product>> getProductsWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) {
        Page<Product> productsWithPagination = productService.findProductsWithPaginationAndSorting(offset, pageSize, field);
        return new ApiResponse<>(productsWithPagination.getSize(), productsWithPagination);
    }
}
