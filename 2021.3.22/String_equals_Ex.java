package StringŬ����_����;

public class String_equals_Ex {

	public static void main(String[] args) {
		String strvar1 = "�����";
		String strvar2 = "�����";

		String strvar3 = new String("�����");// �ߺ��� �ȵ�
		String strvar4 = new String("�����");// �ߺ��� �ȵ�

		System.out.println(strvar1 == strvar2); // ���: true
		System.out.println(strvar1 == strvar3); // ���: true
		System.out.println(strvar3 == strvar4); // ���: true
		System.out.println(strvar1.equals(strvar2));
		System.out.println(strvar1.equals(strvar3));
		System.out.println(strvar3.equals(strvar4));

	}

}
