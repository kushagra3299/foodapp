package com.learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Food;
import com.learning.entity.TypeOfFood;
import com.learning.exceptions.IdNotFoundException;
import com.learning.repository.foodRepository;
import com.learning.service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	foodRepository foodRepository;

	@Override
	public Food addFood(Food food) {
		return (foodRepository.save(food) != null) ? food : null;
	}

	@Override
	public Food getFoodById(long id) throws IdNotFoundException {
		Optional<Food> food = foodRepository.findById(id);
		if (food.isEmpty())
			throw new IdNotFoundException("Sorry food with " + id + " not found!");
		return food.get();
	}

	@Override
	public Food updateFoodById(long id, Food food) throws IdNotFoundException {
		Optional<Food> existing = foodRepository.findById(id);
		if (existing.isEmpty())
			throw new IdNotFoundException("Sorry food with " + food.getId() + " not found!");

		// food id shouldn't change so it shouldn't change
		food.setId(existing.get().getId());
		// filling the missing details with existing details as the save will overrides
		// the existing
		if (food.getTypeOfFood() == null)
			food.setTypeOfFood(existing.get().getTypeOfFood());

		if (food.getFoodName() == null)
			food.setFoodName(existing.get().getFoodName());

		if (food.getFoodCost() == 0.0d)
			food.setFoodCost(existing.get().getFoodCost());

		if (food.getFoodPic() == null)
			food.setFoodPic(existing.get().getFoodPic());

		if (food.getDescription() == null)
			food.setDescription(existing.get().getDescription());

		return foodRepository.save(food);
	}

	@Override
	public List<Food> getAllFoods() {
		return foodRepository.findAll();
	}

	@Override
	public String deleteFoodById(long id) throws IdNotFoundException {
		if (!foodRepository.existsById(id))
			throw new IdNotFoundException("Sorry food with " + id + " not found!");
		foodRepository.deleteById(id);
		return "success";
	}

	@Override
	public List<Food> findAllByTypeOfFood(TypeOfFood typeOfFood) {
		return foodRepository.findAllByTypeOfFood(typeOfFood);
	}

}
