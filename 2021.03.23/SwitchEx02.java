package ���_����;

import java.util.Scanner;

public class SwitchEx02 {
/*
 * [����]
 * ��� ȸ���� ������ ������ �Է��ϸ� ���޿� �ش��ϴ� '�޿�'�� �����ִ�
 * ���α׷��� �ۼ��Ͻÿ�.
 * ����: 700�����Դϴ�.
 * ����: 500�����Դϴ�.
 * �븮: 400�����Դϴ�.
 * �Ϲݻ��: 300�����Դϴ�.
 */
	public static void main(String[] args) {
		System.out.print("������ ������ �Է��ϼ���>>> ");
		Scanner sc = new Scanner(System.in);

		String level = sc.nextLine(); // �޼ҵ� ��ø String�� sc.nextLine();
		
		switch (level) {
		case "����":
			System.out.println("����: 700�����Դϴ�.");
			break;
		case "����":
			System.out.println("����: 500�����Դϴ�.");
			break;
		case "�븮":
			System.out.println("�븮: 400�����Դϴ�.");
			break;
		case "�Ϲݻ��":
			System.out.println("�Ϲݻ��: 300�����Դϴ�.");
			break;
		default:
			System.out.println("�ٽ��Է����ּ���");
			

		}

	}

}
