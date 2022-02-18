package com.learning.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.FoodType;
import com.learning.entity.TypeOfFood;
import com.learning.repository.TypeOfFoodRepository;
import com.learning.service.TypeOfFoodService;

@Service
public class TypeOfFoodServiceImpl implements TypeOfFoodService {

	@Autowired
	TypeOfFoodRepository typeOfFoodRepository;

	@Override
	public TypeOfFood addType(TypeOfFood typeOfFood) {
		Optional<TypeOfFood> type = typeOfFoodRepository.findByFoodType(typeOfFood.getFoodType());
		if (type.isPresent())
			return type.get();
		return typeOfFoodRepository.save(typeOfFood);
	}

	@Override
	public Optional<TypeOfFood> findByFoodType(FoodType foodType) {
		return typeOfFoodRepository.findByFoodType(foodType);
	}

}