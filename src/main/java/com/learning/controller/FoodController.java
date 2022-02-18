package com.learning.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Food;
import com.learning.entity.FoodType;
import com.learning.entity.TypeOfFood;
import com.learning.exceptions.AccountExistsException;
import com.learning.exceptions.IdNotFoundException;
import com.learning.service.FoodService;
import com.learning.service.TypeOfFoodService;

@RestController
public class FoodController {

	@Autowired
	FoodService foodService;

	@Autowired
	TypeOfFoodService typeOfFoodService;

	@PostMapping(path = "/food")
	public ResponseEntity<?> addFood(@RequestBody Map<String, String> json) throws AccountExistsException {
		// food type is considered as an seperate entity so, data from request is taken as map and instantiated object 
		TypeOfFood type = typeOfFoodService.addType(new TypeOfFood(FoodType.valueOf(json.get("foodType"))));
		Food food = new Food(json.get("foodName"), Double.parseDouble(json.get("foodCost")), type, json.get("description"),json.get("foodPic"));
		food = foodService.addFood(food);
		json.put("id", String.valueOf(food.getId()));
		return ResponseEntity.status(201).body(json);

	}

	@GetMapping(path = "/food/")
	public ResponseEntity<?> getFoods() {
		return ResponseEntity.status(200).body(foodService.getAllFoods());
	}
	
	@GetMapping(path = "/food/{foodType}")
	public ResponseEntity<?> getFoodsOfType(@PathVariable FoodType foodType) {
		Optional<TypeOfFood> type = typeOfFoodService.findByFoodType(foodType);
		if(type.isEmpty())
			return ResponseEntity.status(200).body(new ArrayList<String>());
		return ResponseEntity.status(200).body(foodService.findAllByTypeOfFood(type.get()));
	}

	@GetMapping(path = "/foods/{foodId}")
	public ResponseEntity<?> getFood(@PathVariable long foodId) throws IdNotFoundException {
		Food food = foodService.getFoodById(foodId);
		return ResponseEntity.status(200).body(food);
	}
	
	@PutMapping(path = "/foods/{foodId}")
	public ResponseEntity<?> updateFood(@PathVariable long foodId, @RequestBody Map<String, String> json)
			throws IdNotFoundException {
		TypeOfFood type = null;
		if (json.get("foodType") != null)
			type = typeOfFoodService.addType(new TypeOfFood(FoodType.valueOf(json.get("foodType"))));
		Food food = new Food(json.get("foodName"), Double.parseDouble(json.get("foodCost")), type, json.get("description"),json.get("foodPic"));
		food.setId(foodId);
		food = foodService.updateFoodById(foodId, food);
		return ResponseEntity.status(200).body(food);
	}

	

	@DeleteMapping(path = "/foods/{foodId}")
	public ResponseEntity<?> deleteFood(@PathVariable long foodId) throws IdNotFoundException {
		foodService.deleteFoodById(foodId);
		HashMap<String, String> map = new HashMap<>();
		map.put("Message", "Food deleted successfully");
		return ResponseEntity.status(200).body(map);
	}

}
