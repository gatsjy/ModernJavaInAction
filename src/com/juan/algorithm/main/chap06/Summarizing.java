package com.juan.algorithm.main.chap06;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;

import static  com.juan.algorithm.main.chap06.Dish.menu;
import static java.util.stream.Collectors.*;

/**
 * @author Gatsjy
 * @since 2020-11-16
 * realize dreams myself
 * Blog : https://blog.naver.com/gkswndks123
 * Github : https://github.com/gatsjy
 */
public class Summarizing {
    public static void main(String[] args) {
        //1. 갯수 세기
        //System.out.println(howManyDishes());
        //2. 가장 많은 칼로리 찾기
        //System.out.println(findMostCaloricDish());
        //3.
        //System.out.println(findMostCaloricDishUsingComparator());
        //4. calculateAverageCalories
        //System.out.println(calculateAverageCalories());
        System.out.println(calculateMenuStatistics());

    }

    private static long howManyDishes(){
        return menu.stream().collect(counting());
    }

    private static Dish findMostCaloricDish(){
        return menu.stream().collect(reducing((d1,d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
    }

    private static Dish findMostCaloricDishUsingComparator(){
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(dishCaloriesComparator);
        return menu.stream().collect(reducing(moreCaloricOf)).get();
    }

    private static Double calculateAverageCalories(){
        return menu.stream().collect(averagingInt(Dish::getCalories));
    }

    private static IntSummaryStatistics calculateMenuStatistics(){
        return menu.stream().collect(summarizingInt(Dish::getCalories));
    }

}
