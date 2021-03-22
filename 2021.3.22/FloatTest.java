package 기초_예제;

public class FloatTest {

	public static void main(String[] args) {
		double var1 = 3.14;//리터럴
		
		/*
		*자바에서 실수 리터럴의 기본타입을 =>double로 간주한다.
		*실수 리터럴을 float타입 변수에 그냥저장할 수 없다
		*실수 리터럴을 float타입 변수에 저장하려면 리터럴 뒤에 소문자 'f'나 
		*대문자'F'를 붙여야 한다.
		*/
		
		//*float var2 = 3.14; //컴파일에러
		float var2 = 3.14F; 
		System.out.println(var2);
		System.out.println(var1);
		

	}

}
