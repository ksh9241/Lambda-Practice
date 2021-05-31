package lambdaPractice;

import java.util.function.BinaryOperator;
import java.util.function.IntFunction;

public class java_util_function_Example {
	public static void main(String[] args) {
		//IntFunction<R>
		IntFunction intSum = (x) -> x+1;
				System.out.println(intSum.apply(1));
		
		//BinaryOperator<T>
		BinaryOperator stringSum=(x, y)-> x+" "+y;
		System.out.println(stringSum.apply("Welcome","SeHwan"));
	}
}
