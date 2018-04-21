# JAVA 8 STUDY

Content
-------

-	Lambda
-	Stream
-	Optional<T>

디렉토리 구성
-------------

> basePath: src/main/java/com/codingsquid

| dir      | info      |
|:---------|:----------|
| lambda   | 람다식 예제코드   |
| optional | 래퍼 클래스 예제코드 |
| stream   | 스트림 예제코드 |

## 람다식이란?

람다식(lambda expression)은 간단히 말해서 메서드를 하나의 '식(expression)'으로 표현한 것이다. 메서드를 람다식으로 표현하면 메서드의 이름과 반환값이 없어지므로, 람다식을 '익명 함수(anonymous function)'이라고도 한다.

```java
int[] arr = new int[5];
Arrays.setAll(arr, i -> (int)Math.random() * 5) + 1;
```

위의 람다식은 다음 메서드와 같다.

```java
int method(int i) {
  return (int)(Math.random() * 5) + 1;
}
```

위의 메서드는 클래스에 포함되어야 하므로 클래스를 새로 만들어야 하고, 객체도 생성해야만 비로소 필요한 메서드를 호출 할 수 있다. 그러나 람다식은 이 모든 과정없이 오직 람다식 자체만으로도 이 메서드의 역할을 대신할 수 있다. **게다가 람다식은 메서드의 매개변수로 전달되어지는 것이 가능하고, 메서드의 결과로 반환 될 수도 있다.**

다음은 람다의 사용법이다.

**일반 메서드**

```java
반환타입 메서드이름(매개변수 선언) {
  문장들
}
```

**람다식**

```java
(매개변수 선언) -> {
  문장들
}
```

반환값이 있는 메서드의 경우, return문 대신 '식(expression)'으로 대신 할 수 있다. 식의 연산결과가 자동적으로 반환값이 된다. 이때는 '문장(statement)'이 아닌 '식'이므로 끝에 ';'을 붙이지 않는다.

```java
//변경 전
(int a, int b) -> {
  return a > b ? a : b;
}

//변경 후
(int a, int b) -> a > b ? a : b
```

람다식에 선언된 매개변수의 타입은 추론이 가능한 경우는 생략할 수 있는데, 대부분의 경우에 생략가능하다. 람다식에 변환타입이 없는 이유도 항상 추론이 가능하기 때문이다.

```java
(a, b) -> a > b ? a : b
```

아래와 같이 선언된 매개변수가 하나뿐인 경우에는 괄호()를 생략할 수 있다. 단, 매개변수의 타입이 있으면 괄호()를 생략할 수 없다.

```java
//변경 전
(a) -> a * a
//변경 후
a -> a * a //ok

//변경 전
(int a) -> a * a
//변경 후
int a -> a * a //error
```

마찬가지로 괄호{}안의 문장이 하나일 때는 괄호{}를 생략할 수 있다. 이 때 문장의 끝에 ';'을 붙이지 않아야 한다는 것에 주의하자.

```java
//변경 전
(String name, int age) -> {
  System.out.println("name: " + name ", age:" + age);
}
//변경 후
(String name, int age) ->
  System.out.println("name: " + name ", age:" + age);
```

괄호{}안의 문장이 return문일 경우 괄호{}를 생략할 수 없다.

```java
(int a, int b) -> { return a > b ? a : b;} //ok
(int a, int b) -> return a > ? a : b //error
```

자바에서 모든 메서드는 클래스 내에 포함되어야 하는데, 람다식은 어떤 클래스에 포함되는 것일까? 람다식은 익명 클래스의 객체와 동등하다.

```java
(int a, int b) -> a > b ? a : b

//위 람다식은 다음과 같다.
new Object() {
  int max(int a, int b) {
    return a > b ? a : b;
    }
}
```

람다식으로 정의된 메서드를 호출하려면 참조변수에 저장해야한다.

```java
타입 function = (int a, int b) -> a > b ? a : b;
```

참조변수 function의 타입은 인터페이스로 정의해야한다. 이때 인터페이스 @FunctionalInterface 애노테이션을 붙인다. 이를 "**함수형 인터페이스**"라고 부른다. 함수형 인터페이스에는 오직 하나의 추상 메서드만 정의되어 있어야한다. 그래야 람다식과 인터페이스의 메서드가 1:1로 연결될 수 있기 때문이다. 반면에 static메서드와 default메서드의 개수에는 제약이 없다.

```java
@FunctionalInterface
public interface Function {
  public abstract int max(int a, int b);
}

public class lambda {
  Function funtionlambda = (int a, int b) -> return a > b ? a : b;
  Function functionAnomyObject = new Function() {
    public int max(int a, int b) {
      return a > b ? a : b;
    }
  }

  function.max(3, 5);
  functionAnomyObject.max(3, 5);
}
```

이때 익명객체를 람다식으로 대체가 가능한 이유는, 람다식도 실제로는 익명 객체이고, Function인터페이스를 구현한 익명 객체의 메서드 max()와 람다식의 매개변수의 타입과 개수 그리고 반환값이 일치하기 때문이다.

**람다식을 참조변수로 다룰 수 있다는 것은 메서드를 통해 람다식을 주고받을 수 있다는 것을 의미**한다.

**람다식의 타입과 형변환**

-	함수형 인터페이스로 람다식을 참조할 수 있다는 것일 뿐, 람다식의 타입이 함수형 인터페이스의 타입과 일치하는 것은 아니다. 람다식은 익명 객체이고 익명 객체는 타입이 없다. 정확히는 타입은 있지만 컴파일러가 임의로 이름을 정하기 때문에 알 수 없는 것이다. 그래서 대입 연산자의 양변의 타입을 일치시키기 위해 아래와 같이 형변환이 필요하다.

```java
Function function = (Function)(() -> {}); //양변의 타입이 다르므로 형변환 필요
```

-	람다식은 이름이 없을 뿐 분명히 객체인데도, 아래와 같이 Object타입으로 형변환 할 수 없다. 람다식은 오직 함수형 인터페이스로만 형변환이 가능하다.

**외부 변수를 참조하는 람다식**

-	람다식도 익명 객체, 즉 익명 클래스의 인스턴스이므로 람다식에서 외부에 선언된 변수에 접근하는 규칙은 앞서 익명 클래스에서 배운 것과 동일하다.
-	람다식 내에서 참조하는 지역변수는 final이 붙지 않았어도 상수로 간주된다.
-	Inner클래스와 Outer클래스의 인스턴스 변수는 상수로 간주되지 않는다.
-	**외부 지역변수와 같은 이름의 람다식 매개변수는 허용되지 않는다.**

## 스트림이란?

JDK 8에서 추가된 Stream API에 대해서 알아보자.(파일 I/O에서 사용되는 스트림과는 다르다.) 스트림은 데이터소스를 추상화하고, 데이터를 다루는데 자주 사용되는 메서들을 정의해 놓았다. 데이터소스를 추상화하였다는 것은, 데이터 소스가 무엇이든 같은 방식으로 다룰 수 있게 되었다는 것과 코드의 재사용성이 높아진다는 것을 의미한다. 먼저, 코드를 보면서 차이를 느껴보자.
```java
//Stream 사용 전
String[] strArr = { "mash-up", "backend", "codingsquid" }
List<String> strList = Arrays.asList(strArr);

Arrays.sort(strArr);
Collections.sort(strList);

for(String str: strArr) {
  System.out.println(str);
}

for(String str : strList) {
  System.out.println(str);
}

//Stream 사용 후
Stream<String> listStream = strList.stream();
Stream<String> arrayStream = Arrays.stream(strArr);

listStream.sorted().forEach(System.out::println);
arrayStream.sorted().forEach(System.out::println);
```
위의 코드는 배열, 리스트를 정렬 후 출력하는 코드이다. 사용전과 사용후의 코드 간결성과 추상화가 잘 돼있다는 것을 느낄 수 있다.(스트림의 sort함수를 호출한다고 해서 List, Array가 정렬되는 것은 아님에 주의하자. 단지, 스트림의 요소가 정렬되어 출력되는 것이다.)

### 스트림의 특징

- **스트림은 데이터 소스를 변경하지 않는다.**
  - 스트림은 데이터 소스로 부터 데이터를 읽기만할 뿐, 데이터 소스를 변경하지 않는다. 필요하다면, 정렬된 결과를 컬렉션이나 배열에 담아서 반환할 수 있다.
  ```java
  List<String> sortedList = listStream.sorted().collect(Collections.toList());
  ```
- **스트림은 일회용이다.**
  - 스트림은 Iterator처럼 일회용이다. Iterator로 컬렉션의 요소를 모두 읽고 나면 다시 사용할 수 없는 것처럼, 스트림도 한번 사용하면 닫혀서 다시 사용할 수 없다. 필요하다면 스트림을 다시 생성해야한다.
  ```java
  listStream.sorted().forEach(System.out::print);
  int numOfElement = listStream.count(); //에러. 스트림이 이미 닫힘
  ```
- **스트림은 작업을 내부 반복으로 처리한다.**
  - 스트림을 이용한 작업이 간결할 수 있는 비결중의 하나가 바로 '내부 반복'이다. 내부 반복이라는 것은 반복문을 메서드의 내부에 숨길 수 있다는 것을 의미한다.

### 스트림의 연산

스트림이 제공하는 다양한 연산을 이용해서 복잡한 작업들을 간단히 처리할 수 있다. 스트림에 정의된 메서드 중에서 데이터 소스를 다루는 작업을 수행하는 것을 연산(operation)이라고 한다.

스트림이 제공하는 연산은 **중간 연산과 최종 연산**으로 분류할 수 있다.
 - **중간 연산:** 연산결과를 스트림으로 반환하기 때문에 중간 연산을 연속해서 연결할 수 있다.
   - **핵심 메서드: map(), flatMap()**
 - **최종 연산:** 스트림의 요소를 소모하면서 연산을 수행하기 때문에 단 한번만 연산이 가능하다.
   - **핵심 메서드: reduce(), collect()**
  ```java
  stream.distinct()
        .limit(5)
        .sorted()
        .forEach(System.out::print);
  ```

### 병렬 스트림

병렬 스트림은 내부적으로 fork & join 프레임웍을 이용해서 자동적으로 연산을 병렬로 수행한다. 우리가 할일이라고는 그저 스트름에 parallel()이라는 메서드를 호출해서 병렬로 연산을 수행하도록 지시하면 된다. 병렬로 처리되지 않게 하려면 sequential()을 호출하면 된다. 모든 스트림은 기본적으로 병렬 스트림이 아니므로 sequential()을 호출할 필요가 없다. 이 메서드는 parallel()을 호출한 것을 취소할 때만 사용한다.
```java
int sum = strStream.parallel()
                   .mapToInt(s -> s.length())
                   .sum();
```

### 스트림 생성 & 핵심 메서드
