package com.vn.pizzastore.domain.pizza;

import java.util.List;

public class PepperoniPizza extends Pizza{

    public PepperoniPizza() {
        this.name = "Pepperoni Pizza";
        this.dough = "Thick Crust Dough";
        this.sauce = "Tomato Sauce";
        this.toppings.addAll(List.of("Sliced Pepperoni", "Sliced Onion", "Grated Parmesan Cheese"));
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
