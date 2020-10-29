package com.example.wsy.predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ListRemove {

    public static void main(String[] args) {
        List<Car> list = new ArrayList<>();
        for (int i = 0; i < 100; i ++) {
            Car car = new Car();
            car.setBrand("brand" + i);
            car.setColor("color" + i);
            car.setPrice(i + 1);
            list.add(car);
        }
        list.add(new Car());
        System.out.println("size : " + list.size() + ",  -------  " + list);

        String condition = "2";

        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
//        list.removeIf(car -> Optional.ofNullable(car.getBrand()).map(brand-> brand.contains(condition)).orElse(false));
//        list.removeIf(predicate());
        list.removeIf(car -> Optional.ofNullable(car).map(carInfo ->{return carInfo.getBrand().contains("2") || carInfo.getPrice() < 50;}).orElse(false));
//        list.removeIf(carInfo  ->{return carInfo.getBrand().contains("2") || carInfo.getPrice() < 50;});
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

        System.out.println("size : " + list.size() + ",  -------  " + list);
    }

    public static Predicate<Car> predicate (){
        return new Predicate<Car>() {
            @Override
            public boolean test(Car car) {
                return car.getBrand().contains("3");
            }
        };
    }

}
