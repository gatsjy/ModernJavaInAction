package com.juan.algorithm.main.chap05;

import com.juan.algorithm.main.chap04.Dish;

import java.util.Arrays;
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
        // 5.4 검색과 매칭
        // 특정 속성이 데이터 집합에 있는지 여부를 검색하는 데이터 처리도 자주 사용된다. 스트림 API는 allMatch, anyMatch, noneMatch, findFirst, findAny 등 다양한 유틸리티 메서드를 제공한다.
        
        // 5.4.1 프레디케이트가 적어도 한 요소와 일치하는지 확인
        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("The menu is vegeterian friendly");
        }

        // 5.4.2 프레디케이트가 모든 요소와 일치하는지 검사
        boolean isHealthy = menu.stream()
                .allMatch(dish -> dish.getCalories() < 1000);

        // noneMatch는 allMatch와 반대 연산을 수행한다. 즉, noneMatch는 주어진 프레디케이트와 일치하는 요소가 없는지 확인한다.
        // 예를 들어 이전 예제를 다음처럼 noneMatch로 다시 구현할 수 있다.
        boolean isHealth2 = menu.stream()
                .noneMatch(d->d.getCalories() >= 1000);
        // 5.4.3 요소 검색
        // findAny 메서드는 현재 스트림에서 임의의 요소를 반환한다. findAny 메서드를 다른 스트림연산과 연결해서 사용할 수 있다.
        // 예를 들어 다음 코드처럼 filter와 findAny를 이용해서 채식 요리를 선택할 수 있다.
        Optional<Dish> dish =
                menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();

        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(dish2 -> System.out.println(dish2.getName()));
        
        // 5.4.4 첫 번째 요소 찾기
        List<Integer> someNumbers = Arrays.asList(1,2,3,4,5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
                .map(n -> n * n)
                .filter(n -> n % 3 == 0)
                .findFirst();

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
