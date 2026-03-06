package com.vn.pizzastore;

import com.vn.pizzastore.domain.PizzaStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzaStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaStoreApplication.class, args);

        var pizzaStore = new PizzaStore();
        pizzaStore.OrderPizza();
    }

}
