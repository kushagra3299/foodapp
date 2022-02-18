package com.learning.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Food;
import com.learning.entity.TYPE;
import com.learning.exceptions.AlreadyExistsException;
import com.learning.exceptions.IdNotFoundException;
import com.learning.payload.response.MessageResponse;
import com.learning.service.FoodService;

@RestController
@RequestMapping("/api/food")
public class FoodController {
	
	@Autowired
	private FoodService foodService;
	
//	POST request for adding food item
	@PostMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addFood(@RequestBody Food food) throws AlreadyExistsException {
		Food result = foodService.addFood(food);
		return ResponseEntity.status(201).body(result);
	}
	
//	GET request for retrieving food item by id
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> getFoodById(@PathVariable("id") int id) throws IdNotFoundException {
		Optional<Food> optional = foodService.getFoodById(id);
		System.out.println("hello");
		return ResponseEntity.ok(optional.get());
	}
	
//	PUT request for updating food item by id
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateFood(@PathVariable("id") int id, @RequestBody Food food) throws IdNotFoundException {
		Food result = foodService.updateFood(food, id);
		return ResponseEntity.status(200).body(result);
	}
	
//	GET request for retrieving all food items
	@GetMapping("/")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> getAllFood() {
		Optional<List<Food>> optional = foodService.getAllFoods();
		if (optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponse("No record found"));
		}
		return ResponseEntity.ok(optional.get());
	}
	
//	GET request for retrieving food item by food type
	@GetMapping("/type/{type}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> getFoodByType(@PathVariable("type") TYPE foodType) {
		Optional<List<Food>> optional = foodService.getByFoodType(foodType);
		if (optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponse("Sorry Food Not Found"));
		}
		return ResponseEntity.ok(optional.get());
	}
	
//	DELETE request for deleting food item by id
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteFoodById(@PathVariable("id") int id) throws IdNotFoundException {
		foodService.deleteFood(id);
		return ResponseEntity.status(200).body(new MessageResponse("Food item deleted"));
	}

}