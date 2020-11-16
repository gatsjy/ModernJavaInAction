package com.juan.algorithm.main.chap06;
import static com.juan.algorithm.main.chap06.Dish.dishTags;
import static  com.juan.algorithm.main.chap06.Dish.menu;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author Gatsjy
 * @since 2020-11-16
 * realize dreams myself
 * Blog : https://blog.naver.com/gkswndks123
 * Github : https://github.com/gatsjy
 */
public class Grouping {
    enum CaloricLevel{DIET, NORMAL, FAT};

    public static void main(String[] args) {
        //System.out.println("타입별로 그룹핑 되어진다 : " + groupDishesByType());
        //System.out.println(groupDishNamesByType());
        //System.out.println(groupDishTagsByType());
        //System.out.println(groupDishesByCaloricLevel());
        System.out.println(mostCaloricDishedByType());
    }

    private static Map<Dish.Type, List<Dish>> groupDishesByType(){
        return menu.stream().collect(groupingBy(Dish::getType));
    }

    // 이 부분은 잘 이해가 가지 않는다..
    private static Map<Dish.Type, List<String>> groupDishNamesByType(){
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        mapping(Dish::getName, toList())));
    }

    private static Map<Dish.Type, Set<String>> groupDishTagsByType(){
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())));
    }

    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel(){
        return menu.stream().collect(
                groupingBy(dish ->{
                    if(dish.getCalories() <= 400){
                        return CaloricLevel.DIET;
                    }else if(dish.getCalories() <= 700){
                        return CaloricLevel.NORMAL;
                    }else{
                        return CaloricLevel.FAT;
                    }
                })
        );
    }

    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishedByType(){
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
    }
}
