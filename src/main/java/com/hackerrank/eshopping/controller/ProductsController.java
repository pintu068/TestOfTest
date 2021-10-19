package com.hackerrank.eshopping.controller;

import com.hackerrank.eshopping.model.Product;
import com.hackerrank.eshopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Product> insertEventData(@RequestBody Product producRequest) {
        Boolean isDatePresent = productService.createProductData(producRequest);
        if (isDatePresent == true)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateActorData(@PathVariable("id") long productId, @RequestBody Product producRequest) {
        Boolean IsUpdate = productService.updateProductData(productId, producRequest);
        if (productId == 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (IsUpdate == true)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getEventDataByActorId(@PathVariable("id") long productId) {
        Product response = productService.getProductDataById(productId);
        if (response == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getEventData(@RequestParam(required = false) String category) {
        if (category != null) {
            List<Product> productResponse = productService.getProductDataByCategory(category);
            if (productResponse == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        } else {
            List<Product> productResponse = productService.getProductData();
            if (productResponse == null)
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }


}
