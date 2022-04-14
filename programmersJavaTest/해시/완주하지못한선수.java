package java_study;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 완주하지못한선수 {

	public static void main(String[] args) {
		String[] participant = {"mislav", "stanko", "mislav", "ana"};	
		String[] completion = {"stanko", "ana", "mislav"};
		
//		String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};	
//		String[] completion = {"josipa", "filipa", "marina", "nikola"};
		
		String answer = "";
		Map<String, Integer> map = new HashMap<>();

		// 정렬
		Arrays.sort(participant); 
		Arrays.sort(completion);
		
        for (String p : participant) {
            map.put(p, map.getOrDefault(p, 0)+1);
            		  // getOrDefault(값, 기본값) 값이 있으면 1 더한다
        }
        System.out.println(map); // {ana=1, mislav=2, stanko=1}
        
        for (String c : completion) {
            map.put(c, map.get(c) - 1); // 반대로 1을 빼면서 삽입
        }
        System.out.println(map); // {ana=0, mislav=1, stanko=0}
        
        System.out.println(map.keySet()); // [ana, mislav, stanko]
        
        for (String key : map.keySet()) {
            if (map.get(key) == 1) { // key값이 1인것만 넣기
                answer = key;
                break;
            }
        }
        
        System.out.println(answer); // mislav

	}

}
