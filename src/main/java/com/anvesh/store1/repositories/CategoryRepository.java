package com.anvesh.store1.repositories;

import com.anvesh.store1.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}