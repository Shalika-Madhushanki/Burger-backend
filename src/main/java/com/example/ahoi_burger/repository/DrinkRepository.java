package com.example.ahoi_burger.repository;

import com.example.ahoi_burger.model.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink, Long> {
    List<Drink> findByNameContaining(String name);
    List<Drink> findByPriceBetween(double minPrice, double maxPrice);
}
