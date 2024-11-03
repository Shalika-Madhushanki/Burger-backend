package com.example.ahoi_burger.service;

import com.example.ahoi_burger.model.Burger;
import com.example.ahoi_burger.repository.BurgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BurgerService {

    BurgerRepository burgerRepository;

    @Autowired
    public BurgerService(BurgerRepository burgerRepository) {
        this.burgerRepository = burgerRepository;
    }

    public List<Burger> findAllBurgers() {
        return burgerRepository.findAll();
    }


    public Optional<Burger> findBurgerById(Long id) {
        return burgerRepository.findById(id);
    }

    public Burger createBurger(Burger burger) {
        return burgerRepository.save(burger);
    }

    public boolean deleteBurgerById(Long id) {
        Optional<Burger> burger = burgerRepository.findById(id);
        if (burger.isPresent()) {
            burgerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<Burger> updateBurger(Burger burgerDetails, Long id) {
        return burgerRepository.findById(id).map(burger -> {
            burger.setName(burgerDetails.getName());
            burger.setPrice(burgerDetails.getPrice());
            burger.setWeight(burgerDetails.getWeight());
            burger.setIsAvailable(burgerDetails.getIsAvailable());
            burger.setImageUrl(burgerDetails.getImageUrl());
            burger.setIsVegetarian(burgerDetails.getIsVegetarian());
            burger.setDescription(burgerDetails.getDescription());
            burger.setIngredients(burgerDetails.getIngredients());
            burger.setAllergens(burgerDetails.getAllergens());
            return burgerRepository.save(burger);
        });
    }

    public List<Burger> searchBurgerByName(String name) {
        return burgerRepository.findByNameContaining(name);
    }

    public List<Burger> filterBurgersByPrice(double minPrice, double maxPrice) {
        return burgerRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
