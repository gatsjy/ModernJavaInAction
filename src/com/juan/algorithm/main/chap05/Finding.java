package com.juan.algorithm.main.chap05;

import com.juan.algorithm.main.chap04.Dish;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.juan.algorithm.main.chap04.Dish.menu;
import static java.util.stream.Collectors.*;

/**
 * @author Gatsjy
 * @since 2020-11-15
 * realize dreams myself
 * Blog : https://blog.naver.com/gkswndks123
 * Github : https://github.com/gatsjy
 */
public class Finding {
    public static void main(String[] args) {
        if(isVegetrianFriendlyMenu()){
            System.out.println("Vegetarian friendly");
        }

        // 데이터를 어떻게 처리할지는 스트림 API가 관리하므로 편리하게 데이터 관련 작업을 할 수 있다.
        // 따라서 스트림 API 내부적으로 다양한 최적화가 이루어질 수 있다.
        // 스트림 API는 내부 반복 뿐 아니라 코드를 병렬로 실행할지 여부도 결정할 수 있다. 이러한 일은 순차적인 반복을 단일 스레드로 구현하는 외부 반복으로는 달성할 수 없다.
        List<Dish> collect = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());

        System.out.println(collect);
    }

    private static boolean isVegetrianFriendlyMenu(){
        return menu.stream().anyMatch(Dish::isVegetarian);
    }

    private static boolean isHealthyMenu(){
        return menu.stream().allMatch(d->d.getCalories() < 1000);
    }

    private static boolean isHealthyMenu2(){
        return menu.stream().noneMatch(d->d.getCalories() >= 1000);
    }

    private static Optional<Dish> findVegetarianDish(){
        return menu.stream().filter(Dish::isVegetarian).findAny();
    }
}
