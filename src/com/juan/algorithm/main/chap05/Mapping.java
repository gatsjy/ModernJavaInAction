package com.juan.algorithm.main.chap05;

import com.juan.algorithm.main.chap04.Dish;

import java.util.Arrays;
import java.util.List;
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
public class Mapping {

    public static void main(String[] args) {

        // 5.3 매핑
        // 특정 객체에서 특정 데이터를 선택하는 작업은 데이터 처리 과정에서 자주 수행되는 연산이다.

        // 5.3.1 스트림의 각 요소에 함수 적용하기
        // 스트림은 함수를 인수로 받는 map 메서드를 지원한다. 인수로 제공된 함수는 각 요소에 적용되며 함수를 적용한 결과가 새로운 요소로 매핑된다.
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());
        System.out.println(dishNames);

        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths);

        // 각 요리명의 길이를 알고 싶다면?
        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        System.out.println(dishNameLengths);

        // 5.3.2 스트림 평면화
        // 만약 중복되지 않는 고유 문자로 나타내고 싶다면?
        // 첫번째 케이스
    }
}
