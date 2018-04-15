package com.codingsquid.lamda.intro;

import java.util.Scanner;

public class IntroExample {

    public static void introExample() {
        Scanner scanner = new Scanner(System.in);
        //람다식을 인터페이스 참조변수에 저장
        IntroduceFunction introduceFunction = (name, age) ->
                System.out.println("name: " + name + ", age: " + age);
        /*
        * 위의 표현식은 다음과 같다.
        *
        * IntroduceFunction introduceFunction = (IntroduceFunction)((name, age) ->
        *   System.out.println("name: " + name + ", age: " + age));
        *
        * */

        //이름과 나이 입력
        System.out.print("input your name: ");
        String name = scanner.next();
        System.out.print("input your age: ");
        int age = scanner.nextInt();

        //람다식 실행
        introduceFunction.intro(name, age);
    }

}
