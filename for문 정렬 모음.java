public static void main(String[] args) {
	int arr[] = { 4, 23, 33, 15, 17, 19 };

	for (int i = 0; i < arr.length; i++) {
		System.out.print("[" + arr[i] + "]"); // [4][23][33][15][17][19]
	}

	Arrays.sort(arr);

	System.out.println();

	for (int i = 0; i < arr.length; i++) {
		System.out.print("[" + arr[i] + "]"); // [4][15][17][19][23][33]
	}

	System.out.println();

	for (int i : arr) {
		System.out.print("[" + i + "]"); // [4][15][17][19][23][33]
	}

	System.out.println();

	String b = Arrays.toString(arr);
	System.out.println(b); // [4, 15, 17, 19, 23, 33]
}
==================================버블정렬=======================================
	int arr[] = { 4, 23, 33, 15, 17, 19 };
	int temp;

	for (int i = 0; i < arr.length; i++) {
		for (int j = i+1; j < arr.length; j++) {
			if(arr[i] > arr[j]) {
				temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}

		}

	}

	for (int i = 0; i < arr.length; i++) {
		System.out.print("[" + arr[i] + "]"); // [4][15][17][19][23][33]
	}
====================================버블정렬 흐름=============================================
	for (int i = 0; i < arr.length; i++) {
		for (int j = i + 1; j < arr.length; j++) {

			System.out.println("arr[i]: " + arr[i] + ", " +"arr[j]: " + arr[j]);
			System.out.println(arr[i] > arr[j]);
		}
	}
	arr[i]: 4, arr[j]: 23
	false
	arr[i]: 4, arr[j]: 33
	false
	arr[i]: 4, arr[j]: 15
	false
	arr[i]: 4, arr[j]: 17
	false
	arr[i]: 4, arr[j]: 19
	false
	arr[i]: 23, arr[j]: 33
	false
	arr[i]: 23, arr[j]: 15
	true
	arr[i]: 23, arr[j]: 17
	true
	arr[i]: 23, arr[j]: 19
	true
	arr[i]: 33, arr[j]: 15
	true
	arr[i]: 33, arr[j]: 17
	true
	arr[i]: 33, arr[j]: 19
	true
	arr[i]: 15, arr[j]: 17
	false
	arr[i]: 15, arr[j]: 19
	false
	arr[i]: 17, arr[j]: 19
	false

====================================A ~ Z 까지 출력=============================================
for (char i = 'A'; i <= 'Z'; i++) {
	System.out.print(i + " "); // A B C D E F G H I J K L M N O P Q R S T U V W X Y Z 
}

====================================A ~ Z 까지 아스키코드로 출력=============================================
for (int i = 'A'; i <= 'Z'; i++) {
	System.out.print(i + " "); // 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 
}
