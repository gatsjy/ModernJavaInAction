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
                // 1단계부분
                .filter(d->d.getCalories() <400)
                // 2단계부분
                .sorted(comparing(Dish::getCalories))
                // 3단계부분
                .map(Dish::getName)
                .collect(toList());
    }

    private static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
        List<Dish> lowCalroricDishes = new ArrayList<>();
        // 1단계 부분..
        for(Dish d : dishes){
            if(d.getCalories() < 400){
                lowCalroricDishes.add(d);
            }
        }
        // 여기서 끝난게 아니지.. String으로 다시 뽑아줘야 한다.
        List<String> lowCaloricDishesName = new ArrayList<>();
        // 2단계 소팅하는 부분..
        Collections.sort(lowCalroricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });
        // 3단계 소팅된 값에서 이름만 뽑아준다.
        // String으로 뽑아줄 때에는 또 람다식을 통해서 값을 뽑아온다..
        for (Dish d : lowCalroricDishes) {
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }
}
