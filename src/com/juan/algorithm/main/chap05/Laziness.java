package com.juan.algorithm.main.chap05;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gatsjy
 * @since 2020-11-15
 * realize dreams myself
 * Blog : https://blog.naver.com/gkswndks123
 * Github : https://github.com/gatsjy
 */
public class Laziness {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8);
        List<Integer> twoEvenSquares = numbers.stream()
                .filter(n -> {
                    System.out.println("filtering " + n);
                    return n% 2==0;
                })
                .map(n->{
                    System.out.println("mapping " +n);
                    return n*n;
                })
                .limit(2)
                .collect(Collectors.toList());
    }
}
