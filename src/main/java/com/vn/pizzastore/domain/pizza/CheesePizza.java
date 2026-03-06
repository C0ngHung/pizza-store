package com.vn.pizzastore.domain.pizza;

import java.util.List;

public class CheesePizza extends Pizza {

    public CheesePizza() {
        this.name = "The Best Cheese Pizza";
        this.dough = "Very Thin Dough";
        this.sauce = "Very Spicy Sauce";
        this.toppings.addAll(List.of("Cheese", "Mozzarella", "Sausage"));
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough..." + dough);
        System.out.println("Adding sauce..." + sauce);
        System.out.println("Adding toppings: ");
        toppings.forEach(topping -> System.out.println("\t" + topping));
    }
}
