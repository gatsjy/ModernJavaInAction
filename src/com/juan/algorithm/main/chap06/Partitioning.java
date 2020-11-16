package com.juan.algorithm.main.chap06;

import static  com.juan.algorithm.main.chap06.Dish.menu;
import static java.util.stream.Collectors.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Gatsjy
 * @since 2020-11-16
 * realize dreams myself
 * Blog : https://blog.naver.com/gkswndks123
 * Github : https://github.com/gatsjy
 */
public class Partitioning {

    public static void main(String[] args) {
        //System.out.println(partitionByVegeterian());
        //System.out.println(vegeterianDishesByType());
        System.out.println(mostCaloricPartitinedByVegetarian());
    }

    private static Map<Boolean, List<Dish>> partitionByVegeterian(){
        return menu.stream().collect(groupingBy(Dish::isVegetarian));
    }

    private static Map<Boolean, Map<Dish.Type, List<Dish>>> vegeterianDishesByType(){
        return menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
    }

    private static Object mostCaloricPartitinedByVegetarian(){
        return menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get)
                        ));
    }
}
