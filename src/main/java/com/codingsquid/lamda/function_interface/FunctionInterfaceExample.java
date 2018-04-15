package com.codingsquid.lamda.function_interface;

import java.util.function.*;

public class FunctionInterfaceExample {

    public static void predicateExample() {

        Predicate<String> isEmptyStr = text -> text.isEmpty();
        String s = "test";
        if (!isEmptyStr.test(s)) {
            System.out.println("not empty text");
        }

        Supplier<Integer> supplier = () -> (int)(Math.random() * 100) + 1;
        System.out.println(supplier.get());

        IntSupplier intSupplier = () -> (int)(Math.random() * 100) + 100;
        System.out.println(intSupplier.getAsInt());

        Runnable runnable = () -> {
            try {
                for(int i = 0; i < 10; i++) {
                    System.out.println("thread execute");
                    Thread.sleep(1);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        runnable.run();

        Consumer<String> consumer = (text) -> System.out.println(text);
        consumer.accept("hello Lamda expression");

        Function<String, Integer> f = (string) -> Integer.parseInt(string, 16);
        Function<Integer, String> g = (integer) -> Integer.toBinaryString(integer);
        Function<String, String> h = f.andThen(g);
        System.out.println(h.apply("FFA"));

        BiFunction<String, Integer, Integer> referenceMethod = Integer::parseInt;
        System.out.println(referenceMethod.apply("F", 16));

    }
}
