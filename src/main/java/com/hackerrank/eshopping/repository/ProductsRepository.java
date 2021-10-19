package com.hackerrank.eshopping.repository;

import com.hackerrank.eshopping.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long> {
    public List<Product> findByCategory(String category);
}
