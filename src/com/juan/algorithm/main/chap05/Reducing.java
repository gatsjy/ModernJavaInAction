package com.juan.algorithm.main.chap05;

import com.juan.algorithm.main.chap04.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.juan.algorithm.main.chap04.Dish.menu;

/**
 * @author Gatsjy
 * @since 2020-11-15
 * realize dreams myself
 * Blog : https://blog.naver.com/gkswndks123
 * Github : https://github.com/gatsjy
 */
public class Reducing {

    // 5.5 리듀싱
    // 모든 스트림 요소를 처리해서 값으로 도출하는 질의를 리듀싱 연산이라고 한다.
    public static void main(String[] args) {
        // 5.5.1 요소의 합
        List<Integer> numbers = Arrays.asList(3,4,5,1,2);
        int sum = numbers.stream().reduce(0, (a,b) -> a+b);
        // 더욱더 간결하게 표현
        int simpleSum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(simpleSum);

        // 초기값을 받지않도록 오버로드 된 reduce도 있다. 그러나 이 reduce는 Optional 객체를 반환한다.
        Optional<Integer> optionalSum = numbers.stream().reduce((a, b) -> (a + b));

        // 5.2.2 최댓값과 최솟값
        // 최댓값과 최솟값을 찾을 때도 reduce를 활용할 수 있다. reduce를 이용해서 스트림에서 최댓값과 최솟값을 찾는 방법을 살펴보자.
        Optional<Integer> optionalMax = numbers.stream().reduce(Integer::max);
        if(optionalMax.isPresent()){
            System.out.println(optionalMax.get());
        }
        Optional<Integer> optionalMin = numbers.stream().reduce(Integer::min);

        int count = menu.stream().map(d-> 1)
                .reduce(0, (a,b) -> a+b);

        System.out.println(count);

        // 스트림 연산 : 상태 없음과 상태 있음
        // 스트림 연산은 마치 만병통치약 같은 존재다. 스트림을 이용해서 원한느 모든 연산을 쉽게 구현할 수 있으며
        // 컬렉션으로 스트림을 만드는 stream 메서드를 parallelStream으로 바꾸는 것만으로도 별다른 노력 없이 병렬성을 얻을 수 있다.
        // 우리 예제에서 사용한 기법을 많은 애플리케이션에서도 이용한다.
    }
}
