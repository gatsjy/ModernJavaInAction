package com.juan.algorithm.main.chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Gatsjy
 * @since 2020-11-15
 * realize dreams myself
 * Blog : https://blog.naver.com/gkswndks123
 * Github : https://github.com/gatsjy
 */
public class Lambdas {
    public static void main(String[] args) {
        // 간단한 예제
        Runnable r = () -> System.out.println("Hello");
        r.run();
        
        // 람다로 거름
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
        );

        List<Apple> greenApples = filter(inventory, (Apple a) -> a.getColor() == Color.GREEN);
        System.out.println(greenApples);

        Comparator<Apple> c = (Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight();

        inventory.sort(c);
        System.out.println(inventory);
    }

    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    interface ApplePredicate{
        boolean test(Apple a);
    }
}
