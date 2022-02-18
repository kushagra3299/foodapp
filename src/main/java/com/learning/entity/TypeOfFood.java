package com.learning.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity

@NoArgsConstructor
@ToString
@Table(name = "foodTypes")
public class TypeOfFood {

	public TypeOfFood(FoodType foodType) {
		this.foodType = foodType;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private FoodType foodType;

	@OneToMany(mappedBy = "typeOfFood", cascade = CascadeType.ALL)
	private List<Food> foods;

}
