package com.juan.algorithm.main.chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Gatsjy
 * @since 2020-11-15
 * realize dreams myself
 * Blog : https://blog.naver.com/gkswndks123
 * Github : https://github.com/gatsjy
 */
public class Lambdas {
    public static void main(String[] args) {
        // 3.1 람다란 무엇인가?
        // -> 람다 표현식은 메서드로 전달할 수 있는 익명 함수를 단순화한 것이라고 할 수 있다.
        // - 익명
        // 보통의 메서드와 달리 이름이 없으므로 익명이라고 표현한다. 구현해야 할 코드에 대한 걱정거리가 줄어든다.
        // - 함수
        // 람다는 메서드처럼 특정 클래스에 종속되지 않으므로 함수라고 부른다. 하지만 메서드처럼 파라미터 리스트, 바디, 반환 형식, 가능한 예외 리스트를 포함한다.
        // - 전달
        // 람다 표현식을 메서드 인수로 전달하거나 변수로 저장할 수 있다.
        // - 간결설
        // 익명 클래스처럼 많은 자질구레한 코드를 구현할 필요가 없다.

        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
        );

        // 조금 이상해 보일 수 있지만 아래는 정상적인 람다 표현식이다.
        process(() -> System.out.println("This is awesome!!"));

        // 결론적으로 이미 살펴본 것처럼 중괄호는 필요 없다. 자바 언어 명세에서는 void를 반환하는 메소드 호출과 관련한 특별한 규칙을 정하고 있기 때문이다.
        // 즉 한 개의 void 메소드 호출은 중괄호로 감쌀 필요가 없다.
        process(() -> {System.out.println("This is awesome!!2"); });
    }

    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    interface ApplePredicate{
        boolean test(Apple a);
    }
    
    // 3.2.1 함수형 인터페이스
    public interface Predicate<T>{
        boolean test (T t);
    }
    // 간단히 말해 함수형 인터페이스는 정확히 하나의 추상 메서드를 지정하는 인터페이스다. 지금 까지 살펴온 자바 API의 함수형 인터페이스로 Comparator, Runnable 등이 있다.
    public interface Comparator<T>{
        int compare(T o1, T o2);
    }
    public interface Runnable{
        void run();
    }

    public static void process(Runnable r){
        r.run();
    }


}

