package superŰ��������;

public class SuperSonicAirPlaneExample {

	public static void main(String[] args) {
		SuperSonicAirPlane sa = new SuperSonicAirPlane();
		
		sa.takeOff();
		sa.fly(); //�θ�޼ҵ� ȣ��
		sa.flyMode = SuperSonicAirPlane.SUPERSONIC;
		sa.fly(); //�θ�޼ҵ� ȣ��
		sa.flyMode = SuperSonicAirPlane.NORMAL;
		sa.fly(); //�θ�޼ҵ� ȣ��
		sa.land();
		
		
	}

}
