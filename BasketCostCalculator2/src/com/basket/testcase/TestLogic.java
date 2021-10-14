package com.basket.testcase;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.basket.Basket;
import com.basket.BasketCostCalculator;

public class TestLogic {

	 List<Basket> basket = Arrays.asList(
             new Basket("apple", 10, 30),
             new Basket("banana", 20, 10),
             new Basket("orange", 10, 20),
             new Basket("lemon", 10, 3),
             new Basket("peach", 20, 40),
             new Basket("apple", 10, 30),
             new Basket("banana", 10, 10),
             new Basket("apple", 20, 30)
    );

	@Test  
    public void testBasketCostCalculator(){  
        assertEquals(new Double(5230.0), BasketCostCalculator.basketCostCalculator(basket));  
    }  
	
}
