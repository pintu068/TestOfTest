package com.hackerrank.eshopping.service;

import com.hackerrank.eshopping.model.Product;
import com.hackerrank.eshopping.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductsRepository productsRepository;

    public boolean createProductData(Product eventDTO) {
        Optional<Product> response = productsRepository.findById(eventDTO.getId());
        if (response.isPresent()) {
            return true;
        } else {
            productsRepository.save(eventDTO);
            return false;
        }
    }

    public Boolean updateProductData(Long Id, Product producRequest) {

        Optional<Product> response = productsRepository.findById(Id);

        if (response.isPresent()) {


            Product productPrevious = response.get();
            productPrevious.setRetailPrice(producRequest.getRetailPrice());
            productPrevious.setDiscountedPrice(producRequest.getDiscountedPrice());
            productPrevious.setAvailability(producRequest.getAvailability());
            productsRepository.save(productPrevious);
            return false;

        } else {
            return true;
        }
    }


    public Product getProductDataById(Long ProductId) {
        Optional<Product> productResponse = productsRepository.findById(ProductId);

        try {
            Product productResponseFinal = productResponse.get();
            return productResponseFinal;
        } catch (NoSuchElementException e) {
            return null;
        }

    }

    public List<Product> getProductData() {
        List<Product> productResponse = productsRepository.findAll();

        return productResponse;
    }


    public List<Product> getProductDataByCategory(String category) {
        List<Product> productResponse = productsRepository.findByCategory(category);
        productResponse.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getAvailability() == true && o2.getAvailability() == false)
                    return 1;
                else
                    return -1;
                // return 0;
            }
        });
        return productResponse;
    }
}
