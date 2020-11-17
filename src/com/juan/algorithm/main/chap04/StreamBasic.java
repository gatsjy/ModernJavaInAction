package com.juan.algorithm.main.chap04;

import javax.swing.*;
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

        List<String> threeHighCaloricDishNames =
                Dish.menu.stream()
                .filter(dish -> dish.getCalories() > 300) // 람다를 인수로 받아 스트림에서 특정 요소를 제외시킨다.
                .map(Dish::getName) // 람다를 이용해서 한 요소를 다른 요소로 변환하거나 정보를 추출한다.
                .limit(3) // 선착순 3개만 선택 정해진 개수 이상의 요소가 스트림에 저장되지 못하게 스트림 크기를 축소한다.
                .collect(toList()); // 결과를 다른 리스트에 저장한다.
        System.out.println(threeHighCaloricDishNames);
    }

    // 이제 자바 8이다.
    // parallelStream()을 호출했을 때 정확히 어떤 일이 일어날까?
    // 얼마나 많은 스레드가 사용되는 걸까? 얼마나 성능이 좋은 걸까?
    // 다음의 장점?
    // 선언형으로 코드를 구현할 수 있다. 즉, 루프와 if 조건문 등의 제어 블록을 사용해서 어떻게 동작을 구현할지 지정할 필요 없이 '저칼로리의 요리만 선택하라' 같은 동작의 수행을 지정할 수 있다.
    // 3장에서 살펴본 것처럼 선언형 코드와 동작 파라미터화를 활용하면 변하는 요구사항에 쉽게 대응할 수 있다.
    // 여러 빌딩 블록 연산을 연결해서 복잡한 데이터 처리 파이프라인을 만들 수 있다. 여러 연산을 파이프라인으로 연결해도 여전히 가독성과 명확성이 유지된다.
    // filter의 메서드의 결과는 sorted 메서드로, 다시 sorted 결과는 map 메서드로, map 메서드의 결과는 collect로 연결된다.
    private static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        return dishes.parallelStream()
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
        for(Dish d : dishes){ // 누적자로 요소 필터링
            if(d.getCalories() < 400){
                lowCalroricDishes.add(d);
            }
        }
        // 여기서 끝난게 아니지.. String으로 다시 뽑아줘야 한다.
        List<String> lowCaloricDishesName = new ArrayList<>();
        // 2단계 소팅하는 부분.. 익명 클래스로 요리를 정렬한다.
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
