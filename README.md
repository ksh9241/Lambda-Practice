# 람다식 정의

### 람다함수란?
- 람다 함수는 프로그래밍 언어에서 사용되는 개념으로 익명함수(Annonymous function)를 지칭하는 용어이다.
쉽게 말해 함수를 보다 단순하게 표현하는 방법이다.

### 람다의 특정
- 람다 대수는 이름을 가질 필요가 없다. 두개 이상의 입력이 있는 함수는 최종적으로 1개의 입력만 받는 람다 대수로 단순화 될 수 있다.

### 익명함수란?
익명함수란 말 그대로 함수의 이름이 없는 함수이다. 익명함수들은 공통으로 일급객체(first Class citizen)라는 특징을 가지고 있다.
이 일급객체란 일반적으로 다른 객체들에 적용 가능한 연산을 모두 지원하는 개체를 가르킨다. 함수를 값으로 사용 할 수도 있으며 파라메터로 전달 및 변수에 대입 하기와 같은 연산들이 가능하다.

### 람다의 장단점
- 장점
1. 코드의 간결성 - 람다를 사용하면 불필요한 반복문의 삭제가 가능하며 복잡한 식을 단순하게 표현할 수 있다.
2. 지연연산 수행 - 람다는 지연연산을 수행 함으로써 불필요한 연산을 최소화 할 수 있다.
3. 병렬처리 가능 - 멀티쓰레드를 활용하여 병렬처리를 사용할 수 있다.

- 단점
1. 람다식의 호출이 까다롭다.
2. 람다 stream 사용 시 단순 for문 혹은 while문 사용 시 성능이 떨어진다.
3. 불필요하게 너무 사용하게 되면 오히려 가독성을 떨어트릴 수 있다.

### 람다의 표현식
1. 람다는 매개변수 화살표(-> 함수 몸체로 이용하여 사용할 수 있다.
2. 함수몸체가 단일 실행문이면 괄호{}를 생략할 수 있다.
3. 함수몸체가 return문으로만 구성되어 있는 경우 괄호{}를 생략할 수 없다.

```JAVA
//정상적인 유형
() -> {}
() -> 1
() -> { return 1; }

(int x) -> x+1
(x) -> x+1
x -> x+1
(int x) -> { return x+1; }

(int x, int y) -> x+y
(x, y) -> x+y
(x, y) -> { return x+y; }

(String lam) -> lam.length()
lam -> lam.length()
(Thread lamT) -> { lamT.start(); }
lamT -> { lamT.start(); }

//잘못된 유형 선언된 type과 선언되지 않은 type을 같이 사용할 수 없다.
(x, int y) -> x+y
(x, final y) -> x+y
```

### 함수형 인터페이스
	@Functionalinterface
	functional Interface는 일반적으로 '구현해야 할 추상 메소드가 하나만 정의된 인터페이스'를 가리킨다.

### Java에서 지원하는 java.util.function 인터페이스
	java.util.function interface는 예제로 2가지만 설명하고 있습니다.

1. IntFunction<R> : int 값의 인수를 받아들이고 결과를 생성하는 함수를 나타낸다.
```JAVA
intFunction intSum = (x) -> x+1;
System.out.println(intSum.apply(1));
```

2. BinaryOperator<T> : 동일한 유형의 두 피연산자에 대한 연산을 나타내며 피연산자와 동일한 유형의 결과를 생성합니다.
```JAVA
BinaryOperator StringSum=(x, y) -> x+" "+y;
System.out.println(stringSum.apply("Welcome","SeHwan"));
```

# Stream API

### Stream이란?
- Stream이란 다양한 데이터를 표준화된 방법으로 다루기 위한 라이브러리이다. 자바 8부터 추가된 Stream API는 다음과 같이 구성된다.
```JAVA
example.strea().filter(x -> x < 2).count
// stream() : 스트림 생성
// filter : 중간 연산(스트림 변환) - 연속에서 수행 가능합니다.
// count : 최종 연산(스트림 사용) - 마지막에 단 한번만 사용 가능합니다.
```

### Stream의 특징
1. Stream은 데이터를 변경하지 않습니다.
2. Stream은 1회용 입니다.
3. Stream은 지연 연산을 수행합니다.
4. Stream은 병렬 실행이 가능합니다.
					
### Stream에 대한 내용 3가지
1. 생성하기 : 스트림 인스턴스 생성.
2. 가공하기 : 필터링(filtering) 및 맵핑(mapping) 등 원하는 결과를 만들어가는 중간 작업 (intermediate operations)
3. 결과 만들기 : 최종적으로 결과를 만들어내는 작업(terminal operations).
					
전체 -> 맵핑 -> 필터링1 -> 필터링2 -> 결과만들기 -> 결과물
	
### 배열 스트림
  스트림은 배열 또는 컬렉션 인스턴스를 이용해서 생성할 수 있습니다. 배열은 다음과 같이 Arrays.stream 메소드를 사용합니다.
```JAVA
  String[] arr = new String[] {"a","b","c"};
  Stream<String> stream = Arrays.stream(arr);
  Stream<String> streamOfArrayPart = Arrays.stream(arr,1,3);
```
	
### 컬렉션 스트림
   컬렉션 타입(Collection, List, Set)의 경우 인터페이스에 추가된 디폴트 메소드 Stream을 이용해서 스트림을 만들 수 있습니다.
```JAVA
// iterator를 iterable로 변경하는 이유는 라이브러리상 문제는 없지만 iterator로 stream을 하게 될 경우 문제가 발생하기 때문에 iterable로 변환하여 사용한다.
public interface Collection<E> extends Iterable<E> {
  default Stream<E> stream() {
    return StreamSupport.stream(spliterator(), false);
  } 

List<String> list = Arrays.asList("a", "b", "c");
Stream<String> listStream = list.stream();
Stream<String> parallelStream = list.parallelStream(); // 병렬 처리 스트림
}
```

### 비어있는 스트림
비어있는 스트림도 생성할 수 있습니다. 빈 스트림은 요소가 없을 때 null 대신 사용할 수 있습니다.
```JAVA
// Empty Stream
public Stream<String> streamOf(List<String> list){
	return list == null || list.isEmpty() ? Stream.empty() : list.stream();
}
```

### Stream.builder()
빌더를 사용하면 스트림에 직접적으로 원하는 값을 넣을 수 있습니다.
```JAVA
//Stream.builder()
Stream<String> builderStream = Stream.<String>builder()
			.add("Eric").add("Elena").add("JAVA")
			.build(); //[Eric, Elena, JAVA]
```
	
### Stream.generate()
generate 메소드를 이용하면 Supplier<T> 에 해당하는 람다로 값을 넣을 수 있습니다.
Supplier<T> 는 인자는 없고 리턴값만 있는 함수영 인터페이스이다. 람다에서 리턴하는 값이 들어간다.
```JAVA
public static<T> Stream<T> generate(Supplier<T> s){
	return null;
}

//generate 를 사용할 경우 스트림의 크기가 정해져있지않고 무한하기 때문에 특정 사이즈로 최대 크기를 제한해야 한다.
Stream<String> generatedStream = Stream.generate(() -> "gen").limit(5);
```
	
### Stream.iterate()
iterate 메소드를 이용하면 초기값과 해당 값을 다루는 람다를 이용해서 스트림에 들어갈 요소를 만든다. 예제에서는 30이 초기값이고 값이 2씩 증가하는 값들이 들어간다. 즉 현재 요소가 다음 요소의 인풋으로 들어간다. 이 방법 역시 스트림 사이즈가 무한하기 때문에 특정 사이즈로 제한해아 한다.
```JAVA
//Stream.iterate()
Stream<Integer> iteratedStream = Stream.iterate(30, n -> n + 2).limit(5);
//[30, 32, 34, 36, 38]
```
	
### 기본 타입형 스트림
제네릭을 사용하면 리스트나 배열을 이용해서 기본타입(int, long, double) 스트림을 생성할 수 있습니다. 하지만 제네릭을 사용하지 않고 직접적으로 해당 타입의 스트림을 다룰 수도 있습니다. range 와 rangeClosed 는 범위의 차이입니다. 두번째 인자(매개변수)에 종료지점이 포함되느냐 안되느냐의 차이입니다.
```JAVA
// 기본 타입형 스트림
IntStream intStream = IntStream.range(1, 5); // [1, 2, 3, 4, 5]
LongStream longStream = LongStream.rangeClosed(1, 5); // [1, 2, 3, 4, 5]

// 제네릭을 사용할 경우
// <>제네릭을 사용할 경우는 반드시 boxed()함수를 사용하여야 한다.
// boxed() : int, long, double 요소를 Integer, Long, Double 요소로 박싱해서 Stream을 생성한다.
// Stream은 객체 요소를 처리하는 스트림이기 때문에 Integer, Long, Double을 다룰 수 있다.
Stream<Integer> boxedIntStream = IntStream.range(1, 5).boxed();
```

### 문자열 스트링
스트링을 이용해서 스트림을 생성할 수도 있습니다.
```JAVA
// 문자열 스트링 (문자열을 Integer Type으로 형변환)
IntStream charsStream = "Stream".chars(); //[83, 116, 114, 101, 97, 109]
```

### 파일 스트림
자바 NIO의 files 클래스의 lines 메소드는 해당 파일의 각 라인을 스트링 타입의 스트림으로 만들어 준다.
	
```JAVA
// 파일 스트림
try {
Stream<String> lineStream = Files.lines(Paths.get("file.txt"),
				Charset.forName("UTF-8"));
} catch (IOException e) {
	e.printStackTrace();
}
```
	
### 스트림 연결하기
Stream.concat 메소드를 이용해 두 개의 스트림을 연결해서 새로운 스트림을 만들어낼 수 있다.
```JAVA
Stream<String> stream1 = Stream.of("JAVA", "PHP", "JavaScript");
Stream<String> stream2 = Stream.of("자바", "피에이치피", "자바스크립트");
Stream<String> concat = Stream.concat(stream1, stream2);
```
	
### Filtering
필터(filter)는 스트림 내 요소들을 하나씩 평가해서 걸러내는 작업이다. 인자로 받는 Predicate 는 boolean 을 리턴하는 함수형 인터페이스로 평가식이 들어가게 된다.
```JAVA
// Filter Practice
List<String> names = Arrays.asList("Eric", "Elena", "Java");
// asList() : Arrays의 private 정적 클래스인 ArrayList를 리턴한다. 단, util.ArrayList 클래스와 다르게 util.Arrays.ArrayList 클래스는 set(), get(), contains() 메서드만 가지고 있기 때문에 add()할 수 없다.(원소를 추가할 수 없다. 객체를 생성 할 당시의 length만 가능) 또한 생성 시 레퍼런스를 통한 새로운 객체를 만드는 것이 아닌 레퍼런스 배열의 주소값을 가져오게 된다. 레퍼런스 객체를 수정하게 되면 asList객체 역시 수정 된다.

Stream<String> filterStream = names.stream().filter(name -> name.contains("a")); // [Elena, Java]
```
	
### Mapping
맵은 스트림 내 요소들을 하나씩 특정 값으로 변환해준다. 이 때 값을 변환하기 위한 람다를 인자로 받는다.
```JAVA
// Mapping
List<String> names = Arrays.asList("Eric", "Elena", "Java");
Stream<String> mappingStream = names.stream().map(String::toUpperCase); // [ERIC, ELENA, JAVA]
```
	
### flatMap
인자로 mapper를 받고 있는데, 리턴 타입이 Stream 이다. 즉, 새로운 스트림을 생성해서 리턴하는 람다를 넘겨야 한다. flatMap 은 중첩 구조를 한 단계 제거하고 단일 컬렉션으로 만들어주는 역할을 한다. 이러한 작업을 플래트닝(flattening)이라고 한다.
```JAVA
List<List<String>> flatMapList = Arrays.asList(Arrays.asList("a"),
						Arrays.asList("b"));
		
//util.Arrays.ArrayList의 사용 예제
flatMapList.get(0).set(0, "c"); //O
flatMapList.get(0).add("a"); // X

//util.Arrays.ArrayList 를 사용하면서 add도 할 수 있다. (레퍼런스로 asList메서드를 사용했을 경우 데이터를 ArrayList로 반환만 해줄 뿐 같은 주소값을 가지고 있지 않다.)
List<String> flatMapList2 = new ArrayList<>(Arrays.asList("a"));
flatMapList2.add("b"); // O
flatMapList2.set(0, "c"); // O
```
	
### Sorting
```JAVA
System.out.println(
IntStream.of(14, 11, 20, 39, 23)
	.sorted()
	.boxed()
	.collect(Collectors.toList())
); // [11, 14, 20, 23, 39] 인자 없이 호출할 경우 asending

List<String> lang = new ArrayList<>(Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift"));

System.out.println(
lang.stream()
	.sorted()
	.collect(Collectors.toList())
); // 오름차순

System.out.println(
lang.stream()
	.sorted(Comparator.reverseOrder())
	.collect(Collectors.toList())
); // Comparator 인터페이스의 reverseOrder를 사용하여 내림차순으로 정렬하였다.
	
System.out.println(
	lang.stream()
		.sorted(Comparator.comparingInt(String::length))
		.collect(Collectors.toList())
); // length로 정렬 length가 동일한 경우 1번 index의 오름차순

System.out.println(
	lang.stream()
		.sorted((s1, s2) -> s2.length() - s1.length())
		.collect(Collectors.toList())
); // length로 내림차순 s1 : 앞의 인덱스, s2 : 뒤의 인덱스
```

### Iterating
스트림 내 요소들 각각의 대상으로 특정 연산을 수행하는 메소드로는 peek이 있다. peek은 그냥 확인해본다는 단어 뜻 처럼 특정 결과를 반환하지 않는 함수형 인터페이스 Consumer를 인자로 받는다.
```JAVA
int sum = IntStream.of(1,3,5,7,9)
		.peek(System.out :: println)
		.sum();
```
	
### Calculating
스트림 API는 다양한 종료 작업을 제공합니다. 최소, 최대, 합, 평균 등 기본형 타입으로 결과를 만들어 낼 수 있다.
	
```JAVA
long count = IntStream.of(1,3,5,7,9).count();
long sum2 = IntStream.of(1,3,5,7,9).sum();
OptionalInt min = IntStream.of(1,3,5,7,9).min();
OptionalInt max = IntStream.of(1,3,5,7,9).max();
//Optional : NullPointException을 방지하기 위한 클래스이다.

DoubleStream.of(1.1, 2.2, 3.3, 4.4, 5.5) //데이터가 있을 땐 요구조건을 실행한다.
DoubleStream.of()			// 데이터가 없을 땐 실행하지 않는다.
		.average()
		.ifPresent(System.out::println); // ifPresent 메서드는 null을 체크하는 함수이다. 

```
	
### Optional
개발할 때 개발자를 괴롭히는 예외 중 하나는 NullPointException이다. Optional은 빈 값일 때 null 대신 초기값을 사용하길 권장한다.

```JAVA
List<String> names = getNames();
names.sort(); // names 가 null 이라면 NullPointerException 이 일어난다.
	
Optional<String> optional = Optional.empty();
System.out.println(optional); // Optional.empty
System.out.println(optional.isPresent()); // false

Optional<String> optional2 = Optional.ofNullable(getString());
String result = optional2.orElse("other"); // optional2 에 값이 없다면 other를 리턴

List<String> listOpt = Optional.ofNullable(getList()).orElseGet(() -> new ArrayList<>());
	
private static List<String> getList() {
	return null;
}
private static String getString() {
	return null;
}
```
	
### Reduction
스트림은 reduce라는 메소드를 이용해서 결과를 만들어낸다.
- accumulator : 각 요소를 처리하는 계산 로직
- identity : 계산을 위한 초기값으로 스트림이 비어서 계산할 내용이 없더라도 이 값은 리턴.
- combiner : 병렬 스트림에서 나눠 계산한 결과를 하나로 합치는 로직
	
```JAVA
OptionalInt reduced = IntStream.range(1, 4) // [1, 2, 3]
					.reduce((a, b) -> {
						return Integer.sum(a, b);
					}); // 6

int reducedTwoParams = IntStream.range(1, 4)
					.reduce(10, Integer::sum); // 16

Integer reducedParams = Stream.of(1, 2, 3)
				.reduce(10, // identity (계산을 위한 초기값. 값이없어도 리턴함)
						Integer::sum, // accumuator (계산로직)
						(a,b) -> {
					System.out.println("combiner was called");
					return a + b;
				}); // 16
Integer reduceParallel = Arrays.asList(1, 2, 3)
				.parallelStream() //병렬 처리를 하기 때문에 각 Arrays의 값 + 초기값 10은 각자 계산을 진행한다.
				.reduce(10, 
						Integer::sum,
						(a, b) -> {
							System.out.println("combiner was called");
							return a + b;
						}); // 36
// Integer::sum 이 총 3번 동작한다. 초기값 10에 각 스트림 값을 더한 세 개의 값(11, 12, 13)을 계산한다.
```

### Collection
collect 메서드는 또 다른 종료 작업이다. Collector 타입의 인자를 받아서 처리한다.

	
### Matching
매칭은 조건식 람다 Predicate 를 받아서 해당 조건을 만족하는 요소가 있는지 체크한 결과를 리턴한다.
- 하나라도 조건을 만족하는 요소가 있는지 (anyMatch)
- 모두 조건을 만족하는지 (allMatch)
- 모두 조건을 만족하지 않는지 (noneMatch)
	
```JAVA
List<String> names2 = Arrays.asList("Eric", "Elena", "Java");

boolean anyMatch = names2.stream()
			.anyMatch(name -> name.contains("a")); // 객체 중 매개변수 값이 하나롣 있는지? = true

boolean allMatch = names2.stream()
			.allMatch(name -> name.length() > 3); // 모든 배열의 객체들의 length가 3보다 큰가? = true

boolean noneMatch = names2.stream()
			.noneMatch(name -> name.endsWith("s")); // 모든 배열의 객체들의 끝에 s가 있는가?? = No 그러므로 true
```
