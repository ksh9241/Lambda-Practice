package lambdaPractice;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamExam02 {

	//Collection Stream
	public interface Collection<E> extends Iterable<E>{ 
		// 재정의 하는 이유 : list를 iterator 객체로 반환하여  java8 stream을 사용할 경우 문제가 될 수 있다. 
		// iterator를 iterable로 변환한 다음 쉽게 stream을 쓸 수 있는 방법이다.
		default Stream<E> stream(){
			return StreamSupport.stream(spliterator(), false);
		}
	}
	
	public static void main(String[] args) {
		//Arrays Stream
		String[] arr = new String[] {"a","b","c"};
		Stream<String> stream = Arrays.stream(arr);
		Stream<String> streamOfArrayPart = Arrays.stream(arr,1,3);
		
		//Collection Stream
		List<String> list = Arrays.asList("a", "b", "c");
		Stream<String> listStream = list.stream();
		Stream<String> parallelStream = list.parallelStream(); // 병렬 처리 스트림
		
		//Stream.builder()
		Stream<String> builderStream = Stream.<String>builder()
										.add("Eric").add("Elena").add("JAVA")
										.build(); //[Eric, Elena, JAVA]

		//Stream.generate()
		//generate 를 사용할 경우 스트림의 크기가 정해져있지않고 무한하기 때문에 특정 사이즈로 최대 크기를 제한해야 한다.
		Stream<String> generatedStream = Stream.generate(() -> "gen").limit(5);
		
		//Stream.iterate()
		Stream<Integer> iteratedStream = Stream.iterate(30, n -> n + 2).limit(5);
		//[30, 32, 34, 36, 38]
		
		// 기본 타입형 스트림
		IntStream intStream = IntStream.range(1, 5); // [1, 2, 3, 4]
		LongStream longStream = LongStream.rangeClosed(1, 5); // [1, 2, 3, 4, 5]
		
		// 제네릭을 사용할 경우
		// <>제네릭을 사용할 경우는 반드시 boxed()함수를 사용하여야 한다.
		// boxed() : int, long, double 요소를 Integer, Long, Double 요소로 박싱해서 Stream을 생성한다.
		// Stream은 객체 요소를 처리하는 스트림이기 때문에 Integer, Long, Double을 다룰 수 있다.
		Stream<Integer> boxedIntStream = IntStream.range(1, 5).boxed();
		
		// 문자열 스트링 (문자열을 Integer Type으로 형변환)
		IntStream charsStream = "Stream".chars(); //[83, 116, 114, 101, 97, 109]
		
		// 파일 스트림
		try {
			Stream<String> lineStream = Files.lines(Paths.get("file.txt"),
											Charset.forName("UTF-8"));
		} catch (IOException e) {
			System.out.println("테스트파일이라 에러남");
		}
		
		// 스트림 연결하기(합치기)
		Stream<String> stream1 = Stream.of("JAVA", "PHP", "JavaScript");
		Stream<String> stream2 = Stream.of("자바", "피에이치피", "자바스크립트");
		Stream<String> concat = Stream.concat(stream1, stream2);
		
		// Filter Practice
		List<String> names = Arrays.asList("Eric", "Elena", "Java");
		Stream<String> filterStream = names.stream().filter(name -> name.contains("a")); // [Elena, Java]
		
		// Mapping
		Stream<String> mappingStream = names.stream().map(String::toUpperCase); // [ERIC, ELENA, JAVA]
		
		//flatMap
		List<List<String>> flatMapList = Arrays.asList(Arrays.asList("a"),
												Arrays.asList("b"));
		
		//util.Arrays.ArrayList의 사용 예제
		flatMapList.get(0).set(0, "c"); //O
		//flatMapList.get(0).add("a"); // X
		
		String [] arrr = {"a", "b"};
		List<String> flatMapList2 = new ArrayList<>(Arrays.asList(arrr));
		flatMapList2.add("c"); // O
		flatMapList2.set(0, "d"); // O

		//Sorted
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
		
		//Iterating
		int sum = IntStream.of(1,3,5,7,9)
							.peek(System.out :: println)
							.sum();
		
		long count = IntStream.of(1,3,5,7,9).count();
		long sum2 = IntStream.of(1,3,5,7,9).sum();
		OptionalInt min = IntStream.of(1,3,5,7,9).min();
		OptionalInt max = IntStream.of(1,3,5,7,9).max();
		//Optional : NullPointException을 방지하기 위한 클래스이다.
		
//		DoubleStream.of(1.1, 2.2, 3.3, 4.4, 5.5) //데이터가 있을 땐 요구조건을 실행한다.
		DoubleStream.of()						// 데이터가 없을 땐 실행하지 않는다.
					.average()
					.ifPresent(System.out::println); // ifPresent 메서드는 null을 체크하는 함수이다. 
		
		//Optional
		Optional<String> optional = Optional.empty();
		System.out.println(optional); // Optional.empty
		System.out.println(optional.isPresent()); // false
		
		Optional<String> optional2 = Optional.ofNullable(getString());
		String result = optional2.orElse("other"); // optional2 에 값이 없다면 other를 리턴
		
		List<String> listOpt = Optional.ofNullable(getList()).orElseGet(() -> new ArrayList<>());
		
		//reduce
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
		
		//Matching
		List<String> names2 = Arrays.asList("Eric", "Elena", "Java");
		
		boolean anyMatch = names2.stream()
								.anyMatch(name -> name.contains("a")); // 객체 중 매개변수 값이 하나롣 있는지? = true
		
		boolean allMatch = names2.stream()
								.allMatch(name -> name.length() > 3); // 모든 배열의 객체들의 length가 3보다 큰가? = true
		
		boolean noneMatch = names2.stream()
								.noneMatch(name -> name.endsWith("s")); // 모든 배열의 객체들의 끝에 s가 있는가?? = No 그러므로 true
		
		names2.stream().forEach(System.out::println);
		
		// 데이터 출력 확인용 iterator
		Iterator<String> test = filterStream.iterator();
		while(test.hasNext()) {
			System.out.println(test.next());
		}
	}
	
	private static List<String> getList() {
		return null;
	}
	private static String getString() {
		return null;
	}

	// Empty Stream
	public Stream<String> streamOf(List<String> list){
		return list == null || list.isEmpty() ? Stream.empty() : list.stream();
	}
	
	//Stream.generate()
	public static<T> Stream<T> generate(Supplier<T> s){
		return null;
	}
}
