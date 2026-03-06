package com.vn.pizzastore;

public class PizzaStore {

    public void OrderPizza() {
        Pizza pizza = new Pizza();
        // Mặc định là Cheese Pizza cho basic flow
        pizza.setName("Cheese Pizza");
        pizza.setDough("Regular Crust");
        pizza.setSauce("Marinara Sauce");
        
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }
}
