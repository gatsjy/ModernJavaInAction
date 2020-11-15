package com.juan.algorithm.main.chap06;
import static  com.juan.algorithm.main.chap06.Dish.menu;
import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;

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
        System.out.println("타입별로 그룹핑 되어진다 : " + groupDishesByType());
    }

    private static Map<Dish.Type, List<Dish>> groupDishesByType(){
        return menu.stream().collect(groupingBy(Dish::getType));
    }

    private static Map<Dish.Type, List<String>> groupDishNamesByType(){
        return menu.stream().collect(
                groupingBy(Dish::getType,)
        )
    }
}
