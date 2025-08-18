package com.anvesh.store1.services;

import com.anvesh.store1.entities.Category;
import com.anvesh.store1.entities.Product;
import com.anvesh.store1.repositories.CategoryRepository;
import com.anvesh.store1.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class productService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;


    @Transactional
    public void createProduct()
    {
        var category = Category.builder()
                .name("category")
                .build();
        var product = Product.builder()
                .name(11L)
                .description("desc")
                .price(BigDecimal.valueOf(15000.55))
                .category(category)
                .build();

        productRepository.save(product);
        System.out.println(product);
    }
}
