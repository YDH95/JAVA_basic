package �����ڰ�_����_Ŭ��������;

/*
 * Ŭ����?
 *    => ��ü�� �����Ҽ��ִ� ���赵
 *       Ŭ���� ���ο��� ��ü�� �Ӽ��� ���(����, ����), ������ ����
 * 
 */
public class Car { // �ڵ��� Ŭ���� ����
	// ��ü�� �������� �Ӽ� => ��������� ����
	String carName = null; // �ڵ��� �̸�
	// String color; //���ǻ�
	int speed = 0; // ����ӵ�
	int wheelNum = 0; // ������ ����
	// int price; //����

	
	
	// ������ ����
	public Car() {
		
	}
	public Car(String name) { // carName������
		carName = name;
	}

	public Car(int velocity) { // velocity=�ӵ� speed������
		speed = velocity;
	}

	public Car(String name, int velocity) {
		carName = name;
		speed = velocity;
	}

	// �޼ҵ� ���� => ��ü�� ���, ����
	// �޼ҵ� �����ε� => �ٿ뼺�� ������ �� �ִ� ����
	// ���������� ��ȯŸ�� �޼ҵ��̸�( ){
	//     ���
	//}
	public void speedUp() { // void�� return���� ���°��
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
		// ��ü����
		Car D = new Car();
		System.out.println(D.carName);
		Car A = new Car("���׽ý�"); 
		System.out.println("myCarName: " + A.carName);

		Car B = new Car(120);
		System.out.println("yourCarSpeed: " + B.speed + "km/h");

		
		Car C = new Car("����", 150);
		System.out.println("hisCarName " + C.carName + " hisCarSpeed " + C.speed + "km/h");
		
		
		
		
	}

}
