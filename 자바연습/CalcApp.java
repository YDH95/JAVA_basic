package Calculate;

import java.util.Scanner;

public class CalcApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Add add = new Add();
		Sub sub = new Sub();
		Mul mul = new Mul();
		Div div = new Div();

		while (true) {
			String stop = sc.next();
			if (stop.equals("/q")) {
				System.out.println("종료합니다.");
				break;
			}

			int a = Integer.parseInt(stop);
			String operand = sc.next();
			int b = sc.nextInt();

//			if else문 으로 했을 때
//			if (operand.equals("+")) {
//				add.setValue(a, b);
//				System.out.println(add.calculate());
//			} else if (operand.equals("-")) {
//				sub.setValue(a, b);
//				System.out.println(sub.calculate());
//			} else if (operand.equals("*")) {
//				mul.setValue(a, b);
//				System.out.println(mul.calculate());
//			} else if (operand.equals("/")) {
//				div.setValue(a, b);
//				System.out.println(div.calculate());
//			} else {
//				System.out.println("알수없는연산입니다.");
//			}

//	switch case문으로 했을 때
			switch (operand) {
			case "+":
				add.setValue(a, b);
				System.out.println(add.calculate());
				break;
			case "-":
				sub.setValue(a, b);
				System.out.println(sub.calculate());
				break;
			case "*":
				mul.setValue(a, b);
				System.out.println(mul.calculate());
				break;
			case "/":
				div.setValue(a, b);
				System.out.println(div.calculate());
				break;
			default:
				System.out.println("알수없는연산입니다.");
				break;

			}
	
		}
		sc.close();
	}
}