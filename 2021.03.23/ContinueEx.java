package 제어문_예제;

public class ContinueEx {

	public static void main(String[] args) {
		int total = 0;
		int num;

		for (num = 1; num <= 100; num++) {
			if (num % 2 == 0)
				continue;
			total += num;

		}

		System.out.println("1부터 100까지의 홀수의 합:" + total + "입니다.");
		
		int[ ] a = {30,50,10,40,20}; //index[]
		a[0] = 10;
		System.out.println(a[0]);
		
		
		
	}

}
