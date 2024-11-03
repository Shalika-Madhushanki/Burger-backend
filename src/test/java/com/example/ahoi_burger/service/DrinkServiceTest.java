package com.example.ahoi_burger.service;

import com.example.ahoi_burger.model.Drink;
import com.example.ahoi_burger.repository.DrinkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DrinkServiceTest {
    @Mock
    private DrinkRepository drinkRepository;

    @InjectMocks
    private DrinkService drinkService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllDrinks() {
        List<Drink> drinks = Arrays.asList(new Drink("Lime Drink", 3.99, "Delicious Lime Drink","url"));
        when(drinkRepository.findAll()).thenReturn(drinks);

        List<Drink> result = drinkService.findAllDrinks();
        assertEquals(1, result.size());
        assertEquals("Lime Drink", result.get(0).getName());
    }

    @Test
    public void testGetDrinkById() {
        Drink drink = new Drink("Lime Drink", 3.99, "Delicious Lime Drink","url");
        when(drinkRepository.findById(1L)).thenReturn(Optional.of(drink));

        Optional<Drink> result = drinkService.findDrinkById(1L);
        assertTrue(result.isPresent());
        assertEquals("Lime Drink", result.get().getName());
    }

    @Test
    public void testSearchDrinksByName() {
        List<Drink> drinks = Arrays.asList(new Drink("Lime Drink", 3.99, "Delicious Lime Drink","url"));
        when(drinkRepository.findByNameContaining("Lime")).thenReturn(drinks);

        List<Drink> result = drinkService.searchDrinkByName("Lime");
        assertEquals(1, result.size());
        assertEquals("Lime Drink", result.get(0).getName());
    }

    @Test
    public void testFilterDrinksByPrice() {
        List<Drink> drinks = Arrays.asList(new Drink("Lime Drink", 3.99, "Delicious Lime Drink","url"));
        when(drinkRepository.findByPriceBetween(5.0, 15.0)).thenReturn(drinks);

        List<Drink> result = drinkService.filterDrinksByPrice(5.0, 15.0);
        assertEquals(1, result.size());
        assertEquals(3.99, result.get(0).getPrice());
    }

    @Test
    public void testCreateDrink() {
        Drink drink = new Drink();
        drink.setName("Lime Drink");
        drink.setPrice(3.99);

        when(drinkRepository.save(any(Drink.class))).thenReturn(drink);

        Drink createdDrink = drinkService.createDrink(drink);
        assertEquals("Lime Drink", createdDrink.getName());
        assertEquals(3.99, createdDrink.getPrice());
    }
}
