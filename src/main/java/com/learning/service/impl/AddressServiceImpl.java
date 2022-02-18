package com.learning.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Address;
import com.learning.repository.AddressRepository;
import com.learning.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;

	@Override
	public Address addAddress(Address address) {
		Optional<Address> existAddress = addressRepository.findByAddress(address.getAddress());
		if (existAddress.isPresent())
			return existAddress.get();
		return addressRepository.save(address);

	}
	

}
