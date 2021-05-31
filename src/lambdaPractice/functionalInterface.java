package lambdaPractice;

public class functionalInterface{
	public static void main(String [] args) {
		
		//FunctionalInterface lambda식으로 재정의하기
		functionalInterfacePractice plusLambda = (first,second) -> first + second;
		System.out.println("plus : "+plusLambda.Calc(4, 2));
		
		functionalInterfacePractice minusLambda = (first, second) -> first - second;
		System.out.println("minus : "+minusLambda.Calc(4, 2));
		
		// 자바형식으로 인터페이스 재정의하기
		functionalInterfacePractice func = new functionalInterfacePractice() {
			@Override
			public int Calc(int first, int second) {
				return first * second;
			}
		};
		System.out.println("곱하기 : "+func.Calc(4, 2));
	}
}
