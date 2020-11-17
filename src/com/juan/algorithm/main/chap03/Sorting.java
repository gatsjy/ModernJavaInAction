package com.juan.algorithm.main.chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

import static java.util.Comparator.comparing;

public class Sorting {

  public static void main(String... args) {
    // 3.6.1 요약
    // 메서드 참조가 왜 중요한가? 메서드 참조는 특정 메서드만을 호출하는 람다의 축양형이라고 생각할 수 있다. 예를 들어 람다가
    // '이 메서드를 직접 호출해'라고 명령한다면 메서드를 어떻게 호출해야 하는지 설명을 참조하보다는 메서드명을 직접 참조하는 것이 편리하다.
    // 실제 메서드 참조를 이용하면 기존 메서드 구현으로 람다 표현식을 만들 수 있다. 이때 명시적으로 메서드명을 참조함으로써 가독성을 넓힐 수 있다.
    // 메서드 참조는 어떻게 활용할까? 메서드명 앞에 구분자(::)를 붙이는 방식으로 메서드를 참조할 수 있다.
    // - 메서드 참조를 만드는 방법
    // 메서드 참조는 세 가지 유형으로 구분할 수 있다.
    // 1. 정적 메서드 참조
    // 2. 다양한 형식의 인스턴스 메서드 참조
    // 3. 기존 객체의 인스턴스 메서드 참조
    // ex) (String s) -> s.toUpperCase() -> String::toUpperCase
    List<Apple> inventory = new ArrayList<>();
    inventory.addAll(Arrays.asList(
            new Apple(80, Color.GREEN),
            new Apple(155, Color.GREEN),
            new Apple(120, Color.RED)
    ));

    inventory.sort(comparing(Apple::getWeight));

    // [Apple{color=GREEN, weight=80}, Apple{color=RED, weight=120}, Apple{color=GREEN, weight=155}]
    //inventory.sort(new AppleComparator());
    //System.out.println(inventory);

    inventory.set(1, new Apple(30, Color.GREEN));

    //[Apple{color=GREEN, weight=30}, Apple{color=GREEN, weight=80}, Apple{color=GREEN, weight=155}]
    inventory.sort(new Comparator<Apple>() {
      @Override
      public int compare(Apple o1, Apple o2) {
        return o1.getWeight() - o2.getWeight();
      }
    });

    System.out.println(inventory);

    // reshuffling things a little
    inventory.set(1, new Apple(20, Color.RED));

    //[Apple{color=RED, weight=20}, Apple{color=GREEN, weight=30}, Apple{color=GREEN, weight=155}]
    // 내부적으로 형을 유추해서 comparator을 돌려준다...
    inventory.sort((a1, a2) -> a1.getWeight() - a2.getWeight());
    System.out.println(inventory);

    // reshuffling things a little
    inventory.set(1, new Apple(10, Color.RED));

    // ... 위의 람다식을 엄청나게 간단하게 줄여준다.
    // asis inventory.sort((a1,a2) -> a1.getWeight() - a2.getWeight());
    inventory.sort(comparing(Apple::getWeight));
    System.out.println(inventory);

    // [퀴즈 3-6]
    ToIntFunction<String> stringToInt = (String s) -> Integer.parseInt(s);
    Function<String, Integer> stringToInteger = Integer::parseInt;

    BiPredicate<List<String>, String> comtains =(list, element)->list.contains(element);
    BiPredicate<List, Object> listObjectBooleanBiFunction = List::contains;


  }

  // 3.7 람다, 메서드 참조 활용하기
  // 3.7.1 1단계 : 코드 전달
}
