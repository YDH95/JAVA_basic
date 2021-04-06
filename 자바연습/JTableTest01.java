package JTable_�ǽ�_����;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

// JFrame Ŭ������ �ֻ��� �����̳� Ŭ������.

public class JTableTest01 extends JFrame{
	//���� ���� �������� ����
	JTable table;
	JScrollPane scroll;
	
	//JTable�� �� �÷��� ���� ������ ǥ���� 1���� �迭 ��ü ����
	String[] title = {"��ȣ","�̸�","����ó","�̸���"};
	
	String[][] data = {{"1","�丶��","010-1234-5688","wow@gmail.com"},
			{"2","�迬��","010-3241-1242","hky@naver.com"},
			{"2","�迬��","010-3241-1242","hky@naver.com"},
			{"3","�ΰ���","010-5315-1251","gwg@nate.com"}};
	
	
	
	//������
	public JTableTest01() {
		setTitle("JTable �����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		table = new JTable(data, title);
		scroll = new JScrollPane(table);
		
		//�� ũ�� �����ϱ�
		table.getColumnModel().getColumn(0).setPreferredWidth(50); //��ȣ
		table.getColumnModel().getColumn(1).setPreferredWidth(70); //�̸�
		table.getColumnModel().getColumn(2).setPreferredWidth(120); //����ó
		table.getColumnModel().getColumn(3).setPreferredWidth(170); //����
		
		
		add(scroll);
		
		setSize(450, 200);
		setLocation(500, 300);
		setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		//��ü ���� 
		JTableTest01 jt = new JTableTest01();
		
		
	}

}
