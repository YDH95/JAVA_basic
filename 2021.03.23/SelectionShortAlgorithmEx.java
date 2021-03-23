package 알고리즘_예제;

public class SelectionShortAlgorithmEx {

	public static void main(String[] args) {
		// 자료구조 준비 단계=> 배열 객체 생성
		int[] num = { 30, 50, 10, 40, 20 }; //배열의 초기상태

		// 필요한 변수 설정
		int temp; // 임시 기억 장소
		//int n = 5; // 배열의 데이터 갯수가 5개

		// 처리 단계 => 선택 정렬 알고리즘 적용
		for (int i = 0; i < num.length - 1; i++) { 
			//System.out.println(i + "밖");
			for (int j = i + 1; j < num.length; j++) {
				//System.out.print(j + "안");
				if (num[i] > num[j]) {
					temp = num[i];
					num[i] = num[j];
					num[j] = temp;
				}
			}
		}
		System.out.println("<<< 정렬된 데이터 >>>");
		for (int k = 0; k < num.length; k++) {
			System.out.print(num[k] + " ");
		}
	}
}
