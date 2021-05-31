package lambdaPractice;

public class lambdaExam01 {
	public static void main(String[] args) {
		//기존 방식
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Welcome SeHwan Practice");
			}
		}).start();
		
		//람다식
		new Thread(()->{
			System.out.println("Welcome SeHwan Practice");
		}).start();
	}
}
