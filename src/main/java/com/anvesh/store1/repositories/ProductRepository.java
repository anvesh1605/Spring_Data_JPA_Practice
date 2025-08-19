package com.anvesh.store1.repositories;

import com.anvesh.store1.dtos.ProductSummary;
import com.anvesh.store1.dtos.ProductSummaryDTO;
import com.anvesh.store1.entities.Category;
import com.anvesh.store1.entities.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

//    List<Product> findByName(Long name);
//    List<Product> findByNameLike(Long name);
//    List<Product> finByNameLike(String name);
//    List<Product> findByNameContaining(String name);
//    List<Product> findByNameContainingIgnoreCase(String name);
//    // will query the sql accordingly

    //Numbers
    List<Product> findByPriceGreaterThan(BigDecimal price);
    List<Product> findByPriceLessThan(BigDecimal price);
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

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

    //Writing Custom Queries(@Query)

    //Find products whose prices are in a given range and sort by name

//    List<Product> findByPriceBetweenOrderByName(BigDecimal min, BigDecimal max); //derived query


    //custom query
    // can write query in SQL OR JPQL(java persistence query language)
//    @Query(value = "select * from products p where p.price between :min and :max order by p.name",nativeQuery = true)
    //by default query excepts a jpql so we need to give nativeQuery=true
//    @Query("select p from Product p where p.price between :min and :max order by p.name") //JPQL
//    List<Product> findProducts(@Param("min") BigDecimal min,@Param("max") BigDecimal max);

    //iINSTEAD OF WRITING THE QUERY WE CAN GENERATE USING JPA BODY
    //right-click on the derived query there u can generate jpa query and configure

    // in jpql to join with other related entities use 'join'
//    @Query("select p from Product p join p.category where p.price between :min and :max order by p.name")
//    List<Product> findProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max); //derived query

    //for aggregating functions
    @Query("select count(*) from Product p where p.price between :min and :max")
    long countProductsByName(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    //for modifying table
    @Modifying
    @Query("update Product p set p.price= :newPrice where p.category.id = :categoryId ")
    void updatePriceByCategory(int categoryId,BigDecimal newPrice);

//    List<Product> findByCategory(Category category);
//will use dtos to retrieve particular column values
    //projections with derived queries
//        List<ProductSummary> findByCategory(Category category); // WITH INTERFACE
//    List<ProductSummaryDTO> findByCategory(Category category);// WITH CLASS only @query annotation should be used doesn't work with derived queries

    //Projections with custom queries
    //FOR INTERFACES
//    @Query("select p.id,p.name from Product p where p.category = :category")
//    List<ProductSummary> findByCategory(@Param("category") Category category); // WITH INTERFACE
    //FOR CLASSES
    @Query("SELECT new com.anvesh.store1.dtos.ProductSummaryDTO(p.id, p.name) " +
            "FROM Product p WHERE p.category = :category")
    List<ProductSummaryDTO> findByCategory(@Param("category") Category category);

    //calling stored procedures
    @Procedure("findProductsByPrice")
    List<Product> findProducts( BigDecimal min, BigDecimal max);

}