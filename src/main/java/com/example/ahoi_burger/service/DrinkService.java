package com.example.ahoi_burger.service;

import com.example.ahoi_burger.model.Drink;
import com.example.ahoi_burger.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DrinkService {

    DrinkRepository drinkRepository;

    @Autowired
    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    public List<Drink> findAllDrinks() {
        return drinkRepository.findAll();
    }


    public Optional<Drink> findDrinkById(Long id) {
        return drinkRepository.findById(id);
    }

    public Drink createDrink(Drink drink) {
        return drinkRepository.save(drink);
    }

    public boolean deleteDrinkById(Long id) {
        Optional<Drink> drink = drinkRepository.findById(id);
        if (drink.isPresent()) {
            drinkRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<Drink> updateDrink(Drink drinkDetails, Long id) {
        return drinkRepository.findById(id).map(drink -> {
            drink.setName(drinkDetails.getName());
            drink.setPrice(drinkDetails.getPrice());
            drink.setImageUrl(drinkDetails.getImageUrl());
            drink.setDescription(drinkDetails.getDescription());
            return drinkRepository.save(drink);
        });
    }

    public List<Drink> searchDrinkByName(String name) {
        return drinkRepository.findByNameContaining(name);
    }

    public List<Drink> filterDrinksByPrice(double minPrice, double maxPrice) {
        return drinkRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
