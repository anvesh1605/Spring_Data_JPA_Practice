package com.anvesh.store1.repositories;

import com.anvesh.store1.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findByName(Long name);
    List<Product> findByNameLike(Long name);
    List<Product> finByNameLike(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByNameContainingIgnoreCase(String name);
    // will query the sql accordingly

    //Numbers
    List<Product> findByPriceGreaterThan(BigDecimal price);
    List<Product> findByPriceLessThan(BigDecimal price);
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);
    List<Product> findByPriceEqual(BigDecimal price);

    //Null

    List<Product> findByDescriptionNull();
    List<Product> findByDescriptionNotNull();

    //For multiple conditions use 'And' 'Or'
    List<Product> findByDescriptionNullAndNameNull();

    //Sort 'OrderBy' 'Asc' or 'Desc'
    List<Product> findByNameOrderByPriceAsc(Long name);

    //Limit 'Top/First'
    List <Product> findTop5NameOrderByPrice(BigDecimal price);
    List<Product> findFirst5NameLikeOrderByPrice(BigDecimal price);
    List<Product> findLast5NameLikeOrderByPrice(BigDecimal price); // we are hard coding the number of names we want here
    // for variable number of values to be returned there is other method


}