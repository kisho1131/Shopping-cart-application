package com.shoppingcart.service;

import com.shoppingcart.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    /* get the list of all product
      add product
    * get product by Id
    * update product
    * Delete product */
    List<Product> getAllProducts();
    Product addProduct(Product product);
    Product getProductById(Integer id) throws Exception;
    Product updateProduct(Integer id, Product product) throws Exception;
    Map<String, Boolean> deleteProduct(Integer id) throws Exception;

}
