package com.vn.pizzastore;

import com.vn.pizzastore.domain.pizza.CheesePizza;
import com.vn.pizzastore.domain.pizza.GreekPizza;
import com.vn.pizzastore.domain.pizza.PepperoniPizza;
import com.vn.pizzastore.domain.pizza.Pizza;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzaStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaStoreApplication.class, args);

        runPizzaFlow(new CheesePizza());
        System.out.println("----------------------------------");

        runPizzaFlow(new GreekPizza());
        System.out.println("----------------------------------");

        runPizzaFlow(new PepperoniPizza());
    }

    private static void runPizzaFlow(Pizza pizza) {
        System.out.println("Ordering a " + pizza.getClass().getSimpleName());
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }
}

