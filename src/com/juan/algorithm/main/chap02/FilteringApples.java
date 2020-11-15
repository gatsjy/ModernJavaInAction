package com.juan.algorithm.main.chap02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Gatsjy on 2020-11-13
 * Blog : https://blog.naver.com/gkswndks123
 * Github : https://github.com/gatsjy
 */
public class FilteringApples {
    // 시시각각 변하는 사용자 요구 사항에 어떻게 대응해야 할까?
    // -> 새로 추가하는 기능은 쉽게 구현할 수 있으며, 장기적인 관점에서 유지보수가 쉬워야 한다.
    // 동작 파라미터화를 이용하면 자주 바뀌는 요구사항에 효과적으로 대응할 수 있다.
    // 동작 파라미터화란 아직은 어떻게 실행할 것인지 결정하지 않은 코드 블록을 의미한다.
    // - 리스트의 모든 요소에 대해서 '어떤 동작'을 수행할 수 있음
    // - 리스트 관련 작업을 끝낸 다음에 '어떤 다른 동작'을 수행 할 수 있음
    // - 에러가 발생하면 '정해진 어떤 다른 동작'을 수행 할 수 있음

    // 2.2 동작 파라미터화
    // 참 또는 거짓을 반환하는 함수를 프레디케이트라고 한다.
    // 선택조건을 결정하는 인터페이스를 정의하자.
    public interface ApplePredicate{
        boolean test (Apple apple);
    }

    public interface AppleToString{
        String Accept (Apple apple);
    }

    public static class GoodAppleToString implements AppleToString{

        @Override
        public String Accept(Apple apple) {
            String isHeavy = apple.getWeight() > 110 ? "Heavy" : "Light";
            return isHeavy + apple.getColor() + " Apple";
        }
    }

    // 다음예제처럼 다양한 선택 조건을 대표하는 여러버전의 ApplePredicate를 정의할 수 있다.
    public class ApplesWeightPredicate implements ApplePredicate{
        public boolean test(Apple apple){
            return apple.getWeight() > 150;
        }
    }

    public class AppleGreenColorPredicate implements ApplePredicate{

        @Override
        public boolean test(Apple apple) {
            return apple.getColor() == Color.GREEN;
        }
    }


    public static void stringApplesName(List<Apple> inventory, AppleToString p){
        for (Apple apple : inventory) {
            String output = p.Accept(apple);
            System.out.println("output = " + output);
        }
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    // 빨간색이면서 150이상인 사과를 가져다 주세요
    public static class AppleRedColorAndHeavyPredicate implements ApplePredicate{

        @Override
        public boolean test(Apple apple) {
            return apple.getColor().equals(Color.RED) && apple.getWeight() > 110;
        }
    }


    // color를 파라미터화 해서 유지보수성을 증가시킴
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(apple.getColor().equals(color)){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(apple.getWeight() > weight){
                result.add(apple);
            }
        }
        return result;
    }

    public static class Apple {

        private int weight = 0;
        private Color color;

        public Apple(int weight, Color color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Apple{color=%s, weight=%d}", color, weight);
        }

    }

    enum Color {
        RED,
        GREEN
    }

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED));

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        List<Apple> redAndHeavyApples = filterApples(inventory, new AppleRedColorAndHeavyPredicate());
        System.out.println("redAndHeavyApples = " + redAndHeavyApples);

        stringApplesName(inventory, new GoodAppleToString());

        // 자바는 클래스의 선언과 인스턴스화를 동시에 수행 할 수 있도록 익명클래스 라는 기법을 제공한다.
        // 2.3.1 익명 클래스
        // 익명 클래스는 자바의 지역 클래스(블록 내부에 선언된 클래스)와 비슷한 개념이다. 익명 클래스는 말 그대로 이름이 없는 클래스이다.
        // 익명클래스를 이용하면 클래스의 선언과 인스턴스화를 동시에 할 수 있다. 즉, 즉석에서 필요한 구현을 만들어서 사용할 수 있다.
        List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return Color.RED.equals(apple.getColor());
            }
        });

        // 2.3.3 여섯 번째 시도 : 람다 표현식 사용
        // 와 진짜 깔끔하게 위 사항을 표현 할 수 있다.
        List<Apple> result = filterApples(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));

        List<Integer> evennumbers = filter(numbers, (Integer i) -> i % 2 == 0);

    }
    // 2.3.4 일곱 번째 시도 : 리스트 형식으로 추상화
    public interface Predicate<T>{
        boolean test(T t);
    }
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if(p.test(e)){
                result.add(e);
            }
        }
        return result;
    }

}
