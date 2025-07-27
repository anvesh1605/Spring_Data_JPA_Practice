package com.anvesh.store1.repositories;

import com.anvesh.store1.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {

}