package �ֹι�ȣ_üũ����;

import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class JuminCheckEx {
	/*
	 * ������ �ֹι�ȣ�� �Է¹޾� 1�ܰ� üũ ������ �����Ͽ� sum ���� ����ϴ� ���α׷��� �ۼ��Ͻÿ�. [��Ʈ] String Ŭ������
	 * charAt()�� Ȱ��
	 * 
	 */
	/**
	 * 
	 * �ֹι�ȣ üũ �����ڵ�(���ڸ�) 
	 * 1 2 3 4 5 6 - 1 2 3 4 5 6(7) 
	 * x 2 3 4 5 6 7 8 9 2 3 4 5
	 * _______________________________ 
	 * 1�ܰ�:2 6 12 .......... 
	 * 2�ܰ�: ���ڸ������� ���� ���ڵ��� ���
	 * �ջ� : 2+6+12 ........... = sum 
	 * 3�ܰ�: ���Ѱ���� 11�� ������ �������� ���Ѵ� sum % 11 = ������ 
	 * 4�ܰ�: 11���� �������� ����� ���ش� 11 - ������ = ������� �����ڵ�� ������ ��
	 * 
	 */
	public static void main(String[] args) {
		// �غ�ܰ�
		String juminNum;
		int sum = 0; // ���躯���� �ݵ�� 0���� �ʱ�ȭ
		int[] weight = { 2, 3, 4, 5, 6, 7, 0, 8, 9, 2, 3, 4, 5};
		String[][] localeCode = {{"����","00","08"},{"�λ�","09","12"},
								 {"��õ","13","15"},{"���","16","25"},
								 {"����","26","34"},{"���","35","39"},
								 {"����","40","41"},{"�泲","42","47"},
								 {"�泲","42","47"},{"����","44","44"},
								 {"����","96","96"},{"����","48","45"},
								 {"����","55","64"},{"����","65","66"},
								 {"�뱸","67","70"},{"���","71","80"},
								 {"�泲","81","84"},{"�泲","86","90"},
								 {"���","85","85"},{"����","91","95"}};
															
		String[] zod = {"������","��","��","����",
						"��","��","��","�䳢",
						"��","��","��","��"};
		
		// �Է´ܰ�
		System.out.print("�ֹι�ȣ�� �Է��ϼ���>>> ");
		Scanner sc = new Scanner(System.in);
		juminNum = sc.next();
		
		// ó���ܰ� => logic => ����� Ȱ��
		for (int i = 0; i < 13; i++) {
			if (juminNum.charAt(i) == '-') {
						//continue;
			} else {
				sum += (juminNum.charAt(i) - '0') * weight[i];
			}
		}
		if (11 - (sum % 11) == juminNum.charAt(13) - '0') {
					System.out.println("Ȯ�εǾ����ϴ�");
		} else {
			System.out.println("�ֹι�ȣ�� �ٽ� Ȯ�����ּ���");
		}
		/**
		 * �Էµ� �ֹι�ȣ ��ȿ�� �˻� => ���� ǥ���� ���� ����
		 */
		String regex = "^[0-9]{6}-[1234][0-9]{6}$"; 
		//2���� ���
		boolean check = juminNum.matches(regex);
		//boolean check = Pattern.matches(regex, juminNum); 
		if(check == false) {
			System.out.println("�Էµ� �ֹι�ȣ�� ����ǥ���Ŀ� ���� ����.");
		}else {
			System.out.println("�Էµ� �ֹι�ȣ�� ����ǥ���Ŀ� ������.");
		}   
		Calendar cal = Calendar.getInstance(Locale.KOREA); //�ѱ� �ð� ��������
		int year = cal.get(Calendar.YEAR); //�ѱ� ������ �����ͼ� �������ֱ�
		String myyear = juminNum.substring(0, 2); //substring���� �Է��� �ֹι�ȣ ������ ������
		int myold = Integer.parseInt(myyear); //���� ���ڿ��� �����κ�ȯ
	  //String myyear = Integer.parseInt(juminNum.substring(0, 2)); //�̷����ϸ� �����ΰ��� ����� �ʿ����
		int gendercode = Integer.parseInt(juminNum.substring(7, 8));//substring(�����ּ�, �����ּ�+�����ð���); 
		if(gendercode == 1 || gendercode == 2 ) { //���� �Է��� �ֹ� 7��° ���ڰ� 1�̰ų� 2��
			myold = myold + 1900; 
		}else if(gendercode == 3 || gendercode == 4 ) { //���� �Է��� �ֹ� 7��° ���ڰ� 3�̰ų� 4��
			myold = myold + 2000;
		}
		int age = year - myold + 1;
		System.out.println("���̴�"+age+"�Դϴ�");
		
		//'����' ����
		if((juminNum.charAt(7)-'0') % 2 == 0) { //2,4�ΰ�� ���� ¦���� ����
 			System.out.println("����: ����");
		}else { 								// 1, 3 �ΰ�� Ȧ���� ����
			System.out.println("����: ����");
		}
		
		//������ȣ
		String localeStinrg = juminNum.substring(8, 10); //
		int locale = Integer.parseInt(localeStinrg);
		String birthPlace = null;
//123456-1251233
		for(int j = 0; j <20; j++) {
			if(locale >= Integer.parseInt(localeCode[j][1]) && (locale <= Integer.parseInt(localeCode[j][2]))) {
				birthPlace = localeCode[j][0]; 
			}
		}
		System.out.println("�������:"+birthPlace);
		//'�������' ����
		System.out.println("�������:"+myold+"/"+juminNum.substring(2, 4)+"/"+juminNum.substring(4, 6)+"/"+zod[myold%12]+"���Դϴ�.");
		
		//'��' ����
		// zod[myold%12]+"���Դϴ�."
	}
}
	/*
   //2�ܰ����
   temp = 11 = (sum%11); // 11��ⷯ�� ����
   //3�ܰ����
   result = temp%10;
   
   if(result == juminNum.charAt(13) - 48){
    sysotem.out.println("�Է����ֹι�ȣ�� �����Դϴ�");
   }else {
	 sysotem.out.println("Ʋ���ֹ��Դϴ�.");
   }
   */
  
