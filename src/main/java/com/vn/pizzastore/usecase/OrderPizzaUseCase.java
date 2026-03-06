package com.vn.pizzastore.usecase;

import com.vn.pizzastore.factory.PizzaFactory;
import com.vn.pizzastore.domain.pizza.Pizza;
import com.vn.pizzastore.domain.pizza.PizzaType;

public class OrderPizzaUseCase {

    private final PizzaFactory pizzaFactory;

    public OrderPizzaUseCase(PizzaFactory pizzaFactory) {
        this.pizzaFactory = pizzaFactory;
    }

    public Pizza orderPizza(PizzaType pizzaType) {
        Pizza pizza = pizzaFactory.createPizza(pizzaType);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}
