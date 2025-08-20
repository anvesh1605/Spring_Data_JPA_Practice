package com.anvesh.store1.services;

import com.anvesh.store1.dtos.UserSummary;
import com.anvesh.store1.entities.*;
import com.anvesh.store1.repositories.*;
import com.anvesh.store1.repositories.specifications.ProductSpec;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ProfileRepository profileRepository;

    private final EntityManager entityManager;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    // this "entityManager" is responsible for managing the entity persistence context

    @Transactional  // will make the boundary of transaction long
    public void showEntityStates()
    {
        var user = User.builder()
                .name("John Doe")
                .email("john@gmail.com")
                .password("password")
                .build();

        if(entityManager.contains(user)) {
            System.out.println("Persistent");
        }
        else
        {
            System.out.println("Transient/Detached");
        }

        userRepository.save(user);
        if(entityManager.contains(user)) {
            System.out.println("Persistent");
        }
        else
        {
            System.out.println("Transient/Detached");
        }
    }

    @Transactional
    public void showRelatedEntities()
    {
//        var user= userRepository.findById(1L).orElseThrow();
        var profile= profileRepository.findById(2L).orElseThrow();
        // when this line starts transaction starts and when it ends transaction ends with these line
        System.out.println(profile.getBio());
        System.out.println(profile.getUser().getEmail());//this doesn't come in transaction
        //to tackle this issue, we keep this whole method as a transaction with the annotation
    }

    public void showAddresses()
    {
        var address = addressRepository.findById(1L).orElseThrow();
        System.out.println(address);
    }

    public void persistRelated()
    {
        var user = User.builder()
                .name("John Doe")
                .email("johnnn@gmial.com")
                .password("password")
                .build();
        var address = Address.builder()
                .street("street11")
                .city("city11")
                .state("state11")
                .zip("zip11")
                .build();
        user.addAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void deleteRelated()
    {
//        userRepository.deleteById(1L);
        var user = userRepository.findById(3L).orElseThrow();
        var address = user.getAddresses().get(0);
        user.removeAddress(address);
        userRepository.save(user);
    }

    @Transactional
    public void manageProducts()
    {
//        step 2 8.9
//        var category = categoryRepository.findById((byte) 1).orElseThrow();
//
//        var product = Product.builder()
//                .name(12L)
//                .description("dessc")
//                .price(BigDecimal.valueOf(15000.55))
//                .category(category)
//                .build();
//        productRepository.save(product);

        //step 3 8.9
//        var user = userRepository.findById(3L).orElseThrow();
//        var products = productRepository.findAll();
//        products.forEach(user::addFavouriteProduct);
//        userRepository.save(user);

        //step 4 8.9

        productRepository.deleteById(3L);
    }

    //for update methods wrap it in a transactional annotation
    @Transactional
    public void updateProductPrices()
    {
        productRepository.updatePriceByCategory(1,BigDecimal.valueOf(10));
    }

    @Transactional
    public void fetchProducts()
    {
//        var products = productRepository.findByCategory(new Category((byte)1));// for getting all colums
        //will use dtos to retrieve particular column values

//        var products = productRepository.findByCategory(new Category((byte)1));
//        var products = productRepository.findProducts(BigDecimal.valueOf(1),BigDecimal.valueOf(10));
//        products.forEach(System.out::println);

        //QueryByExample 10.1
        //till now we are using crud repo but now we are using jparepository where the methods are flush(),deleteAllInBatch(),findAll(example) and also crud repo methods

        var product = new Product();
        product.setName(11L);
//        var example = Example.of(product);
//        productRepository.findAll(example).forEach(System.out::println);

        /*
        limitations:
            --No support for nested properties
            --No support for matching collections/maps
            --Database-specific support for matching strings
            --Exact matching for other types (e.g. numbers/dates)
         */
        var matcher =ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIncludeNullValues()
                .withIgnorePaths("name");// to ignore any fields or related entities
        var example = Example.of(product,matcher);
        productRepository.findAll(example).forEach(System.out::println);
    }

    @Transactional
    public void fetchUser()
    {
        var user = userRepository.findByName("john").orElseThrow();
        System.out.println(user);
    }
    @Transactional
    public void fetchUsers()
    {
        var users = userRepository.findAll();
        users.forEach( (User u) -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });
        var usersAddr = userRepository.findAllWithAddresses();
        usersAddr.forEach( (User u) -> {
            System.out.println(u);
            u.getAddresses().forEach(System.out::println);
        });

    }

    @Transactional
    public void fetchProfiles()
    {
//        var profiles = profileRepository.findByLoyaltyPointsGreaterThan(6);
//        profiles.forEach(System.out::println);
//
//        profiles.forEach((Profile p)->{
//            System.out.println(p.getId());
//            System.out.println(p.getUser().getEmail());
//        });

        var users = userRepository.findLoyalUsers(11);
        users.forEach(System.out::println);

        users.forEach((UserSummary u)->{
            System.out.println(u.getId());
            System.out.println(u.getEmail());
        });

    }

    public void fetchProductsByCriteria()
    {
        var products = productRepository.findProductsByCriteria(null,BigDecimal.valueOf(1),BigDecimal.valueOf(10));//the values are optional
        products.forEach(System.out::println);
    }

    public void fetchCategoryByCriteria(String name,BigDecimal minPrice,BigDecimal maxPrice)// optional parameters
    {
        Specification<Product> spec = Specification.where(null); // this is the neutral starting point

        if (name != null) {
            spec = spec.and(ProductSpec.hasName(name));
        }
        if (minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }
        productRepository.findAll(spec).forEach(System.out::println);

    }
}
