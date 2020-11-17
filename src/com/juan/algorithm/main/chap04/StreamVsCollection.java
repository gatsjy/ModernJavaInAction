package com.juan.algorithm.main.chap04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Gatsjy
 * @since 2020-11-15
 * realize dreams myself
 * Blog : https://blog.naver.com/gkswndks123
 * Github : https://github.com/gatsjy
 */
public class StreamVsCollection {
    public static void main(String[] args) {
        // 4.3 스트림과 컬렉션
        // 자바의 기존 컬렉션과 새로운 스트림 모두 연속된 요소 형식의 값을 저정하는 자료구조의 인터페이스를 제공한다.
        // 여기서 연속된이라는 표현은 순서와 상관없이 아무 값에나 접속하는 것이 아니라 순차적으로 값에 접근한다는 것을 의미한다.
        List<String> names = Arrays.asList("Java8", "Lambdas", "In", "Action");
        Stream<String> s = names.stream();
        s.forEach(System.out::println);
        // 스트림은 한 번 만 소비할 수 있으므로 아래 행의 주석을 제거하면 IllegerStateException이 발생
        //s.forEach(System.out::println);
        List<String> collect = Dish.menu.stream()
                .filter(Dish -> Dish.getCalories() > 400)
                .map(Dish::getName)
                .collect(Collectors.toList());

        System.out.println(collect);

    }
}
