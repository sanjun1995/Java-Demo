package com.sanjun.project.effective.chapter2.item2.hierarchicalbuilder;

import static com.sanjun.project.effective.chapter2.item2.hierarchicalbuilder.NyPizza.Size.*;
import static com.sanjun.project.effective.chapter2.item2.hierarchicalbuilder.Pizza.Topping.*;

/**
 * Created by caozhixin on 2019-08-23 15:07
 */
public class PizzaTest {
    public static void main(String[] args) {
        NyPizza pizza = (NyPizza) new NyPizza.Builder(SMALL)
                .addTopping(SAUSAGE).addTopping(ONION).build();
        Calzone calzone = (Calzone) new Calzone.Builder()
                .addTopping(HAM).sauceInside().build();
        System.out.println(pizza);
        System.out.println(calzone);
    }
}
