package �������ý��۱����Կ���;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/*************************************************************************
 * | ������ �ý���(����, ����, �̸�����, �߰�, ���� ��ɱ���) | <<�����̷�(Modification Information)>> |
 * ������ ������ �������� |
 * ----------------------------------------------------------------------| ��)
 * 2021.04.08 ���� ����,����, �߰� ��ɱ��� | ���� ���� �ҷ����� ��� ���� |
 *************************************************************************/
//���� Ŭ����(inner class)
public class CustomerSystem extends JFrame { // �ܺ� Ŭ����
	// �̹��� ���� �ڵ� ���
	public static final String String = null;

	// ���� Ŭ���� ��ü ���� => ��ü���� �������� ����

	Menumain menumain = new Menumain(); // �޴�
	West west = new West(); // ���� �Է�
	West2 west2 = new West2(); // ���� Ȯ�� �� �˻�
	ShowTable showtable = new ShowTable(); // �Է� Ȯ��
	Buttons buttons = new Buttons(); // ��ư
	BorderLayout borderLayout = new BorderLayout();
	JPanel westPanel = new JPanel();

	int updateRow; // '����' ��ư���� �̺�Ʈ �߻� �� ����� ��������
//==============================================================================
	// ������ => �ܺ� Ŭ����

	public CustomerSystem() {
		// �̹��� ���� �߰�
		OUTTER: while (true) {
			// jpg���� ��Ʈ�� ȭ�� ����
			ImageIcon icon = new ImageIcon("image/gettyimages740x490.jpg");
//			Image icon2 = icon.getImage();
//			Image icon3 = icon2.getScaledInstance(700, 600, java.awt.Image.SCALE_AREA_AVERAGING);
//			ImageIcon icon4 = new ImageIcon(icon3);
			JOptionPane.showMessageDialog(null, null, "������ ���� �ý���", JOptionPane.NO_OPTION, icon);

			// �н����� â ����
			String password = JOptionPane.showInputDialog(null, "������ �ý���" + "\n" + "�н����� �Է�", "��й�ȣ�Է�",
					JOptionPane.QUESTION_MESSAGE);
			String passwd = "0000";

			if (password == null) {
				break OUTTER;
			} else if (password.equals(passwd)) {

				setTitle("������ �ý���");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				add(menumain.mb, BorderLayout.NORTH);
				add(buttons, BorderLayout.SOUTH);
				add(westPanel, BorderLayout.WEST);
				add(showtable.scroll, BorderLayout.CENTER);
				// BorderLayout borderLayout = new BorderLayout();
				westPanel.setLayout(borderLayout); // borderLayout�� panel�� ����
				westPanel.add(west); // �гο� west�� ����
				westPanel.add(west2); // �гο� west2�� ����
				borderLayout.addLayoutComponent(west, BorderLayout.CENTER);
				borderLayout.addLayoutComponent(west2, BorderLayout.SOUTH);

				// window������ ����
				setSize(1000, 700);
				setLocation(500, 30);
				setVisible(true);
				break OUTTER;
			} else {
				JOptionPane.showMessageDialog(null, " �н����尡 ��ġ���� �ʽ��ϴ�." + "\n" + "'Ȯ��' ��ư�� ��������.", "�н����� ��������",
						JOptionPane.ERROR_MESSAGE);

				break;
			}
		}
	}

//================================================================================================
	// ����Ŭ���� ���� => �޴� ���� ����ϴ� Ŭ����
	class Menumain extends JPanel implements ActionListener, ItemListener {

		JMenuBar mb;
		JMenu file, sort, help;
		JMenuItem fnew, fopen, fsave, fexit, proinfo;
		JCheckBoxMenuItem sname, sNum, schulloc, sbirth;
		FileDialog readOpen, saveOpen;
		String fileDir, fileName, saveFileName, readFileName;

		// ������ => ����Ŭ���� ��
		public Menumain() {
			mb = new JMenuBar();
			file = new JMenu("����");
			sort = new JMenu("����");
			help = new JMenu("����");

			
			fnew = new JMenuItem("���� �����");
			fopen = new JMenuItem("����");
			fsave = new JMenuItem("����");
			fexit = new JMenuItem("�ݱ�");

			sNum = new JCheckBoxMenuItem("��ȣ");
			sname = new JCheckBoxMenuItem("�̸�");
			schulloc = new JCheckBoxMenuItem("�������");
			sbirth = new JCheckBoxMenuItem("����");

			proinfo = new JMenuItem("���α׷� ����");

			file.add(fnew);
			file.add(fopen);
			file.add(fsave);
			file.addSeparator(); // �Ǽ�
			file.add(fexit);
			sort.add(sNum);
			sort.add(sname);
			sort.add(schulloc);
			sort.add(sbirth);
			help.add(proinfo);

			mb.add(file);
			mb.add(sort);
			mb.add(help);

			// �̺�Ʈ ����
			fnew.addActionListener(this);
			fopen.addActionListener(this);
			fsave.addActionListener(this);
			fexit.addActionListener(this);
			proinfo.addActionListener(this);

			sNum.addItemListener(this);
			sname.addItemListener(this);
			schulloc.addItemListener(this);
			sbirth.addItemListener(this);

		}

//================================================================================================
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("���� �����"))
				fnew();
			if (e.getActionCommand().equals("����"))
				save();
			if (e.getActionCommand().equals("����"))
				open();
			if (e.getActionCommand().equals("�ݱ�"))
				exit();
			if (e.getActionCommand().equals("���α׷� ����"))
				info();

		}// end actionPerformed



//===============================���� ����=================================================================

		public void save() {
			saveOpen = new FileDialog(CustomerSystem.this, "��������", FileDialog.SAVE);
			saveOpen.setVisible(true);
			// C:\ �ؿ� '�������ý��۵���������' ��������
			// �������ý��۵�����.txt �� ����
			fileDir = saveOpen.getDirectory();
			fileName = saveOpen.getFile();
			saveFileName = fileDir + "//" + fileName;

			String str = "";
			String temp = "";

			try {
				BufferedWriter save = new BufferedWriter(new FileWriter(saveFileName)); // ���� ��� ����
				// [�߿� ����]
				for (int i = 0; i < showtable.table.getRowCount(); i++) { // table�� �ִ� ������ �� ������ �� ��, �ڵ��� ��ȣ, �ֹι�ȣ
					temp = showtable.data.elementAt(i).toString();
					str += temp.substring(1, temp.length() - 1) + "\n";
					// 1��° index���� temp�� ���� ������ -1, vector [] ������ [ȫ�浿,010,950604] ��ȣ�� index��
				}
				save.write(str);
				save.close();
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

//==============================������ ���� ����==================================================================
		public void open() {
			StringTokenizer st;
			Vector<String> vec;

			readOpen = new FileDialog(CustomerSystem.this, "��������", FileDialog.LOAD);
			readOpen.setVisible(true);

			fileDir = readOpen.getDirectory();
			fileName = readOpen.getFile();
			readFileName = fileDir + "//" + fileName;

			try {
				BufferedReader read = new BufferedReader(new FileReader(readFileName));
				// JTable�� ��µ� 2����Vector���� �ʱ�ȭ��Ų�� ������
				showtable.data.removeAllElements();

				String line = null;
				// ���� ����
				while ((line = read.readLine()) != null) {
					st = new StringTokenizer(line, ", ");
					vec = new Vector<String>();

					while (st.hasMoreTokens()) {
						vec.add(st.nextToken());
					}
					showtable.data.addElement(vec);
				}
				showtable.datamodel.fireTableDataChanged(); // fireTableDataChanged() �޼ҵ带 ȣ��

			} catch (IOException e) {
				System.out.println(e);
			}
		}

//===========================������ �����============================================================
		public void exit() {
			System.exit(0);
		}

//================================================================================================
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource().equals(sNum)) // ��ȣ
				sortName(0); // ��ȣ����
			if (e.getSource().equals(sname)) // �̸�
				sortName(1); // �̸�����
			if (e.getSource().equals(schulloc)) // ��� ����
				sortName(7); // �����������
			if (e.getSource().equals(sbirth)) // ����
				sortName(8); // ��������
		}

		public void sortName(int sort) {
			int row = showtable.table.getRowCount();
			int col = showtable.table.getColumnCount();

			String[][] arr = new String[row][col];
			String temp;
			// JTable�� �����͵��� arr[][] 2���� �迭�� �ű��
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					arr[i][j] = (String) showtable.table.getValueAt(i, j);
				}
			}
			// 2���� �迭 => ���� ���� �˰��� ����
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < row; j++) {
					if (arr[i][sort].compareTo(arr[j][sort]) > 0) {
						for (int k = 0; k < col; k++) {
							temp = arr[i][k];
							arr[i][k] = arr[j][k];
							arr[j][k] = temp;
						}
					}
				}
			}
			// 2���� �迭�� �����͵��� JTable�� �ű��
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					showtable.table.setValueAt(arr[i][j], i, j);
				}
			}
		}
	}

//================================================================================================
// ���� Ŭ���� ���� => ������ �Է�, �Ż������� ����ϴ� Ŭ����
	class West extends JPanel {
		// ��� ���� ����
		JLabel la;
		JTextField tf[]; // JTextField ���۳�Ʈ ��ü���� �ְ��������� �迭
		// ������ => ����Ŭ���� ��
		JComboBox<String> jobs;

		public West() {
			// [�Է�] ���� �����
			LineBorder line = new LineBorder(Color.BLUE, 1);// ������ �β�
			setBorder(new TitledBorder(line, "�Է�"));

			String[] text = { "��ȣ", "�� ��", "�ڵ��� ��ȣ", "�̸���", "�ֹι�ȣ", "����" };
			tf = new JTextField[7];
			setLayout(new GridLayout(8, 2, 5, 10));

			String[] job = { "��������", "ȸ���", "�ֺ�", "�л�", "����", "��Ÿ" };

			jobs = new JComboBox<String>(job);

			for (int i = 0; i < text.length; i++) {
				la = new JLabel(text[i]);
				tf[i] = new JTextField();
				la.setHorizontalAlignment(JLabel.CENTER);
				tf[i].setHorizontalAlignment(JLabel.CENTER);

				add(la);
				add(tf[i]);

			}
//			add(new JLabel("                      ����"));
			add(jobs);
			setPreferredSize(new Dimension(230, 300));
		}
	}

//===============================================================================
	class West2 extends JPanel implements ActionListener {
		JPanel p = new JPanel(); // �����˻��г�
		JPanel search = new JPanel();
		CardLayout card;
		JRadioButton[] searchRadio = new JRadioButton[4];
		JLabel ageLabel = new JLabel();
		JLabel sexLabel = new JLabel();
		JLabel chulLabel = new JLabel();
		JLabel birthLabel = new JLabel();
		JTextField jf = new JTextField(10);

		// ������
		public West2() {
			card = new CardLayout();
			setLayout(card);
			// �Ż�����ī��
			p.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 1), "�Ż�����"));
			p.setLayout(new GridLayout(4, 2, 10, 5));

			p.add(new JLabel("����", JLabel.LEFT));
			p.add(ageLabel);

			p.add(new JLabel("����", JLabel.LEFT));
			p.add(sexLabel);

			p.add(new JLabel("�����", JLabel.LEFT));
			p.add(chulLabel);

			p.add(new JLabel("����", JLabel.LEFT));
			p.add(birthLabel);

			p.setPreferredSize(new Dimension(260, 210));

			add(p, "ù��° ī��");

			search.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 2), "�˻�"));

			ButtonGroup group = new ButtonGroup();

			String[] search_name = { "�̸�", "����", "�������", "���� ����" };
			JButton search_btn = new JButton("ã��");
			JButton exit_btn = new JButton("������");

			// ����� ������ġ laout�ϴ¹�
			search.setLayout(null);
			int x = -70;
			// ��ī��� ũ�Ⱑ �Ȱ��ƾ� ��ȯ�� �� �ٲ�� ������ ��
			search.setPreferredSize(new Dimension(350, 210));

			for (int i = 0; i < searchRadio.length; i++) {
				searchRadio[i] = new JRadioButton(search_name[i]);
				// �ݺ��� ���� ��, x���� ����Ǹ鼭 searchRadio ��ư��ġ�� �������ֱ����� �ۿ� -70�� �������ذ�
				searchRadio[i].setBounds(x += 80, 30, 80, 30);
				search.add(searchRadio[i]);
				group.add(searchRadio[i]);

			}

			jf.setBounds(25, 80, 200, 30);
			search_btn.setBounds(25, 130, 70, 30);
			exit_btn.setBounds(115, 130, 110, 30);
			search.add(jf);
			search.add(search_btn);
			search.add(exit_btn);

			add(search, "�ι�° ī��");

			search_btn.addActionListener(this);
			exit_btn.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("ã��"))
				search_btn();
			if (e.getActionCommand().equals("������"))
				exit_btn();

		}

		// ������ ��ư�̺�Ʈ �ż���
		public void exit_btn() {
			// ī�巹�̾ƿ�����
			card.next(west2);
			// JTable�� �������� ���
			showtable.datamodel.setDataVector(showtable.data, showtable.colum_name);
			// �˻���ưȰ��ȭ
			buttons.search.setEnabled(true);
			// �Ż����� �ʱ�ȭ
			if (west.tf[0].getText().trim().length() == 0) {
				ageLabel.setText(null);
				sexLabel.setText(null);
				chulLabel.setText(null);
				birthLabel.setText(null);

			}

		}

		public void search_btn() {
			if (jf.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "�˻����� �Է����ּ���", "���޽���", JOptionPane.ERROR_MESSAGE);
				jf.requestFocus(true);
			} else {
				// JTable���� �˻��� 2���迭�� �����Ͽ� �ѷ��ֱ� ���� �ӽú��� ���
				Vector<Vector<String>> findData = new Vector<Vector<String>>();

				// ������ �����ڽ��� JTable�� �˻��� ���� �ε����� �ʱ�ȭ
				int num = 0;
				if (searchRadio[0].isSelected())
					num = 1; // �̸�
				if (searchRadio[1].isSelected())
					num = 9; // ����
				if (searchRadio[2].isSelected())
					num = 7; // �������
				if (searchRadio[3].isSelected())
					num = 8; // ����

				for (int i = 0; i < showtable.data.size(); i++) {
					// 2���� Vector�� data���� ���� �ش� ���� �Է°��� ���ԵǾ� �ִ��� üũ�Ѵ�
					if (showtable.data.elementAt(i).get(num).contains(jf.getText().trim())) {
						// �ӽ� 2���� ���Ϳ� �߰�
						findData.addElement(showtable.data.elementAt(i));
					}
				}
				// ���Ϳ� �ƹ��͵� ���ٸ� (�˻��� ���� �������)
				if (findData.isEmpty()) {
					JOptionPane.showMessageDialog(null, "�˻��Ͻ� ������ �����ϴ�", "���޽���", JOptionPane.ERROR_MESSAGE);
					// showtable.datamodel.setDataVector(showtable.data, showtable.columnNames);
					jf.setText(null);
				} else {
					// JTable datamodel�� 2���� ���ͳֱ�
					// �÷� ���Ϳ� ������ ���� ��ġ��
					showtable.datamodel.setDataVector(findData, showtable.colum_name);
					showtable.datamodel.fireTableDataChanged();
				}
			}

		}
	}

//================================================================================================
// ���� Ŭ���� ���� => JTable�� �Է��� ������ �����ִ� Ŭ����
	class ShowTable extends MouseAdapter {// MouseAdapterŬ���� ���
		DefaultTableModel datamodel; // �߰�, ����, ���� ��� �߰�
		JTable table;
		JScrollPane scroll;

		String[] colName = { "��ȣ", "�� ��", "�ڵ�����ȣ", "�̸���", "�ֹι�ȣ", "����", "����", "�������", "����", "����" }; // 9

		// [�߿�]
		// DefaultTableModel Ŭ������ ������
		// DefaultTableModel(Vector data,Vector columnNames)
		// �ڷ� ����
		Vector<Vector<String>> data;
		Vector<String> colum_name;

		// ������ => ����Ŭ���� ��
		public ShowTable() {
			data = new Vector<Vector<String>>(); // �⺻ 10���� �����迭 ��ü����
			colum_name = new Vector<String>(); // �⺻ 10���� �����迭 ��ü����

			for (int i = 0; i < colName.length; i++) {
				colum_name.add(colName[i]);
			}
//			[1�ܰ�] DefaultTableModel ����
//			[�߿�]
			datamodel = new DefaultTableModel(data, colum_name); // �߰�, ����, ���� ��� ����

//			[2�ܰ�]
			table = new JTable(datamodel);

//			[3�ܰ�]
			scroll = new JScrollPane(table);

			// ���� ũ�� ����
			table.getColumnModel().getColumn(0).setPreferredWidth(50); // ��ȣ
			table.getColumnModel().getColumn(1).setPreferredWidth(50); // �̸�
			table.getColumnModel().getColumn(2).setPreferredWidth(50); // �ڵ�����ȣ
			table.getColumnModel().getColumn(3).setPreferredWidth(50); // �̸���
			table.getColumnModel().getColumn(4).setPreferredWidth(50); // �ֹι�ȣ
			table.getColumnModel().getColumn(5).setPreferredWidth(50); // ����

			// ���� �����͸� ��� ���� ��Ű��
			DefaultTableCellRenderer tableCell = new DefaultTableCellRenderer(); // �۾� �߾� ����
			tableCell.setHorizontalAlignment(SwingConstants.CENTER);
			TableColumnModel tb = table.getColumnModel();
			for (int i = 0; i < tb.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(tableCell);
			}
			// �̺�Ʈ����
			table.addMouseListener(this);
		}

//================================================================================================
		@Override
		public void mouseClicked(MouseEvent e) { // MouseAdapter�� ������ִ� mouseClicked�޼��带 �ҷ���
			updateRow = table.getSelectedRow();// ������ ���� index���� ����
			System.out.println("updateRow" + updateRow);
			west.tf[0].setText((String) showtable.table.getValueAt(updateRow, 0));
			west.tf[1].setText((String) showtable.table.getValueAt(updateRow, 1));
			west.tf[2].setText((String) showtable.table.getValueAt(updateRow, 2));
			west.tf[3].setText((String) showtable.table.getValueAt(updateRow, 3));
			west.tf[4].setText((String) showtable.table.getValueAt(updateRow, 4));
			west.jobs.setSelectedItem((String) showtable.table.getValueAt(updateRow, 9));
			// ��[�߿�] �ֹι�ȣ�� ���� ��Ȱ��ȭ ��Ű��
			west.tf[4].setEditable(false);

			// �� Ŭ���� �Ż������� ������ ��
			west2.ageLabel.setText((String) showtable.table.getValueAt(updateRow, 5));
			west2.sexLabel.setText((String) showtable.table.getValueAt(updateRow, 6));
			west2.chulLabel.setText((String) showtable.table.getValueAt(updateRow, 7));
			west2.birthLabel.setText((String) showtable.table.getValueAt(updateRow, 8));
		}
	}

//================================================================================================
// ���� Ŭ���� ���� => �ϴܿ� ��ư�� ��� ó�� ��� Ŭ����
	class Buttons extends JPanel implements ActionListener {
		// ��� ���� ����
		Vector<String> vector;
		JButton addBtn, updateBtn, delBtn, back, next, search;
		String juminNo;

		// ������ => ����Ŭ���� ��
		public Buttons() {
			setLayout(new GridLayout(1, 6, 5, 0));
//			setLayout(new FlowLayout(3));
			addBtn = new JButton("�߰�");
			delBtn = new JButton("����");
			back = new JButton("����");
			updateBtn = new JButton("����");
			next = new JButton("����");
			search = new JButton("�˻�");

			addBtn.setBackground(Color.WHITE);
			delBtn.setBackground(Color.WHITE);
			back.setBackground(Color.WHITE);
			next.setBackground(Color.WHITE);
			updateBtn.setBackground(Color.WHITE);
			search.setBackground(Color.WHITE);

			add(addBtn);
			add(delBtn);
			add(back);
			add(next);
			add(updateBtn);
			add(search);

			// �̺�Ʈ ����
			addBtn.addActionListener(this);
			updateBtn.addActionListener(this);
			delBtn.addActionListener(this);
			back.addActionListener(this);
			next.addActionListener(this);
			search.addActionListener(this);

		}

//================================================================================================
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("�߰�"))
				addData(); // ����� ���� �޼ҵ� ȣ��
			if (e.getActionCommand().equals("����"))
				updateData(); // ����� ���� �޼ҵ� ȣ��
			if (e.getActionCommand().equals("����"))
				deleteData(); // ����� ���� �޼ҵ� ȣ��
			if (e.getActionCommand().equals("����"))
				nextData(); // ����� ���� �޼ҵ� ȣ��
			if (e.getActionCommand().equals("����"))
				backData(); // ����� ���� �޼ҵ� ȣ��
			if (e.getActionCommand().equals("�˻�"))
				searchData(); // ����� ���� �޼ҵ� ȣ��

		}// actionPerformed end

		// updateData() ����� ���� �޼ҵ� ����
		// ����� ���� �޼ҵ�
//================================================================================================

		public void addData() {
			Vector<String> vector = new Vector<String>();
			// �� üũ �ż���
			boolean checkInput = validInput();
			if (checkInput == false)
				return;

			vector.add(west.tf[0].getText()); // ��ȣ �Է°� ������
			vector.add(west.tf[1].getText()); // �̸� �Է°� ������
			vector.add(west.tf[2].getText()); // ��ȭ��ȣ �Է°� ������
			vector.add(west.tf[3].getText()); // �̸��� �Է°� ������

			juminNo = west.tf[4].getText(); // �ֹι�ȣ������
			// �ֹι�ȣ ��ȿ��üũ

			// ===========================================================================
			/**
			 * �Էµ� �ֹι�ȣ ��ȿ�� �˻� => ���� ǥ���� ���� ����
			 */
			String regex = "^[0-9]{6}-[1234][0-9]{6}$";
			// 2���� ���
			boolean check = juminNo.matches(regex);
			if (check == false) {
				JOptionPane.showMessageDialog(null, "�ֹι�ȣ������ǥ���Ŀ� ��������", "���޼���", JOptionPane.ERROR_MESSAGE);
				west.tf[4].setText(null);
				west.tf[4].requestFocus();
				return; // ��������
			}

			int sum = 0; // ���躯���� �ݵ�� 0���� �ʱ�ȭ
			int[] weight = { 2, 3, 4, 5, 6, 7, 0, 8, 9, 2, 3, 4, 5 };
			String[][] localeCode = { { "����", "00", "08" }, { "�λ�", "09", "12" }, { "��õ", "13", "15" },
					{ "���", "16", "25" }, { "����", "26", "34" }, { "���", "35", "39" }, { "����", "40", "41" },
					{ "�泲", "42", "47" }, { "�泲", "42", "47" }, { "����", "44", "44" }, { "����", "96", "96" },
					{ "����", "48", "45" }, { "����", "55", "64" }, { "����", "65", "66" }, { "�뱸", "67", "70" },
					{ "���", "71", "80" }, { "�泲", "81", "84" }, { "�泲", "86", "90" }, { "���", "85", "85" },
					{ "����", "91", "95" } };

			// ===========================================================================
			// ó���ܰ� => logic => ����� Ȱ��
			for (int i = 0; i < 13; i++) {
				if (juminNo.charAt(i) == '-') {
					// continue;
				} else {
					sum += (juminNo.charAt(i) - '0') * weight[i];
				}
			}
			if (11 - (sum % 11) == juminNo.charAt(13) - '0') {
				System.out.println("Ȯ�εǾ����ϴ�");
			} else {
				System.out.println("�ֹι�ȣ�� �ٽ� Ȯ�����ּ���");
			}

			vector.add(west.tf[4].getText()); // �ֹι�ȣ����

			// ===========================================================================
			Calendar cal = Calendar.getInstance(Locale.KOREA); // �ѱ� �ð� ��������
			int year = cal.get(Calendar.YEAR); // �ѱ� ������ �����ͼ� �������ֱ�
			String myyear = juminNo.substring(0, 2); // substring���� �Է��� �ֹι�ȣ ������ ������
			int myold = Integer.parseInt(myyear); // ���� ���ڿ��� �����κ�ȯ
			// String myyear = Integer.parseInt(juminNum.substring(0, 2)); //�̷����ϸ� �����ΰ��� �����
			// �ʿ����
			int gendercode = Integer.parseInt(juminNo.substring(7, 8));// substring(�����ּ�, �����ּ�+�����ð���);
			if (gendercode == 1 || gendercode == 2) { // ���� �Է��� �ֹ� 7��° ���ڰ� 1�̰ų� 2��
				myold = myold + 1900;
			} else if (gendercode == 3 || gendercode == 4) { // ���� �Է��� �ֹ� 7��° ���ڰ� 3�̰ų� 4��
				myold = myold + 2000;
			}
			int age = year - myold + 1;
			String a = Integer.toString(age);
			vector.add(a);
			// ===========================================================================
			// '����' ����
			if ((juminNo.charAt(7) - '0') % 2 == 0) { // 2,4�ΰ�� ���� ¦���� ����
				vector.add("����");
			} else { // 1, 3 �ΰ�� Ȧ���� ����
				vector.add("����");
			}
			// ===========================================================================
			// ������� ���
			String localeStinrg = juminNo.substring(8, 10); // �Է��� �ֹι�ȣ 8,9��° ����
			int locale = Integer.parseInt(localeStinrg);
			String birthPlace = "";
			// 123456-1251233
			for (int j = 0; j < localeCode.length; j++) {
				if (locale >= Integer.parseInt(localeCode[j][1]) && (locale <= Integer.parseInt(localeCode[j][2]))) {
					birthPlace = localeCode[j][0];
				}
			}
			System.out.println("�ּ�" + birthPlace);
			vector.add(birthPlace);
//			// '�������' ����
			vector.add(myold + "/" + juminNo.substring(2, 4) + "/" + juminNo.substring(4, 6));
			if (west.jobs.getSelectedItem().equals("��Ÿ")) {
				vector.add(west.tf[5].getText());
			} else {
				vector.add((String) west.jobs.getSelectedItem());
			}
			/*
			 * �Է¹��� �ֹι�ȣ�� ��ȿ�� üũ�� ������ �����ϸ� üũ�� �� �������� �ֹι�ȣ�϶��� Vector ��ü�� �����Ѵ�.
			 */

			west.tf[0].setText(null);
			west.tf[1].setText(null);
			west.tf[2].setText(null);
			west.tf[3].setText(null);
			west.tf[4].setText(null);
			west.tf[0].requestFocus();
			showtable.data.addElement(vector);
			// �����Ͱ� ����� �� ���� ������ JTable�� �����Ϸ���
			// fireTableDataChanged() �޼ҵ带 ȣ���ؾ��Ѵ�.
			showtable.datamodel.fireTableDataChanged();

		} // end add.data

	}

//================================================================================================
	public void updateData() {

		if (showtable.table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "������ �׸��� �������ֽðų� �Է¶��� ������ �Է����ּ���!", "��� �޼���",
					JOptionPane.ERROR_MESSAGE);
			west.tf[0].requestFocus();
			return;
		}

		// �� üũ �ż���
		boolean checkInput = validInput();
		if (checkInput == false)
			return;

		// ���� �� = table �� �����ϱ�
		showtable.table.setValueAt(west.tf[0].getText(), updateRow, 0);
		showtable.table.setValueAt(west.tf[1].getText(), updateRow, 1);
		showtable.table.setValueAt(west.tf[2].getText(), updateRow, 2);
		showtable.table.setValueAt(west.tf[3].getText(), updateRow, 3);
		showtable.table.setValueAt(west.tf[4].getText(), updateRow, 4);
		showtable.table.setValueAt(west.jobs.getSelectedItem(), updateRow, 9);

		west.tf[0].setText(null);
		west.tf[1].setText(null);
		west.tf[2].setText(null);
		west.tf[3].setText(null);
		west.tf[4].setText(null);

		// �ֹι�ȣ �Է¶� Ȱ��ȭ ��Ű��
		west.tf[4].setEditable(true);
		west.tf[0].requestFocus();
		// ������ Row���� ����
		showtable.table.removeRowSelectionInterval(0, updateRow);
	}

//================================================================================================
	// deletData()�޼ҵ� ����
	private void deleteData() {
		// JTable tb = showtable.table;

		int deleteRow = showtable.table.getSelectedRow();
		System.out.println("updateRow��" + updateRow);
		if (deleteRow == -1) { // ����ڰ� ���� �������� �ʰ� '����' ��ư�� �������
			JOptionPane.showMessageDialog(null, "�����Ͻ��� ������ư�� �������ּ���", "���� ���� �޼���", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int yes_no_select = JOptionPane.showConfirmDialog(null, "���� ���� �Ͻðڽ��ϴٱ�?", "�� ������ ����",
				JOptionPane.YES_NO_OPTION);
		if (yes_no_select == JOptionPane.YES_OPTION) {
			// DefaultTableModel model = (DefaultTableModel) tb.getModel();
			showtable.datamodel.removeRow(deleteRow);

			west.tf[0].setText(null);
			west.tf[1].setText(null);
			west.tf[2].setText(null);
			west.tf[3].setText(null);
			west.tf[4].setText(null);

			// �ֹι�ȣ �Է¶� Ȱ��ȭ ��Ű��
			west.tf[4].setEditable(true);
			west.tf[0].requestFocus();
		} else { // Ȯ��â���� '�ƴϿ�'�� ���� ���
			return;

		}

	}

	private boolean validInput() {
		if (west.tf[0].getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "��ȣ�� �Է��� �ȵǾ����ϴ�", "�Է� ����", JOptionPane.ERROR_MESSAGE);
			west.tf[0].requestFocus();
			return false;
		}
		if (west.tf[1].getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "�̸��� �Է��� �ȵǾ����ϴ�", "�Է� ����", JOptionPane.ERROR_MESSAGE);
			west.tf[1].requestFocus();
			return false;
		}
		if (west.tf[2].getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "�ڵ�����ȣ�� �Է��� �ȵǾ����ϴ�", "�Է� ����", JOptionPane.ERROR_MESSAGE);
			west.tf[2].requestFocus();
			return false;
		}
		if (west.jobs.getSelectedItem().equals("��������")) {
			JOptionPane.showMessageDialog(null, "������ �Է��� �ȵǾ����ϴ�", "�Է� ����", JOptionPane.ERROR_MESSAGE);
			west.tf[5].requestFocus();
			return false;
		}

		// �ڵ�����ȣ �Է°� �˻�
		String hpNum = west.tf[2].getText(); // �ڵ�����ȣ�ʵ尪
		String hpnum_pattern = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$";
		boolean checkHP = hpNum.matches(hpnum_pattern);
		if (checkHP == false) {
			JOptionPane.showMessageDialog(null, "�ڵ��� �Է��� �ٽ� Ȯ�����ּ���", "�ڵ��� �Է¿���", JOptionPane.ERROR_MESSAGE);
			west.tf[2].requestFocus();
			return false;
		}
		// �̸��� �Է°� �˻�
		String email = west.tf[3].getText(); // �̸����ʵ尪
		String email_pattern = "^\\w+(\\.)?\\w+@\\w+\\.\\w+(\\.\\w+)?";
		boolean checkEmail = email.matches(email_pattern);
		if (checkEmail == false) {
			JOptionPane.showMessageDialog(null, "�̸��� �Է��� �ٽ� Ȯ�����ּ���", "�̸��� �Է¿���", JOptionPane.ERROR_MESSAGE);
			west.tf[3].requestFocus();
			return false;
		}
		return true;
	}

	public void searchData() {
		west2.card.next(west2);
		west2.searchRadio[0].setSelected(true);
		buttons.search.setEnabled(false); // �˻���ư ��Ȱ��ȭ
	}

	// ���� ��ư �̺�Ʈó��
	public void nextData() {

		updateRow = updateRow + 1;
		// ��ü���̺��� �హ���� �����ͼ� ���� �������� row���� ��
		if (showtable.table.getRowCount() == updateRow) {
			JOptionPane.showMessageDialog(null, "���̻� ������ ����Ÿ�� �����ϴ�", "��� ���¸޽���", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		west.tf[0].setText((String) showtable.table.getValueAt(updateRow, 0));
		west.tf[1].setText((String) showtable.table.getValueAt(updateRow, 1));
		west.tf[2].setText((String) showtable.table.getValueAt(updateRow, 2));
		west.tf[3].setText((String) showtable.table.getValueAt(updateRow, 3));
		west.tf[4].setText((String) showtable.table.getValueAt(updateRow, 4));
		west.jobs.setSelectedItem((String) showtable.table.getValueAt(updateRow, 9));

		// ��[�߿�] �ֹι�ȣ�� ���� ��Ȱ��ȭ ��Ű��
		west.tf[4].setEditable(false);

		// �� Ŭ���� �Ż������� ������ ��
		west2.ageLabel.setText((String) showtable.table.getValueAt(updateRow, 5));
		west2.sexLabel.setText((String) showtable.table.getValueAt(updateRow, 6));
		west2.chulLabel.setText((String) showtable.table.getValueAt(updateRow, 7));
		west2.birthLabel.setText((String) showtable.table.getValueAt(updateRow, 8));

	}

	// ���� ��ư
	public void backData() {

		updateRow = updateRow - 1;

		if (updateRow < 0) {
			JOptionPane.showMessageDialog(null, "���̻� ������ ����Ÿ�� �����ϴ�", "��� ���¸޽���", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		west.tf[0].setText((String) showtable.table.getValueAt(updateRow, 0));
		west.tf[1].setText((String) showtable.table.getValueAt(updateRow, 1));
		west.tf[2].setText((String) showtable.table.getValueAt(updateRow, 2));
		west.tf[3].setText((String) showtable.table.getValueAt(updateRow, 3));
		west.tf[4].setText((String) showtable.table.getValueAt(updateRow, 4));
		west.jobs.setSelectedItem((String) showtable.table.getValueAt(updateRow, 9));

		// ��[�߿�] �ֹι�ȣ�� ���� ��Ȱ��ȭ ��Ű��
		west.tf[4].setEditable(false);

		// �� Ŭ���� �Ż������� ������ ��
		west2.ageLabel.setText((String) showtable.table.getValueAt(updateRow, 5));
		west2.sexLabel.setText((String) showtable.table.getValueAt(updateRow, 6));
		west2.chulLabel.setText((String) showtable.table.getValueAt(updateRow, 7));
		west2.birthLabel.setText((String) showtable.table.getValueAt(updateRow, 8));

	}

	public void info() { // ���� ����
		JOptionPane.showMessageDialog(null, "TEST", "����", JOptionPane.INFORMATION_MESSAGE);

	}

	public void fnew() { //���� �����
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
//================================================================================================
	public static void main(String[] args) {
		new CustomerSystem();
	}
}
