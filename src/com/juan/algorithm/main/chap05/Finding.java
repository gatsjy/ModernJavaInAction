package com.juan.algorithm.main.chap05;

import com.juan.algorithm.main.chap04.Dish;

import java.util.Optional;

import static com.juan.algorithm.main.chap04.Dish.menu;

/**
 * @author Gatsjy
 * @since 2020-11-15
 * realize dreams myself
 * Blog : https://blog.naver.com/gkswndks123
 * Github : https://github.com/gatsjy
 */
public class Finding {
    public static void main(String[] args) {
        if(isVegetreianFriendlyMenu()){
            System.out.println("Vegetarian friendly");
        }
    }

    private static boolean isVegetreianFriendlyMenu(){
        return menu.stream().anyMatch(Dish::isVegetarian);
    }

    private static boolean isHealthyMenu(){
        return menu.stream().allMatch(d->d.getCalories() < 1000);
    }

    private static boolean isHealthyMenu2(){
        return menu.stream().noneMatch(d->d.getCalories() >= 1000);
    }

    private static Optional<Dish> findVegeterianDish(){
        return menu.stream().filter(Dish::isVegetarian).findAny();
    }
}
