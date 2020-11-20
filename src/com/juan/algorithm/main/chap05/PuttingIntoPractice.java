package com.juan.algorithm.main.chap05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * @author Gatsjy
 * @since 2020-11-20
 * realize dreams myself
 * Blog : https://blog.naver.com/gkswndks123
 * Github : https://github.com/gatsjy
 */
public class PuttingIntoPractice {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        
        // 1.2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오
        List<Transaction> q1 = transactions.stream()
                .filter(d -> d.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(toList());

        System.out.println(q1);

        // 2. 거래자가 근무하는 모든 도시를 중복 없이 나열 하시오.
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());

        System.out.println(cities);

        // 3. 케임브리지에서 근무하는 모든 거래자를 찾아 이름순으로 정렬하시오.
        List<String> names = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(d -> d.getTrader().getName())
                .collect(toList());

        System.out.println(names);

        // 4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.
        String q4 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);

        System.out.println(q4);

        // 5. 밀라노에 거래자가 있는가?
        boolean milan = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        System.out.println(milan);

        // 6. 케임브릿지에 거주하는 거래자의 모든 트랜잭션 값을 출력하시오
        List<Integer> transactionsInCambridge = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Camebridge"))
                .map(Transaction::getValue)
                .collect(toList());

        transactions.stream()
                .filter(t-> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // 7. 전체 트랜잭션 중 최댓값은 얼마인가?
        Optional<Integer> reduce = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        System.out.println(reduce);

        Optional<Transaction> reduce1 = transactions.stream()
                .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2);

        System.out.println(reduce1);


    }
}
