package com.vn.pizzastore.domain.pizza;

import java.util.List;

public class GreekPizza extends Pizza{

    public GreekPizza() {
        this.name = "Greek Pizza";
        this.dough = "Thin Crust Dough";
        this.sauce = "Garlic Sauce";
        this.toppings.addAll(List.of("Feta Cheese", "Black Olives", "Spinach"));
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
