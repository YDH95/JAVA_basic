package �˰���_����;

public class SelectionShortAlgorithmEx {

	public static void main(String[] args) {
		// �ڷᱸ�� �غ� �ܰ�=> �迭 ��ü ����
		int[] num = { 30, 50, 10, 40, 20 }; //�迭�� �ʱ����

		// �ʿ��� ���� ����
		int temp; // �ӽ� ��� ���
		//int n = 5; // �迭�� ������ ������ 5��

		// ó�� �ܰ� => ���� ���� �˰��� ����
		for (int i = 0; i < num.length; i++) { 
			//System.out.println(i + "��");
			for (int j = i + 1; j < num.length; j++) {
				//System.out.print(j + "��");
				if (num[i] > num[j]) {
					temp = num[i];
					num[i] = num[j];
					num[j] = temp;
				}
			}
		}
		System.out.println("<<< ���ĵ� ������ >>>");
		for (int k = 0; k < num.length; k++) {
			System.out.print(num[k] + " ");
		}
	}
}