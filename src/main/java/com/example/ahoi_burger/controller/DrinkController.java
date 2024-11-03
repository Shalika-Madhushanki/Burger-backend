package com.example.ahoi_burger.controller;

import com.example.ahoi_burger.model.Drink;
import com.example.ahoi_burger.service.DrinkService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/drinks")
public class DrinkController {
    DrinkService drinkService;

    @Autowired
    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping
    public ResponseEntity<List<Drink>> getAllDrinks() {
        List<Drink> drinksList = drinkService.findAllDrinks();
        return new ResponseEntity<>(drinksList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drink> getDrinkById(@PathVariable Long id) {
        return drinkService.findDrinkById(id).map(drink -> new ResponseEntity<>(drink, HttpStatus.OK)).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    public ResponseEntity<Drink> createDrink(@Valid @RequestBody Drink drink) {
        Drink createdDrink = drinkService.createDrink(drink);
        return new ResponseEntity<>(createdDrink, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Drink> updateDrink(@Valid @RequestBody Drink drinkDetails, @PathVariable Long id) {
        return drinkService.updateDrink(drinkDetails, id)
                .map(updatedDrink -> new ResponseEntity<>(updatedDrink, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrink(@PathVariable Long id) {
        if (drinkService.deleteDrinkById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Drink>> searchDrinkByName(@RequestParam String name) {
        List<Drink> drinkList = drinkService.searchDrinkByName(name);
        return new ResponseEntity<>(drinkList, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Drink>> filterDrinkByPrice(@RequestParam double minPrice, @RequestParam double maxPrice) {
        List<Drink> drinks = drinkService.filterDrinksByPrice(minPrice, maxPrice);
        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }
}
