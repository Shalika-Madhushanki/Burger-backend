package com.example.ahoi_burger.controller;

import com.example.ahoi_burger.model.Burger;
import com.example.ahoi_burger.service.BurgerService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/burgers")
public class BurgerController {
    BurgerService burgerService;

    @Autowired
    public BurgerController(BurgerService burgerService) {
        this.burgerService = burgerService;
    }

    @GetMapping
    public ResponseEntity<List<Burger>> getAllBurgers() {
        List<Burger> burgersList = burgerService.findAllBurgers();
        return new ResponseEntity<>(burgersList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Burger> getBurgerById(@PathVariable Long id) {
        return burgerService.findBurgerById(id).map(burger -> new ResponseEntity<>(burger, HttpStatus.OK)).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }

    @PostMapping
    public ResponseEntity<Burger> createBurger(@Valid @RequestBody Burger burger) {
        Burger createdBurger = burgerService.createBurger(burger);
        return new ResponseEntity<>(createdBurger, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Burger> updateBurger(@Valid @RequestBody Burger burgerDetails, @PathVariable Long id) {
        return burgerService.updateBurger(burgerDetails, id)
                .map(updatedBurger -> new ResponseEntity<>(updatedBurger, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBurger(@PathVariable Long id) {
        if (burgerService.deleteBurgerById(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Burger>> searchBurgerByName(@RequestParam String name) {
        List<Burger> burgerList = burgerService.searchBurgerByName(name);
        return new ResponseEntity<>(burgerList, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Burger>> filterBurgerByPrice(@RequestParam double minPrice, @RequestParam double maxPrice) {
        List<Burger> burgers = burgerService.filterBurgersByPrice(minPrice, maxPrice);
        return new ResponseEntity<>(burgers, HttpStatus.OK);
    }
}
