package com.shoppingcart.controller;

import com.shoppingcart.exception.ResourceNotFoundException;
import com.shoppingcart.model.Product;
import com.shoppingcart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/all/products")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @RequestMapping(value = "/add/product", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product){
        Product product_obj = new Product();
        product_obj.setProductName(product.getProductName());
        product_obj.setProductDescription(product.getProductDescription());
        product_obj.setPrice(product.getPrice());
        product_obj.setStock(product.getStock());
        product_obj.setId(product.getId());
        productRepository.save(product_obj);
        return product_obj;
    }

    @RequestMapping(value = "/get/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductById(@PathVariable Integer id){
        Product product =  productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee Not Exist with Given Id: " + id));
        return ResponseEntity.ok(product);
    }

    @RequestMapping(value = "/update/product/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@PathVariable  Integer id,@RequestBody Product product){
        Product product_obj =  productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee Not Exist with Given Id: " + id));
        product_obj.setProductName(product.getProductName());
        product_obj.setProductDescription(product.getProductDescription());
        product_obj.setStock(product.getStock());
        product_obj.setPrice(product.getPrice());
        Product updatedProduct  = productRepository.save(product_obj);
        return ResponseEntity.ok(updatedProduct);
    }

    @RequestMapping(value = "/delete/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Integer id){
        Product product_obj =  productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee Not Exist with Given Id: " + id));
        productRepository.delete(product_obj);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Product Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
