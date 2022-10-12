package com.shoppingcart.service.impl;

import com.shoppingcart.model.Product;
import com.shoppingcart.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    private ProductServiceImpl productServiceTest;

    @BeforeEach
    void setUp(){
       productServiceTest = new ProductServiceImpl(productRepository);
    }

    @Test
    @DisplayName("Test Case 1: Test case Return all the Product list present inside DB")
    @Disabled
    void test_getAllProductList_return_allProduct_in_DB(){
        /* Given */
        Product RECORD_2 = new Product(2, "iPhone 6s", "iPhone description", 127, 156);
        Product RECORD_3 = new Product(3, "iPhone 14", "iPhone 14 description", 127, 156);
        List<Product> productList = new ArrayList<>(Arrays.asList(RECORD_2, RECORD_3));
        /* When */
        when(productRepository.findAll()).thenReturn(productList);

        /* then */
        int actualResult = productList.size();
        int expectedResult = 2;
        assertEquals(expectedResult, actualResult);
    }
    @Test
    @DisplayName("Test Case 2: Test case Return In correct the Product list present inside DB")
    @Disabled
    void test_getAllProductList_return_incorrect_Product_in_DB(){
        /* Given */
        Product RECORD_2 = new Product(2, "iPhone 6s", "iPhone description", 127, 156);
        Product RECORD_3 = new Product(3, "iPhone 14", "iPhone 14 description", 127, 156);
        List<Product> productList = new ArrayList<>(Arrays.asList(RECORD_2, RECORD_3));
        /* When */
        when(productRepository.findAll()).thenReturn(productList);

        /* then */
        int actualResult = productList.size();
        assertNotEquals(4,actualResult);
    }

    @Test
    @DisplayName("Test Case 3: Test case Return all the Product list present inside DB v2")
    void test_getAllProductList_return_allProduct_in_DB_v1(){
        /* Given */
        /* When */
        productServiceTest.getAllProducts();
        /* then */
        verify(productRepository).findAll();
    }

    @Test
    @DisplayName("Test Case 4: Test case for adding Product in DB")
    void test_addProduct_in_DB(){

        /* Given */
        Product product = new Product("iPhone Pro", "iPhone description", 127, 156);

        /* When */
        productServiceTest.addProduct(product);

        /* Then => checks that product repository was invoked with the same record */
        ArgumentCaptor<Product> argumentCaptor = ArgumentCaptor.forClass(Product.class);
        verify(productRepository).save(argumentCaptor.capture());

        Product capturedValue = argumentCaptor.getValue();
        assertThat(capturedValue).isEqualTo(product);
    }



}