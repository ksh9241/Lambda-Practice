package lambdaPractice;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
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
		
		// 데이터 출력 확인용 iterator
		Iterator<String> test = filterStream.iterator();
		while(test.hasNext()) {
			System.out.println(test.next());
		}
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
