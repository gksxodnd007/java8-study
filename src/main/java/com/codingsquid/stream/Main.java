package com.codingsquid.stream;

public class Main {

    public static void main(String[] args) {
        System.out.println("스트림 중간 연산");
        System.out.println("--------------------------------");
        IntermediateOperation.filter();
        System.out.println("--------------------------------");
        IntermediateOperation.sorted();
        System.out.println("--------------------------------");
        IntermediateOperation.limit();
        System.out.println("--------------------------------");
        IntermediateOperation.intRange();
        System.out.println("--------------------------------");
        IntermediateOperation.iterate();
        System.out.println("\n-------------------------------");
        IntermediateOperation.map();
        System.out.println("--------------------------------");
        IntermediateOperation.flatMap();
        System.out.println("\n\n스트림 최종 연산");
        System.out.println("--------------------------------");
        FinalOperation.collect();
        System.out.println("--------------------------------");
        FinalOperation.reduce();
    }

}
