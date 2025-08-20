package com.anvesh.store1.repositories;

import com.anvesh.store1.entities.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductCriteriaRepository {


    List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice );
}
