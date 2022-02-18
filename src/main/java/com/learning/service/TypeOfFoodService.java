package com.learning.service;

import java.util.Optional;

import com.learning.entity.FoodType;
import com.learning.entity.TypeOfFood;

public interface TypeOfFoodService {

	TypeOfFood addType(TypeOfFood typeOfFood);
	
	Optional<TypeOfFood> findByFoodType(FoodType foodType);
	
}
