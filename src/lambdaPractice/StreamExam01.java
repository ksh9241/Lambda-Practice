package lambdaPractice;

import java.util.stream.IntStream;

public class StreamExam01 {
	public static void main(String[] args) {
		//Stream API를 이용한 간단한 짝수 판별
		IntStream.range(1, 11).filter(i -> i%2 == 0)
					.forEach(System.out::println);
		
		System.out.println("---------------");
		//0 ~ 1000 까지의 값 중 500 이상이며 짝수이면서 5의 배수인 수의 합을 구하라
		System.out.println(
			IntStream.range(0, 1001)
					.skip(500)
					.filter(i -> i%2 == 0)
					.filter(i -> i% 5 == 0)
					.sum()
		);
	}
}
