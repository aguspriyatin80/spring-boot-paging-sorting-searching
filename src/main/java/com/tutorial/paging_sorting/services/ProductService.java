package com.tutorial.paging_sorting.services;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tutorial.paging_sorting.dto.Search;
import com.tutorial.paging_sorting.entities.Product;
import com.tutorial.paging_sorting.repositories.ProductRepo;

@Service
public class ProductService {

	@Autowired
	ProductRepo productRepo;
	
//	@PostConstruct
//	public void initDB() {
//		 List<Product> products = IntStream.rangeClosed(1, 200)
//               .mapToObj(i -> new Product(i, "product " + i, new Random().nextInt(100), new Random().nextLong(50000)))
//               .collect(Collectors.toList());
//       productRepo.saveAll(products);
//	}
		
	public List<Product> getAllProducts(){
		return productRepo.findAll();
	}	
	
	public List<Product> getProductWithSorting(String field){
		return productRepo.findAll(Sort.by(Sort.Direction.ASC,field));
	}
	
	public Page<Product> findProductsByNameWithPagination(String name, int quality, Pageable pageable){		
		Page<Product> products = productRepo.findByNameContainingAndQualityLike(name, quality, pageable);		
        return  products;
    }
	
	public Page<Product> findProductsWithPagination(int offset,int pageSize){
        Page<Product> products = productRepo.findAll(PageRequest.of(offset, pageSize));
        return  products;
    }
	
	public Page<Product> findProductsWithPaginationAndSorting(int offset,int pageSize,String field){
        Page<Product> products = productRepo.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  products;
    }


}
