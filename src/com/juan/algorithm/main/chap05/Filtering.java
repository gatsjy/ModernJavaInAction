package com.juan.algorithm.main.chap05;

import com.juan.algorithm.main.chap04.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.juan.algorithm.main.chap04.Dish.*;
import static java.util.stream.Collectors.*;

/**
 * @author Gatsjy
 * @since 2020-11-17
 * realize dreams myself
 * Blog : https://blog.naver.com/gkswndks123
 * Github : https://github.com/gatsjy
 */
public class Filtering {
    public static void main(String[] args) {
        // filter 메서드는 프레디케이트(불리언을 반환하는 함수)를 인수로 받아서 프레디케이트와 일치하는 모든 요소를 포함하는 스트림을 반환한다.
        // 5.1.1 프레디케이트로 필터링
        List<Dish> vagetarianMenu = menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());
        
        // 5.1.2 고유 요소 필터링
        // 스트림은 고유 요소로 이루어진 스트림을 반환하는 distinct 메서드도 지원한다.(고유 여부는 스트림에서 만든 객체의 hashCode, equal로 결정된다)
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i%2 ==0)
                .distinct()
                .forEach(System.out::println);

        // 5.2 스트림 슬라이싱
        // 스트림의 요소를 선택하거나 스킵하는 다양한 방법을 설명한다.
        // 프레트케이트를 이용하는 방법, 스트림의 처음 몇개의 요소를 무시하는 방법, 특정 크기로 스트림을 줄이는 방법 등 다양한 방법을 이용해 효율적으로 이런 작업을 수행할 수 있다.
        
        // 5.2.1 프레디케이트를 이용한 슬라이싱
        List<Dish> specialMenu = Arrays.asList(
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER));
        System.out.println("Filtered sorted menu:");
        // TAKEWHILE 활용
        // 어떻게 320 칼로리 이하의 요리를 선택할 수 있을까?
        List<Dish> filteredMenu = specialMenu.stream()
                .filter(dish -> dish.getCalories() < 320)
                .collect(toList());
        // 320 칼로리보다 크거나 같은 요리가 나왔을 때 반복 작업을 중단할 수 있다.
        List<Dish> slicedMenu1 = specialMenu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .collect(toList());

        System.out.println(slicedMenu1);

        // 320 칼로리보다 크거나 같은 요리가 나왔을 때 부터 나머지 값을 출력한다.
        List<Dish> slicedMenu2 = specialMenu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .collect(toList());

        System.out.println(slicedMenu2);
        
        // 5.2.2 스트림 축소
        // 스트림은 주어진 값 이하의 크기를 갖는 새로운 스트림을 반환하는 limit(n) 메서드를 지원한다.
        // 스트림이 정렬되어 있으면 최대 n개를 반환할 수 있다.
        // 예를 들어 다음처럼 300칼로리 이상의 세 요리를 선택해서 리스트를 만들 수 있다.
        List<Dish> dishes = specialMenu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(toList());

        System.out.println(dishes);

        // 5.2.3 요소 건너뛰기
        List<Dish> dishes2 = specialMenu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(2)
                .collect(toList());

        System.out.println(dishes2);

        // [퀴즈 5-1]
        List<Dish> quiz = specialMenu.stream()
                .filter(dish -> dish.getType().equals(Type.MEAT))
                .limit(2)
                .collect(toList());

        System.out.println(quiz);

    }
}
