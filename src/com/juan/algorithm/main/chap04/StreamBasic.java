package com.juan.algorithm.main.chap04;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * Created by Gatsjy on 2020-11-14
 * Blog : https://blog.naver.com/gkswndks123
 * Github : https://github.com/gatsjy
 */
public class StreamBasic {
    public static void main(String[] args) {

        // 자바 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);

        System.out.println("---");

        // 자바 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);
    }

    // 이제 자바 8이다.
    private static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        return dishes.stream()
                // 칼로리가 400미만인 것을 골라낸다.
                .filter(d->d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
    }

    private static List<String> getLowCaloricDishedNamesInJava8_2(List<Dish> dishes){
        return dishes.stream()
                .filter(d->d.getType().equals(Dish.Type.MEAT)) // c# where 같은 느낌
                .sorted(comparing(Dish::getName))
                .map(Dish::getName) // c# select 같은 느낌이다.
                .collect(toList());
    }

    private static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for(Dish d : dishes){
            if(d.getCalories() < 400){
                lowCaloricDishes.add(d);
            }
        }
        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }
}
