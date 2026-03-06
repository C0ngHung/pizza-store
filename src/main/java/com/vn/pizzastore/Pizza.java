package com.vn.pizzastore;

import java.util.List;

public class Pizza {

    private String name;
    private String dough;
    private String sauce;
    private List<String> toppings;

    public void setName(String name) { this.name = name; }
    public void setDough(String dough) { this.dough = dough; }
    public void setSauce(String sauce) { this.sauce = sauce; }
    public void setToppings(List<String> toppings) { this.toppings = toppings; }


    public void prepare() {
        System.out.println("Preparing " + name);
        System.out.println("Tossing dough..." + dough);
        System.out.println("Adding sauce..." + sauce);
        System.out.println("Adding toppings: ");
        if (toppings != null) {
            toppings.forEach(toppings -> System.out.println("\t" + toppings));
        }
    }

    public void bake() {
        System.out.println("Baking " + name + " for 25 minutes at 350");
    }

    public void cut() {
        System.out.println("Cutting " + name + " into diagonal slices");
    }

    public void box() {
        System.out.println("Placing " + name + " in official PizzaStore box");
    }
}
