package com.codingsquid.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FinalOperation {

    private static String data = GetData.data;

    public static void collect() {
        Stream<String> strStream = Arrays.stream(data.split(","));
        List<Character> list = strStream.map(str -> str.charAt(0))
                .collect(Collectors.toList());

        for (Character ch : list) {
            System.out.print(ch + " ");
        }
    }

    public static void reduce() {
        Stream<String> strStream = Arrays.stream(data.split(","));
        System.out.println(strStream.reduce((str1, str2) -> str1 + str2).get());
    }
}
