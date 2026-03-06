package com.vn.pizzastore.domain.pizza;

import java.util.ArrayList;
import java.util.List;

public abstract class Pizza {

    // Ở đây set protected để các class con có thể truy cập và set giá trị theo ý muốn,
    // còn nếu set private thì sẽ phải tạo setter để set giá trị cho các thuộc tính này
    protected String name;
    protected String dough;
    protected String sauce;
    protected List<String> toppings = new ArrayList<>(); // Khởi tạo để tránh NullPointerException khi gọi phương thức prepare() mà chưa set giá trị cho toppings

    public abstract void prepare();


//    public void prepare() {
//        System.out.println("Preparing " + name);
//        System.out.println("Tossing dough..." + dough);
//        System.out.println("Adding sauce..." + sauce);
//        System.out.println("Adding toppings: ");
//        if (toppings != null) {
//            toppings.forEach(toppings -> System.out.println("\t" + toppings));
//        }
//    }

    public void bake() {
        System.out.println("Baking " + name + " for 25 minutes at 350");
    }

    public void cut() {
        System.out.println("Cutting " + name + " into diagonal slices");
    }

    public void box() {
        System.out.println("Placing " + name + " in official PizzaStore box");
    }

    public String getName() {return name;}
}
