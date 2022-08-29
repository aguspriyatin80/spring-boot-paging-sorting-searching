package com.tutorial.paging_sorting.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.paging_sorting.entities.Product;

public interface ProductRepo extends JpaRepository<Product,Integer>{

	List<Product> findByNameContaining(String keyword);
	Page<Product> findByNameContainingAndQualityLike(String keyword, int quality, Pageable pageable);
}
