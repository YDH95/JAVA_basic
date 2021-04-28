package 개발자가_직접_클래스생성;

/*
 * 클래란?
 *    => 객체를 생성할수있는 설계도
 *       클래스 내부에는 객체의 속성과 기능(동작, 행위), 생성자 설정
 * 
 */
public class Car { // 자동차 클래스 설계
	// 객체가 가져야할 속성 => 멤버변수로 선언
	String carName = null; // 자동차 이름
	// String color; //차의색
	int speed = 0; // 현재속도
	int wheelNum = 0; // 바퀴의 갯수
	// int price; //가격

	
	
	// 생성자 구현
	public Car() {
		
	}
	public Car(String name) { // carName생성자
		carName = name;
	}

	public Car(int velocity) { // velocity=속도 speed생성자
		speed = velocity;
	}

	public Car(String name, int velocity) {
		carName = name;
		speed = velocity;
	}

	// 메소드 구현 => 객체의 기능, 동작
	// 메소드 오버로딩 => 다용성을 구현할 수 있는 개념
	// 접근제어자 반환타입 메소드이름( ){
	//     기능
	//}
	public void speedUp() { // void는 return값이 없는경우
		speed += 1;
	}

	public void speedUp(int velocity) {
		speed = speed + velocity;
	}

	public void speedDown() {
		speed -= 1;
		if (speed < 0) {
			speed = 0;
		}
	}

	public void stop() {
		speed = 0;
	}

	public static void main(String[] args) {
		// 객체생성
		Car D = new Car();
		System.out.println(D.carName);
		Car A = new Car("제네시스"); 
		System.out.println("myCarName: " + A.carName);

		Car B = new Car(120);
		System.out.println("yourCarSpeed: " + B.speed + "km/h");

		
		Car C = new Car("에쿠스", 150);
		System.out.println("hisCarName " + C.carName + " hisCarSpeed " + C.speed + "km/h");
		
		
		
		
	}

}
