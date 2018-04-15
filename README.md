JAVA 8 STUDY
============

Content
-------

-	Lamda
-	Stream
-	Optional<T>

디렉토리 구성
-------------

> basePath: src/main/java/com/codingsquid

| dir      | info      |
|:---------|:----------|
| lamda    | 람다식    |
| optional | 래퍼 클래 |
| stream   | 스트림    |

람다식이란?
-----------

람다식(Lamda expression)은 간단히 말해서 메서드를 하나의 '식(expression)'으로 표현한 것이다. 메서드를 람다식으로 표현하면 메서드의 이름과 반환값이 없어지므로, 람다식을 '익명 함수(anonymous function)'이라고도 한다.

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

다음은 람다의 사용법이다.**일반 메서드**

```java
  반환타입 메서드이름(매개변수 선언) {
    문장들
  }
```

**람다식**

```java
  (매개변수 선언) -> {

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

Stream이란?
-----------
