package com.vn.pizzastore.factory;

import com.vn.pizzastore.domain.pizza.CheesePizza;
import com.vn.pizzastore.domain.pizza.GreekPizza;
import com.vn.pizzastore.domain.pizza.PepperoniPizza;
import com.vn.pizzastore.domain.pizza.Pizza;
import com.vn.pizzastore.domain.pizza.PizzaType;

public class DefaultPizzaFactory implements PizzaFactory {

    @Override
    public Pizza createPizza(PizzaType pizzaType) {
        return switch (pizzaType) {
            case CHEESE -> new CheesePizza();
            case GREEK -> new GreekPizza();
            case PEPPERONI -> new PepperoniPizza();
            default -> throw new IllegalArgumentException("We do not have " + pizzaType + " pizza on our menu.");
        };
    }
}
