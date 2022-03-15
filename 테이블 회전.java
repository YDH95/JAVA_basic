  public static void main(String[] args) throws IOException {
      int[][] arr = {{0,0,0},{1,0,0},{0,1,1}}; // 2차원 배열 생성
      int[][] temp = {{0,0,0},{0,0,0},{0,0,0}}; // 보관해줄 다른 배열 추가
// 	  int[][] temp = new int[arr.length][arr[0].length];
		
//		for (int i = 0; i < temp.length; i++) {
//			for (int j = 0; j < temp.length; j++) {
//				System.out.print(temp[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		int N = arr.length; // 배열 길이
		
		for (int k = 0; k < 4; k++) {
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[0].length; j++) {
					
					if (k == 0) { // 90도
						temp[i][j] = arr[N - j - 1][i];
					} else if (k == 1) { // 180도
						temp[i][j] = arr[N - i - 1][N - j - 1];
					} else if (k == 2) { // 270도
						temp[i][j] = arr[j][N - i - 1];
					} else if (k == 3) { // 360도
						temp[i][j] = arr[i][j];
					}
				}
			}
			
			for (int l = 0; l < temp.length; l++) {
				for (int m = 0; m < temp[l].length; m++) {
					System.out.print(temp[l][m] + " ");
				}
				System.out.println();
			}
			System.out.println(); 
			
//			temp = new int[arr.length][arr[0].length];
		}
	}
}
