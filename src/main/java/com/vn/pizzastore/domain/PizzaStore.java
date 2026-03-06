package com.vn.pizzastore.domain;

import com.vn.pizzastore.domain.pizza.CheesePizza;
import com.vn.pizzastore.domain.pizza.Pizza;

public class PizzaStore {

    public void OrderPizza() {
        Pizza pizza = new CheesePizza();
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }
}
