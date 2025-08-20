package com.anvesh.store1.repositories.specifications;

import com.anvesh.store1.entities.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpec {
    /*
    In this class we will add bunch of static utility methods for creating specifications objects.

     */
    public static Specification<Product> hasName(String name){
        return (root, cq, cb) -> cb.equal(root.get("name"),  name);

    }
    public static Specification<Product> hasPriceGreaterThanOrEqualTo(BigDecimal price)
    {
        return (root, cq, cb) -> cb.greaterThanOrEqualTo(root.get("price"), price);
    }
    public static Specification<Product> hasPriceLessThanOrEqualTo(BigDecimal price)
    {
        return (root, cq, cb) -> cb.lessThanOrEqualTo(root.get("price"), price);
    }
    // can combine this specifications to find the products


}
