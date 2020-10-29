package com.example.wsy.pattern.builder;

public class BuilderPatternDemo {
    public static void main(String [] args) {
        MealBuilder builder = new MealBuilder();

        Meal meal1 = builder.prepareVegMeal();
        System.out.println("Veg Meal");
        meal1.showItems();
        System.out.println(meal1.cost());

        Meal meal2 = builder.prepareNonVegMeal();
        System.out.println("Chicken Meal");
        meal2.showItems();
        System.out.println(meal2.cost());

    }
}
