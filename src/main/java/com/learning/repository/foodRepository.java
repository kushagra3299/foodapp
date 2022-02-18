package com.learning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.Food;
import com.learning.entity.TypeOfFood;
@Repository
public interface foodRepository extends JpaRepository<Food, Long> {
	
	List<Food> findAllByTypeOfFood(TypeOfFood typeOfFood);

}
