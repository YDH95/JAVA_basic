package StringÅ¬·¡½º_¿¹Á¦;

public class String_equals_Ex {

	public static void main(String[] args) {
		String strvar1 = "¼ÕÈï¹Î";
		String strvar2 = "¼ÕÈï¹Î";

		String strvar3 = new String("¼ÕÈï¹Î");// Áßº¹ÀÌ ¾ÈµÊ
		String strvar4 = new String("¼ÕÈï¹Î");// Áßº¹ÀÌ ¾ÈµÊ

		System.out.println(strvar1 == strvar2); // °á°ú: true
		System.out.println(strvar1 == strvar3); // °á°ú: true
		System.out.println(strvar3 == strvar4); // °á°ú: true
		System.out.println(strvar1.equals(strvar2));
		System.out.println(strvar1.equals(strvar3));
		System.out.println(strvar3.equals(strvar4));

	}

}
