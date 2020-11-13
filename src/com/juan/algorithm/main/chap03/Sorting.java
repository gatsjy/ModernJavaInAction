package com.juan.algorithm.main.chap03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sorting {

  public static void main(String... args) {
    // 3.1 람다란 무엇인가?
    // 람다 표현식은 메서드로 전달할 수 있는 익명 함수를 단순화한 것이라고 할 수 있다.
    // 람다 표현식에는 이름은 없지만, 파라미터 리스트, 바디, 반환 형식, 발생할 수 있는 예외 리스트는 가질 수 있다.

    List<Apple> inventory = new ArrayList<>();
    inventory.addAll(Arrays.asList(
            new Apple(80, Color.GREEN),
            new Apple(155, Color.GREEN),
            new Apple(120, Color.RED)
    ));

    Runnable r1 = () -> System.out.println("Hello world 1");

    Runnable r2 = new Runnable() {
      @Override
      public void run() {
        System.out.println("Hello world 2");
      }
    };

    process(r1);
    process(r2);
    process(() -> System.out.println("Hello world 3"));
  }

  public static void process(Runnable r){
    r.run();
  }
}
