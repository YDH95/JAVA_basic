package �������������Ϳ켱����_����;

import java.util.Scanner;

	/*
	 * [����] 
	 * ������ �ܼ�â���κ��� �Է¹޾� ���� ó�� ���α׷� �ۼ�
	 * Scanner �н� 
	 */

public class OperatorEx02 {	public static void main(String[] args) {
		//�Է� �ܰ�
		System.out.print("���� �Է�>>> ");
		
		//Scanner Ŭ���� ��ü ����
		//scan�� Scanner Ŭ���� ��ü�� ����Ű�� '��ü��������' �̴�.
		Scanner sc = new Scanner(System.in);
		int score = sc.nextInt();
	
		//ó�� �ܰ�
		String grade = score >= 90 ? "A����" : 
					   score >= 80 ? "B����" :
					   score >= 70 ? "C����" :
					   score >= 60 ? "D����" : "F����";
		//��� �ܰ�
		if(score <= 100) {
			System.out.println("�л��� ������:"+ grade + "�Դϴ�.");
		}else {
			System.out.println("������ 100������ ���ڷ� �Է����ּ���.");
		}
			
	}

}
