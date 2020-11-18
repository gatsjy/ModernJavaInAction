package com.juan.algorithm.main.chap05;

import com.juan.algorithm.main.chap04.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        // 우선 배열 스트림 대신 문자열 스트림이 필요하다. 다음 코드에서 보여주는 것처럼 문자열을 받아 스트림을 만드는 Arrays.stream() 메서드가 있다.

        List<Stream<String>> collect = words.stream()
                .map(word -> word.split("")) // 각 단어를 개별 문자열 배열로 변환
                .map(Arrays::stream) // 각 배열을 별도의 스트림으로 생성
                .distinct()
                .collect(toList());
        // 문제가 해결되지 않았다. 문제를 해결하려면 먼저 각 단어를 개별 문자열로 이루어진 배열로 만든 다음에 각 배열을 별도의 스트림으로 만들어야 한다.

        List<String> uniqueCharacters = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream) // 생성된 스트림을 하나릐 스트림으로 평면화
                .distinct()
                .collect(toList());

        System.out.println(uniqueCharacters);

        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        List<Integer> squares = numbers.stream().map(n -> n * n)
                .collect(toList());

        System.out.println(squares);

        List<Integer> numbers1 = Arrays.asList(1,2,3);
        List<Integer> numbers2 = Arrays.asList(3,4);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                .map(j-> new int[]{i,j})).collect(toList());

        List<int[]> stream = numbers2.stream()
                            .flatMap(i ->
                                    numbers2.stream()
                                            .filter(j -> (i + j) % 3 == 0)
                                            .map(j -> new int[]{i * j}))
                            .collect(toList());

        pairs.forEach(System.out::println);
    }
}
