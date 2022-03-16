===================================
Pattern과 Matcher를 사용한 번호체크
===================================
public static void main(String[] args) {
  Pattern phoneNumberCheck = Pattern.compile("\\d{3}-\\d{4}-\\d{4}");
  String number = "010-1234-1234";
  Matcher matcher = phoneNumberCheck.matcher(number);

  if (matcher.matches()) {
    System.out.println(true);
  } else {
    System.out.println(false);
  }


}
