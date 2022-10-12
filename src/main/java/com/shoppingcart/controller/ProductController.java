package com.shoppingcart.controller;

import com.shoppingcart.model.Product;
import com.shoppingcart.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping("/all/products")
    public List<Product> getAllProducts(){
        return productServiceImpl.getAllProducts();
    }

    @RequestMapping(value = "/add/product", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product){
        return this.productServiceImpl.addProduct(product);
    }

    @RequestMapping(value = "/get/product/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) throws Exception {
        Product product =  this.productServiceImpl.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @RequestMapping(value = "/update/product/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@PathVariable  Integer id,@RequestBody Product product) throws Exception {
        Product updatedProduct  = this.productServiceImpl.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @RequestMapping(value = "/delete/product/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Map<String, Boolean>> deleteProduct(@PathVariable Integer id) throws Exception {
        Map<String, Boolean> response = this.productServiceImpl.deleteProduct(id);
        return ResponseEntity.ok(response);
    }
}
