package super키워드응용;

//자식 클래스

public class SuperSonicAirPlane extends AirPlane {
	// 상수 선언(클래스 변수)
	// static final 필드는 객체마다 생성되지 않고
	// 클래스에만 포함된다. 그리고 한번 초기값이 저장되면 변경할 수 없다.
	public static final int NORMAL = 1;// 상수 선언할 때 변수는 대문자임 final(불변)변하지않음
	public static final int SUPERSONIC = 2; // NOMAL = 일반비행 , SUPERSONIC = 음속비행

	public int flyMode = NORMAL;

	@Override // 재정의
	public void fly() {
		if (flyMode == SUPERSONIC) {
			System.out.println("음속비행");
		} else {
			super.fly(); // 부모 메소드 호출
		}
	}

}
