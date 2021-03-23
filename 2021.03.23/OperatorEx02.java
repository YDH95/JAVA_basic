package 연산자의종류와우선순위_예제;

import java.util.Scanner;

	/*
	 * [문제] 
	 * 점수를 콘솔창으로부터 입력받아 학점 처리 프로그램 작성
	 * Scanner 학습 
	 */

public class OperatorEx02 {	public static void main(String[] args) {
		//입력 단계
		System.out.print("점수 입력>>> ");
		
		//Scanner 클래스 객체 생성
		//scan은 Scanner 클래스 객체를 가리키는 '객체참조변수' 이다.
		Scanner sc = new Scanner(System.in);
		int score = sc.nextInt();
	
		//처리 단계
		String grade = score >= 90 ? "A학점" : 
					   score >= 80 ? "B학점" :
					   score >= 70 ? "C학점" :
					   score >= 60 ? "D학점" : "F학점";
		//출력 단계
		if(score <= 100) {
			System.out.println("학생의 학점은:"+ grade + "입니다.");
		}else {
			System.out.println("점수는 100이하의 숫자로 입력해주세요.");
		}
			
	}

}
