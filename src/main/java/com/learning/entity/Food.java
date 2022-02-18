package com.learning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.learning.utils.FoodTypeSerializer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity

@NoArgsConstructor
@Table(name = "food")
@ToString
public class Food {

	public Food(String name, double cost, TypeOfFood typeOfFood, String description, String foodPic) {
		super();
		this.foodName = name;
		this.foodCost = cost;
		this.typeOfFood = typeOfFood;
		this.description = description;
		this.foodPic = foodPic;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "foodId")
	private long id;

	@NotBlank
	@Size(min = 2, max = 50)
	private String foodName;

	@Min(value = 1L)
	private double foodCost;

	// many foods can come under a single food type
	@ManyToOne
	@JoinColumn(name = "foodTypeId")
	@JsonSerialize(using = FoodTypeSerializer.class)
	private TypeOfFood typeOfFood;

	@NotBlank
	@Size(min = 50, max = 150)
	private String description;

	@NotBlank
	@URL
	private String foodPic;

}
