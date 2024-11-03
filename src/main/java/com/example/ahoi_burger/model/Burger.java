package com.example.ahoi_burger.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Burger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String name;

    @Positive(message = "Price must be greater than 0")
    private double price;

    @Positive(message = "Weight must be greater than 0")
    private int weight;
    private Boolean isAvailable;
    private Boolean isVegetarian;

    @Size(max = 500, message = "Description must be less than 500 characters")
    private String description;
    private String imageUrl;


    @ElementCollection
    @CollectionTable(name = "burger_ingredients", joinColumns = @JoinColumn(name = "burger_id"))
    @Column(name = "ingredient")
    private List<String> ingredients;

    @ElementCollection
    @CollectionTable(name = "burger_allergens", joinColumns = @JoinColumn(name = "burger_id"))
    @Column(name = "allergen")
    private List<String> allergens;

    public Burger() {
    }

    public Burger(String name, double price, int weight, Boolean isAvailable, Boolean isVegetarian, String description, String imageUrl) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.isAvailable = isAvailable;
        this.isVegetarian = isVegetarian;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean available) {
        isAvailable = available;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getIsVegetarian() {
        return isVegetarian;
    }

    public void setIsVegetarian(Boolean vegetarian) {
        isVegetarian = vegetarian;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<String> allergens) {
        this.allergens = allergens;
    }
}
