package ���_����;

import java.util.Scanner;

/*[����]
 * ��� ȸ�翡�� ������ ��� ó���� �ϰ��� �Ѵ�.
 * �Ϲݰ�(C), ����(B), VIP��(A)
 * �� �Ǵܹ��� switch���� Ȱ���Ͽ� ���α׷��� �ۼ��ѽÿ�.
 */
public class SwitchEx01 {

	public static void main(String[] args) {
		// �Է´ܰ�
		System.out.print("�� ����� �Է��ϼ���>>> ");
		Scanner sc = new Scanner(System.in);

		char grade = sc.next().charAt(0); // �޼ҵ� ��ø

		// ó�� �ܰ�
		switch (grade) {
		case 'A':
		case 'a':
			System.out.println("VIP ���Դϴ�.");
			break; // �ݵ�� break������ switch���� ����� �Ѵ�! �б��Ű��
		case 'B':
		case 'b':
			System.out.println("��� ���Դϴ�.");
			break;
		case 'C':
		case 'c':
			System.out.println("�Ϲ� ���Դϴ�.");
			break;
		default :
			System.out.println("����?");
		}
	}

}
