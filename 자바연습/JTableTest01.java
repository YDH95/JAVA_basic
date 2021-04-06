package JTable_실습_예제;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

// JFrame 클래스는 최상위 컨테이너 클래스다.

public class JTableTest01 extends JFrame{
	//전역 변수 참조변수 선언
	JTable table;
	JScrollPane scroll;
	
	//JTable에 들어갈 컬럼의 제목 정보를 표현할 1차원 배열 객체 생성
	String[] title = {"번호","이름","연락처","이메일"};
	
	String[][] data = {{"1","토마스","010-1234-5688","wow@gmail.com"},
			{"2","김연아","010-3241-1242","hky@naver.com"},
			{"2","김연아","010-3241-1242","hky@naver.com"},
			{"3","인간인","010-5315-1251","gwg@nate.com"}};
	
	
	
	//생성자
	public JTableTest01() {
		setTitle("JTable 만들기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		table = new JTable(data, title);
		scroll = new JScrollPane(table);
		
		//셀 크기 조절하기
		table.getColumnModel().getColumn(0).setPreferredWidth(50); //번호
		table.getColumnModel().getColumn(1).setPreferredWidth(70); //이름
		table.getColumnModel().getColumn(2).setPreferredWidth(120); //연락처
		table.getColumnModel().getColumn(3).setPreferredWidth(170); //메일
		
		
		add(scroll);
		
		setSize(450, 200);
		setLocation(500, 300);
		setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		//객체 생성 
		JTableTest01 jt = new JTableTest01();
		
		
	}

}
