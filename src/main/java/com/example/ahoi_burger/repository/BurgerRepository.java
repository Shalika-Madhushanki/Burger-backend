package com.example.ahoi_burger.repository;

import com.example.ahoi_burger.model.Burger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BurgerRepository extends JpaRepository<Burger, Long> {
    List<Burger> findByNameContaining(String name);
    List<Burger> findByPriceBetween(double minPrice, double maxPrice);
}
