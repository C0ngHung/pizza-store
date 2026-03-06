package com.vn.pizzastore.factory;

import com.vn.pizzastore.domain.pizza.Pizza;
import com.vn.pizzastore.domain.pizza.PizzaType;

public interface PizzaFactory {
    Pizza createPizza(PizzaType pizzaType);
}
