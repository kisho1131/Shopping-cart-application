package com.shoppingcart.repository;

import com.shoppingcart.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    /* runs Before each test, helpful in the code setup */
    @BeforeEach
    void setUp(){

        /* Given Condition */
        Product RECORD_1 = new Product(1,"iPhone XR", "iPhone description", 127, 156);
        Product RECORD_2 = new Product(2, "iPhone 6s", "iPhone description", 127, 156);
        Product RECORD_3 = new Product(3, "iPhone 14", "iPhone 14 description", 127, 156);
        productRepository.save(RECORD_1);
        productRepository.save(RECORD_2);
        productRepository.save(RECORD_3);
    }

    /* runs after each test, helpful in the code clean up */
    @AfterEach
    void tearDown(){
        productRepository.deleteAll();
    }


    @Test
    @DisplayName("Test Case 1: Return the Correct no.of record from Database")
    void test_findProductByProductDescription_return_correct_numberOf_records() {
        /* given */

        /* when */
        String description = "iPhone description";
        List<Product> productList = productRepository.findProductByProductDescription(description);

        /* then */
        assertEquals(2, productList.size());
    }

    @Test
    @DisplayName("Test Case 2: Return the In Correct no.of record from Database")
    void test_findProductByProductDescription_return_incorrect_numberOf_records() {
        /* given */

        /* when */
        String description = "iPhone description";
        List<Product> productList = productRepository.findProductByProductDescription(description);

        /* then */
        assertNotEquals(3, productList.size());
    }
}