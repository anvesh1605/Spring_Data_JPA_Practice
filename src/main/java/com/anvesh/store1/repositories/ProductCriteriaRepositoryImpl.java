package com.anvesh.store1.repositories;

import com.anvesh.store1.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
https://chatgpt.com/share/68a55731-cd50-800f-8b61-2cb5d3e661e9
 */
@AllArgsConstructor // as entityManager is declared as final, we need to provide a constructor
@Repository // so spring can create instances at run time
public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {
    //we have to provide custom implementation for this issue

    @PersistenceContext
    private final EntityManager entityManager; // we are directly working with hibernate to create a query dynamically

    @Override
    public List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        //from products (below line)
        Root<Product> root = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();
        if (name != null) {
            // during run-time it converts the below lines as ' name like %name% '
            predicates.add(cb.equal(root.get("name"), "%"+ name +"%"));
        }
        if (minPrice != null) {
            // price >= minPrice
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        }
        if (maxPrice != null) {
            //price <=maxPrice
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        }
        cq.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        return entityManager.createQuery(cq).getResultList();
    }
}
