package com.learning.service;

import java.util.List;

import com.learning.entity.Food;
import com.learning.entity.FoodType;
import com.learning.entity.TypeOfFood;
import com.learning.exceptions.IdNotFoundException;

public interface FoodService {
	Food addFood(Food food);
	
	Food getFoodById(long id) throws IdNotFoundException;
	
	Food updateFoodById(long id, Food food) throws IdNotFoundException;

	List<Food> getAllFoods();
	
	String deleteFoodById(long id) throws IdNotFoundException;
	
	List<Food> findAllByTypeOfFood(TypeOfFood typeOfFood);

}
