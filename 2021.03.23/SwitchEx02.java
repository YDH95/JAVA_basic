package 제어문_예제;

import java.util.Scanner;

public class SwitchEx02 {
/*
 * [문제]
 * 어느 회사의 직원의 직급을 입력하면 직급에 해당하는 '급여'를 보여주는
 * 프로그램을 작성하시오.
 * 부장: 700만원입니다.
 * 과장: 500만원입니다.
 * 대리: 400만원입니다.
 * 일반사원: 300만원입니다.
 */
	public static void main(String[] args) {
		System.out.print("직원의 직급을 입력하세요>>> ");
		Scanner sc = new Scanner(System.in);

		String level = sc.nextLine(); // 메소드 중첩 String은 sc.nextLine();
		
		switch (level) {
		case "부장":
			System.out.println("부장: 700만원입니다.");
			break;
		case "과장":
			System.out.println("과장: 500만원입니다.");
			break;
		case "대리":
			System.out.println("대리: 400만원입니다.");
			break;
		case "일반사원":
			System.out.println("일반사원: 300만원입니다.");
			break;
		default:
			System.out.println("다시입력해주세요");
			

		}

	}

}
