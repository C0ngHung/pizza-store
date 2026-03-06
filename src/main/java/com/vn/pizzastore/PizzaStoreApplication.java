package com.vn.pizzastore;

import com.vn.pizzastore.factory.DefaultPizzaFactory;
import com.vn.pizzastore.factory.PizzaFactory;
import com.vn.pizzastore.domain.pizza.Pizza;
import com.vn.pizzastore.domain.pizza.PizzaType;
import com.vn.pizzastore.usecase.OrderPizzaUseCase;

public class PizzaStoreApplication {

    public static void main(String[] args) {

        PizzaFactory pizzaFactory = new DefaultPizzaFactory();
        OrderPizzaUseCase orderPizzaUseCase = new OrderPizzaUseCase(pizzaFactory);

        System.out.println("=== ORDERING PIZZAS ===");

        Pizza cheesePizza = orderPizzaUseCase.orderPizza(PizzaType.CHEESE);
        System.out.println("Ordered: " + cheesePizza.getName());
        System.out.println("----------------------------------");

        Pizza greekPizza = orderPizzaUseCase.orderPizza(PizzaType.GREEK);
        System.out.println("Ordered: " + greekPizza.getName());
        System.out.println("----------------------------------");

        Pizza pepperoniPizza = orderPizzaUseCase.orderPizza(PizzaType.PEPPERONI);
        System.out.println("Ordered: " + pepperoniPizza.getName());
    }
}
