package com.shoppingcart.service.impl;

import com.shoppingcart.model.Product;
import com.shoppingcart.repository.ProductRepository;
import com.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product addProduct(Product product) {
//        Product product_obj = new Product();
//        product_obj.setProductName(product.getProductName());
//        product_obj.setProductDescription(product.getProductDescription());
//        product_obj.setPrice(product.getPrice());
//        product_obj.setStock(product.getStock());
//        product_obj.setId(product.getId());
        productRepository.save(product);
        return product;
    }


    @Override
    public Product getProductById(Integer id) throws Exception {
        return productRepository.findById(id).orElseThrow(() ->
                new Exception("Employee Not Exist with Given Id: " + id));
    }

    @Override
    public Product updateProduct(Integer id, Product product) throws Exception {
        Product product_obj =  productRepository.findById(id).orElseThrow(() ->
                new Exception("Employee Not Exist with Given Id: " + id));
        product_obj.setProductName(product.getProductName());
        product_obj.setProductDescription(product.getProductDescription());
        product_obj.setStock(product.getStock());
        product_obj.setPrice(product.getPrice());
        return productRepository.save(product_obj);
    }

    @Override
    public Map<String, Boolean> deleteProduct(Integer id) throws Exception {
        Product product_obj =  productRepository.findById(id).orElseThrow(() ->
                new Exception("Employee Not Exist with Given Id: " + id));
        productRepository.delete(product_obj);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Product Deleted", Boolean.TRUE);
        return response;
    }
}
