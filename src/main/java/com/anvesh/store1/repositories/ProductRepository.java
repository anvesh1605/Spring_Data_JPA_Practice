package com.anvesh.store1.repositories;

import com.anvesh.store1.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}