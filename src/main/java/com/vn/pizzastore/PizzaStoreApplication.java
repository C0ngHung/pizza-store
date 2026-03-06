package com.vn.pizzastore;

import com.vn.pizzastore.domain.factory.DefaultPizzaFactory;
import com.vn.pizzastore.domain.factory.PizzaFactory;
import com.vn.pizzastore.domain.pizza.Pizza;
import com.vn.pizzastore.domain.pizza.PizzaType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzaStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaStoreApplication.class, args);

        PizzaFactory pizzaFactory = new DefaultPizzaFactory();

        runPizzaFlow(pizzaFactory.createPizza(PizzaType.CHEESE));
        System.out.println("----------------------------------");

        runPizzaFlow(pizzaFactory.createPizza(PizzaType.GREEK));
        System.out.println("----------------------------------");

        runPizzaFlow(pizzaFactory.createPizza(PizzaType.PEPPERONI));
    }

    private static void runPizzaFlow(Pizza pizza) {
        System.out.println("Ordering a " + pizza.getClass().getSimpleName());
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }
}

