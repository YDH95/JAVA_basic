=============================
for문 if문 연습
=============================
// Scanner보다 속도가빠름
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// System.out.println() 보다 빠름
BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


=============================================================
for (int i = 0; i < 5; i++) {
  bw.write("*");
  for (int j = 0; j < i; j++) {
    bw.write("*");
  }
  bw.newLine();
}
==================
*
**
***
****
*****
==================

=============================================================
for (int i = 1; i < 6; i++) {
  for (int j = 5; j > 0; j--) {
    if (i < j) {
      bw.write(" ");
    }else {
      bw.write("*");
    }

  }

  bw.newLine();
}
==================
    *
   **
  ***
 ****
*****
==================

=============================================================
for (int i = 1; i < 5; i++) {
  for (int j = 4; j > 0; j--) {
    if (i < j) {
      bw.write(" ");
    } else {
      bw.write("*");
    }
  }
  bw.newLine();
}

for (int i = 0; i < 3; i++) {
  bw.write(" ");
  for (int j = 0; j < 3; j++) {
    if (j < i) {
      bw.write(" ");
    }else {
      bw.write("*");
    }

  }
  bw.newLine();
}

bw.flush(); // 입력해줘야 콘솔창에 출력됨
bw.close(); // 끝맺음

=============================================================
   *
  **
 ***
****
 ***
  **
   *
=============================================================
