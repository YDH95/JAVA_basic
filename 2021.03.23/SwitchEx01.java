package 제어문_예제;

import java.util.Scanner;

/*[문제]
 * 어느 회사에서 고객관리 등급 처리를 하고자 한다.
 * 일반고객(C), 골드고객(B), VIP고객(A)
 * 비교 판단문의 switch문을 활용하여 프로그램을 작성한시오.
 */
public class SwitchEx01 {

	public static void main(String[] args) {
		// 입력단계
		System.out.print("고객 등급을 입력하세요>>> ");
		Scanner sc = new Scanner(System.in);

		char grade = sc.next().charAt(0); // 메소드 중첩

		// 처리 단계
		switch (grade) {
		case 'A':
		case 'a':
			System.out.println("VIP 고객입니다.");
			break; // 반드시 break문으로 switch문을 벗어나야 한다! 분기시키는
		case 'B':
		case 'b':
			System.out.println("골드 고객입니다.");
			break;
		case 'C':
		case 'c':
			System.out.println("일반 고객입니다.");
			break;
		default :
			System.out.println("누구?");
		}
	}

}
