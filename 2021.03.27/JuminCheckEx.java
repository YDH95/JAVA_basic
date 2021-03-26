package 주민번호_체크예제;

import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class JuminCheckEx {
	/*
	 * 본인의 주민번호를 입력받아 1단계 체크 공식을 적용하여 sum 값을 출력하는 프로그램을 작성하시오. [힌트] String 클래스의
	 * charAt()를 활용
	 * 
	 */
	/**
	 * 
	 * 주민번호 체크 검증코드(끝자리) 
	 * 1 2 3 4 5 6 - 1 2 3 4 5 6(7) 
	 * x 2 3 4 5 6 7 8 9 2 3 4 5
	 * _______________________________ 
	 * 1단계:2 6 12 .......... 
	 * 2단계: 각자리수에서 곱한 숫자들을 모두
	 * 합산 : 2+6+12 ........... = sum 
	 * 3단계: 더한결과를 11로 나누어 나머지를 구한다 sum % 11 = 나머지 
	 * 4단계: 11에서 나머지의 결과를 빼준다 11 - 나머지 = 결과값이 검증코드와 같은지 비교
	 * 
	 */
	public static void main(String[] args) {
		// 준비단계
		String juminNum;
		int sum = 0; // 누계변수는 반드시 0으로 초기화
		int[] weight = { 2, 3, 4, 5, 6, 7, 0, 8, 9, 2, 3, 4, 5};
		String[][] localeCode = {{"서울","00","08"},{"부산","09","12"},
								 {"인천","13","15"},{"경기","16","25"},
								 {"강원","26","34"},{"충북","35","39"},
								 {"대전","40","41"},{"충남","42","47"},
								 {"충남","42","47"},{"세종","44","44"},
								 {"세종","96","96"},{"전북","48","45"},
								 {"전남","55","64"},{"광주","65","66"},
								 {"대구","67","70"},{"경북","71","80"},
								 {"경남","81","84"},{"경남","86","90"},
								 {"울산","85","85"},{"제주","91","95"}};
															
		String[] zod = {"원숭이","닭","개","돼지",
						"쥐","소","범","토끼",
						"용","뱀","말","양"};
		
		// 입력단계
		System.out.print("주민번호를 입력하세요>>> ");
		Scanner sc = new Scanner(System.in);
		juminNum = sc.next();
		
		// 처리단계 => logic => 제어문을 활용
		for (int i = 0; i < 13; i++) {
			if (juminNum.charAt(i) == '-') {
						//continue;
			} else {
				sum += (juminNum.charAt(i) - '0') * weight[i];
			}
		}
		if (11 - (sum % 11) == juminNum.charAt(13) - '0') {
					System.out.println("확인되었습니다");
		} else {
			System.out.println("주민번호를 다시 확인해주세요");
		}
		/**
		 * 입력된 주민번호 유효성 검사 => 정규 표현식 패턴 적용
		 */
		String regex = "^[0-9]{6}-[1234][0-9]{6}$"; 
		//2가지 방법
		boolean check = juminNum.matches(regex);
		//boolean check = Pattern.matches(regex, juminNum); 
		if(check == false) {
			System.out.println("입력된 주민번호는 정규표현식에 맞지 않음.");
		}else {
			System.out.println("입력된 주민번호는 정규표현식에 적합함.");
		}   
		Calendar cal = Calendar.getInstance(Locale.KOREA); //한국 시간 가져오기
		int year = cal.get(Calendar.YEAR); //한국 연도만 가져와서 변수에넣기
		String myyear = juminNum.substring(0, 2); //substring으로 입력한 주민번호 연도만 빼오기
		int myold = Integer.parseInt(myyear); //연도 문자열을 정수로변환
	  //String myyear = Integer.parseInt(juminNum.substring(0, 2)); //이렇게하면 변수두개를 사용할 필요없음
		int gendercode = Integer.parseInt(juminNum.substring(7, 8));//substring(시작주소, 시작주소+가져올갯수); 
		if(gendercode == 1 || gendercode == 2 ) { //만약 입력한 주민 7번째 숫자가 1이거나 2면
			myold = myold + 1900; 
		}else if(gendercode == 3 || gendercode == 4 ) { //만약 입력한 주민 7번째 숫자가 3이거나 4면
			myold = myold + 2000;
		}
		int age = year - myold + 1;
		System.out.println("나이는"+age+"입니다");
		
		//'성별' 추출
		if((juminNum.charAt(7)-'0') % 2 == 0) { //2,4인경우 여자 짝수는 여자
 			System.out.println("성별: 여자");
		}else { 								// 1, 3 인경우 홀수는 남자
			System.out.println("성별: 남자");
		}
		
		//지역번호
		String localeStinrg = juminNum.substring(8, 10); //
		int locale = Integer.parseInt(localeStinrg);
		String birthPlace = null;
//123456-1251233
		for(int j = 0; j <20; j++) {
			if(locale >= Integer.parseInt(localeCode[j][1]) && (locale <= Integer.parseInt(localeCode[j][2]))) {
				birthPlace = localeCode[j][0]; 
			}
		}
		System.out.println("출생지역:"+birthPlace);
		//'생년월일' 추출
		System.out.println("생년월일:"+myold+"/"+juminNum.substring(2, 4)+"/"+juminNum.substring(4, 6)+"/"+zod[myold%12]+"띠입니다.");
		
		//'띠' 추출
		// zod[myold%12]+"띠입니다."
	}
}
	/*
   //2단계공식
   temp = 11 = (sum%11); // 11모듈러스 적용
   //3단계공식
   result = temp%10;
   
   if(result == juminNum.charAt(13) - 48){
    sysotem.out.println("입력한주민번호는 정상입니다");
   }else {
	 sysotem.out.println("틀린주민입니다.");
   }
   */
  
