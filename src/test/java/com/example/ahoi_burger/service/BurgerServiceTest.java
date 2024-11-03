package com.example.ahoi_burger.service;

import com.example.ahoi_burger.model.Burger;
import com.example.ahoi_burger.repository.BurgerRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BurgerServiceTest {
    @Mock
    private BurgerRepository burgerRepository;

    @InjectMocks
    private BurgerService burgerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllBurgers() {
        List<Burger> burgers = Arrays.asList(new Burger("Big Burger", 9.99, 180, true, false, "Delicious burger","url"));
        when(burgerRepository.findAll()).thenReturn(burgers);

        List<Burger> result = burgerService.findAllBurgers();
        assertEquals(1, result.size());
        assertEquals("Big Burger", result.get(0).getName());
    }

    @Test
    public void testGetBurgerById() {
        Burger burger = new Burger("Big Burger", 9.99, 180, true, false, "Delicious burger","url");
        when(burgerRepository.findById(1L)).thenReturn(Optional.of(burger));

        Optional<Burger> result = burgerService.findBurgerById(1L);
        assertTrue(result.isPresent());
        assertEquals("Big Burger", result.get().getName());
    }

    @Test
    public void testSearchBurgersByName() {
        List<Burger> burgers = Arrays.asList(new Burger("Big Burger", 9.99, 180, true, false, "Delicious burger","url"));
        when(burgerRepository.findByNameContaining("Big")).thenReturn(burgers);

        List<Burger> result = burgerService.searchBurgerByName("Big");
        assertEquals(1, result.size());
        assertEquals("Big Burger", result.get(0).getName());
    }

    @Test
    public void testFilterBurgersByPrice() {
        List<Burger> burgers = Arrays.asList(new Burger("Big Burger", 9.99, 180, true, false, "Delicious burger","url"));
        when(burgerRepository.findByPriceBetween(5.0, 15.0)).thenReturn(burgers);

        List<Burger> result = burgerService.filterBurgersByPrice(5.0, 15.0);
        assertEquals(1, result.size());
        assertEquals(9.99, result.get(0).getPrice());
    }

    @Test
    public void testCreateBurger() {
        Burger burger = new Burger();
        burger.setName("Big Burger");
        burger.setPrice(9.99);

        when(burgerRepository.save(any(Burger.class))).thenReturn(burger);

        Burger createdBurger = burgerService.createBurger(burger);
        assertEquals("Big Burger", createdBurger.getName());
        assertEquals(9.99, createdBurger.getPrice());
    }
}
