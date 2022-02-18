package com.learning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Food;
import com.learning.entity.TYPE;

@Repository
public interface FoodRepo extends JpaRepository<Food, Integer> {
	
//	Retrieving food items by food type
	List<Food> findAllByFoodType(TYPE foodType);

}