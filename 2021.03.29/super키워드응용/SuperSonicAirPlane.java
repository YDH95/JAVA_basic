package superŰ��������;

//�ڽ� Ŭ����

public class SuperSonicAirPlane extends AirPlane {
	// ��� ����(Ŭ���� ����)
	// static final �ʵ�� ��ü���� �������� �ʰ�
	// Ŭ�������� ���Եȴ�. �׸��� �ѹ� �ʱⰪ�� ����Ǹ� ������ �� ����.
	public static final int NORMAL = 1;// ��� ������ �� ������ �빮���� final(�Һ�)����������
	public static final int SUPERSONIC = 2; // NOMAL = �Ϲݺ��� , SUPERSONIC = ���Ӻ���

	public int flyMode = NORMAL;

	@Override // ������
	public void fly() {
		if (flyMode == SUPERSONIC) {
			System.out.println("���Ӻ���");
		} else {
			super.fly(); // �θ� �޼ҵ� ȣ��
		}
	}

}
