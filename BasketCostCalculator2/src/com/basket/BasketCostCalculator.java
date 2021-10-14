package com.basket;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BasketCostCalculator {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 double totalbasketCost = 0;
		
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

		totalbasketCost = basketCostCalculator(basket);
        
        System.out.println("Total Cost of Basket is " +totalbasketCost);
	}

	public static Double basketCostCalculator(List<Basket> basket) {
		double basketCost = 0;
		
		Map<String, Long> counting = basket.stream().collect(
	                Collectors.groupingBy(Basket::getName, Collectors.counting()));

	    System.out.println(counting); 
		 
        Map<String, Integer> sum = basket.stream().collect(
	                Collectors.groupingBy(Basket::getName, Collectors.summingInt(Basket::getQty)));

        System.out.println(sum);
	    
        Map<Double, Set<String>> result =
        		basket.stream().collect(
                        Collectors.groupingBy(Basket::getPrice,
                                Collectors.mapping(Basket::getName, Collectors.toSet())
                        )
                );

        System.out.println(result);
        
        Map<String, Integer> itemsTotalQty = new HashMap<String, Integer>() ;
        
        for(Map.Entry<String, Long> count : counting.entrySet()){
        	
        	for(Map.Entry<String, Integer> qty : sum.entrySet()) {
        		
        		if (count.getKey() == qty.getKey()) {
        			
        			int totalQty = (int) (count.getValue()*qty.getValue());
        			itemsTotalQty.put(count.getKey(), totalQty);
        			
        		}
        	}
        }
		 
        System.out.println(itemsTotalQty);
        
		
         
        Map<String, Double> bskt = new HashMap<String, Double>();
        
        for(Map.Entry<Double, Set<String>> count : result.entrySet()){
        	
        	for(Map.Entry<String, Integer> qty : itemsTotalQty.entrySet()){
        
        		for(String itemname : count.getValue()) {
        			
        			if (qty.getKey() == itemname) {
        				
        				Double itemCost = qty.getValue()* count.getKey();
        				
        				bskt.put(qty.getKey(), itemCost);
        			}
        		}
        	}
        }
        
        System.out.println(bskt);
        
        for(Map.Entry<String, Double> basketContent : bskt.entrySet()) {
        	basketCost = basketCost + basketContent.getValue();
        }
         
        return basketCost;
	}

}
