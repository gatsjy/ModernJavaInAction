package com.juan.algorithm.main.chap05;

import com.juan.algorithm.main.chap04.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.juan.algorithm.main.chap04.Dish.menu;

/**
 * @author Gatsjy
 * @since 2020-11-15
 * realize dreams myself
 * Blog : https://blog.naver.com/gkswndks123
 * Github : https://github.com/gatsjy
 */
public class Reducing {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3,4,5,1,2);
        int sum = numbers.stream().reduce(0, (a,b) -> a+b);
        System.out.println(sum);

        int max = numbers.stream().reduce(0, (a,b) -> Integer.max(a,b));
        System.out.println(max);

        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        int caloris = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println(caloris);
    }
}
