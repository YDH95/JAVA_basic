package super키워드응용;

public class SuperSonicAirPlaneExample {

	public static void main(String[] args) {
		SuperSonicAirPlane sa = new SuperSonicAirPlane();
		
		sa.takeOff();
		sa.fly(); //부모메소드 호출
		sa.flyMode = SuperSonicAirPlane.SUPERSONIC;
		sa.fly(); //부모메소드 호출
		sa.flyMode = SuperSonicAirPlane.NORMAL;
		sa.fly(); //부모메소드 호출
		sa.land();
		
		
	}

}
