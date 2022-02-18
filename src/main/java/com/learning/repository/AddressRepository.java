package com.learning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	Optional<Address> findByAddress(String address);

}
