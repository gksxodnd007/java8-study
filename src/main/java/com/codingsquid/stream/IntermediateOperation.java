package com.codingsquid.stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntermediateOperation {

    private static String data = GetData.data;

    //'t'를 포함하는 문자열만 출력
    public static void filter() {
        Stream<String> strStream = Arrays.stream(data.split(","));
        strStream.filter(str -> str.contains("t")).forEach(System.out::println);
    }

    //문자열 정렬 후 출력
    public static void sorted() {
        Stream<String> strStream = Arrays.stream(data.split(","));
        strStream.sorted().forEach(System.out::println);
    }

    //전체 요소 중 일부만 출력
    public static void limit() {
        IntStream.range(0, 10).limit(5).forEach(System.out::println);
    }

    //0 ~ 9 까지의 정수 출력(마지막 10 제외)
    public static void intRange() {
        IntStream.range(0, 10).forEach(System.out::println);
    }

    //씨앗값 부터 해당 함수를 거쳐 10개만 뽑아 출력(무한스트림이 생성되기 때문)
    public static void iterate() {
        IntStream.iterate(1, n -> n + 1).limit(10).forEach(System.out::print);
    }

    //원하는 값만 뽑아 스트림화
    public static void map() {
        Stream<String> strStream = Arrays.stream(data.split(","));
        strStream.map(element -> element.charAt(0)).forEach(System.out::println);
    }

    //map과 flatMap의 차이
    public static void flatMap() {
        Stream<String []> strArrStream = Stream.of(
                new String[] {"abc", "def", "ghi"},
                new String[] {"jkl", "mno", "pqr"}
        );

        Stream<String> strStream = strArrStream.flatMap(Arrays::stream);
        strStream.forEach(System.out::print);
    }

}
