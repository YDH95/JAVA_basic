package 고객관리시스템교수님예시;

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
 * | 고객관리 시스템(열기, 저장, 이름정렬, 추가, 삭제 기능구현) | <<개정이력(Modification Information)>> |
 * 수정일 수정자 수정내용 |
 * ----------------------------------------------------------------------| 예)
 * 2021.04.08 윤모씨 열기,저장, 추가 기능구현 | 파일 열기 불러오기 기능 구현 |
 *************************************************************************/
//내부 클래스(inner class)
public class CustomerSystem extends JFrame { // 외부 클래스
	// 이미지 삽입 코드 상수
	public static final String String = null;

	// 내부 클래스 객체 생성 => 객체들을 전역으로 선언

	Menumain menumain = new Menumain(); // 메뉴
	West west = new West(); // 정보 입력
	West2 west2 = new West2(); // 정보 확인 및 검색
	ShowTable showtable = new ShowTable(); // 입력 확인
	Buttons buttons = new Buttons(); // 버튼
	BorderLayout borderLayout = new BorderLayout();
	JPanel westPanel = new JPanel();

	int updateRow; // '수정' 버튼에서 이벤트 발생 시 사용할 전역변수
//==============================================================================
	// 생성자 => 외부 클래스

	public CustomerSystem() {
		// 이미지 삽입 추가
		OUTTER: while (true) {
			// jpg파일 인트로 화면 띄우기
			ImageIcon icon = new ImageIcon("image/gettyimages740x490.jpg");
//			Image icon2 = icon.getImage();
//			Image icon3 = icon2.getScaledInstance(700, 600, java.awt.Image.SCALE_AREA_AVERAGING);
//			ImageIcon icon4 = new ImageIcon(icon3);
			JOptionPane.showMessageDialog(null, null, "고객관리 정보 시스템", JOptionPane.NO_OPTION, icon);

			// 패스워드 창 띄우기
			String password = JOptionPane.showInputDialog(null, "고객관리 시스템" + "\n" + "패스워드 입력", "비밀번호입력",
					JOptionPane.QUESTION_MESSAGE);
			String passwd = "0000";

			if (password == null) {
				break OUTTER;
			} else if (password.equals(passwd)) {

				setTitle("고객관리 시스템");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				add(menumain.mb, BorderLayout.NORTH);
				add(buttons, BorderLayout.SOUTH);
				add(westPanel, BorderLayout.WEST);
				add(showtable.scroll, BorderLayout.CENTER);
				// BorderLayout borderLayout = new BorderLayout();
				westPanel.setLayout(borderLayout); // borderLayout에 panel를 넣음
				westPanel.add(west); // 패널에 west를 넣음
				westPanel.add(west2); // 패널에 west2를 넣음
				borderLayout.addLayoutComponent(west, BorderLayout.CENTER);
				borderLayout.addLayoutComponent(west2, BorderLayout.SOUTH);

				// window사이즈 설정
				setSize(1000, 700);
				setLocation(500, 30);
				setVisible(true);
				break OUTTER;
			} else {
				JOptionPane.showMessageDialog(null, " 패스워드가 일치하지 않습니다." + "\n" + "'확인' 버튼을 누르세요.", "패스워드 인증실패",
						JOptionPane.ERROR_MESSAGE);

				break;
			}
		}
	}

//================================================================================================
	// 내부클래스 구현 => 메뉴 쪽을 담당하는 클래스
	class Menumain extends JPanel implements ActionListener, ItemListener {

		JMenuBar mb;
		JMenu file, sort, help;
		JMenuItem fnew, fopen, fsave, fexit, proinfo;
		JCheckBoxMenuItem sname, sNum, schulloc, sbirth;
		FileDialog readOpen, saveOpen;
		String fileDir, fileName, saveFileName, readFileName;

		// 생성자 => 내부클래스 것
		public Menumain() {
			mb = new JMenuBar();
			file = new JMenu("파일");
			sort = new JMenu("정렬");
			help = new JMenu("도움말");

			
			fnew = new JMenuItem("새로 만들기");
			fopen = new JMenuItem("열기");
			fsave = new JMenuItem("저장");
			fexit = new JMenuItem("닫기");

			sNum = new JCheckBoxMenuItem("번호");
			sname = new JCheckBoxMenuItem("이름");
			schulloc = new JCheckBoxMenuItem("출생지역");
			sbirth = new JCheckBoxMenuItem("생일");

			proinfo = new JMenuItem("프로그램 정보");

			file.add(fnew);
			file.add(fopen);
			file.add(fsave);
			file.addSeparator(); // 실선
			file.add(fexit);
			sort.add(sNum);
			sort.add(sname);
			sort.add(schulloc);
			sort.add(sbirth);
			help.add(proinfo);

			mb.add(file);
			mb.add(sort);
			mb.add(help);

			// 이벤트 연결
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
			if (e.getActionCommand().equals("새로 만들기"))
				fnew();
			if (e.getActionCommand().equals("저장"))
				save();
			if (e.getActionCommand().equals("열기"))
				open();
			if (e.getActionCommand().equals("닫기"))
				exit();
			if (e.getActionCommand().equals("프로그램 정보"))
				info();

		}// end actionPerformed



//===============================파일 저장=================================================================

		public void save() {
			saveOpen = new FileDialog(CustomerSystem.this, "문서저장", FileDialog.SAVE);
			saveOpen.setVisible(true);
			// C:\ 밑에 '고객관리시스템데이터파일' 폴더생성
			// 고객관리시스템데이터.txt 로 저장
			fileDir = saveOpen.getDirectory();
			fileName = saveOpen.getFile();
			saveFileName = fileDir + "//" + fileName;

			String str = "";
			String temp = "";

			try {
				BufferedWriter save = new BufferedWriter(new FileWriter(saveFileName)); // 저장 경로 설정
				// [중요 로직]
				for (int i = 0; i < showtable.table.getRowCount(); i++) { // table에 있는 내용을 다 가져옴 이 름, 핸드폰 번호, 주민번호
					temp = showtable.data.elementAt(i).toString();
					str += temp.substring(1, temp.length() - 1) + "\n";
					// 1번째 index부터 temp의 길이 끝에서 -1, vector [] 없에기 [홍길동,010,950604] 괄호도 index임
				}
				save.write(str);
				save.close();
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}

//==============================저장한 파일 열기==================================================================
		public void open() {
			StringTokenizer st;
			Vector<String> vec;

			readOpen = new FileDialog(CustomerSystem.this, "문서열기", FileDialog.LOAD);
			readOpen.setVisible(true);

			fileDir = readOpen.getDirectory();
			fileName = readOpen.getFile();
			readFileName = fileDir + "//" + fileName;

			try {
				BufferedReader read = new BufferedReader(new FileReader(readFileName));
				// JTable에 출력된 2차원Vector값을 초기화시킨후 가져옴
				showtable.data.removeAllElements();

				String line = null;
				// 열기 로직
				while ((line = read.readLine()) != null) {
					st = new StringTokenizer(line, ", ");
					vec = new Vector<String>();

					while (st.hasMoreTokens()) {
						vec.add(st.nextToken());
					}
					showtable.data.addElement(vec);
				}
				showtable.datamodel.fireTableDataChanged(); // fireTableDataChanged() 메소드를 호출

			} catch (IOException e) {
				System.out.println(e);
			}
		}

//===========================나가기 만들기============================================================
		public void exit() {
			System.exit(0);
		}

//================================================================================================
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource().equals(sNum)) // 번호
				sortName(0); // 번호정렬
			if (e.getSource().equals(sname)) // 이름
				sortName(1); // 이름정렬
			if (e.getSource().equals(schulloc)) // 출생 지역
				sortName(7); // 출생지역정렬
			if (e.getSource().equals(sbirth)) // 생일
				sortName(8); // 생일정렬
		}

		public void sortName(int sort) {
			int row = showtable.table.getRowCount();
			int col = showtable.table.getColumnCount();

			String[][] arr = new String[row][col];
			String temp;
			// JTable의 데이터들을 arr[][] 2차원 배열로 옮기기
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					arr[i][j] = (String) showtable.table.getValueAt(i, j);
				}
			}
			// 2차원 배열 => 선택 정렬 알고리즘 적용
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
			// 2차원 배열의 데이터들을 JTable에 옮기기
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					showtable.table.setValueAt(arr[i][j], i, j);
				}
			}
		}
	}

//================================================================================================
// 내부 클래스 구현 => 데이터 입력, 신상정보를 담당하는 클래스
	class West extends JPanel {
		// 멤버 변수 선언
		JLabel la;
		JTextField tf[]; // JTextField 컴퍼넌트 객체들의 주고값을저장할 배열
		// 생성자 => 내부클래스 것
		JComboBox<String> jobs;

		public West() {
			// [입력] 보더 만들기
			LineBorder line = new LineBorder(Color.BLUE, 1);// 라인의 두께
			setBorder(new TitledBorder(line, "입력"));

			String[] text = { "번호", "이 름", "핸드폰 번호", "이메일", "주민번호", "직업" };
			tf = new JTextField[7];
			setLayout(new GridLayout(8, 2, 5, 10));

			String[] job = { "직업선택", "회사원", "주부", "학생", "무직", "기타" };

			jobs = new JComboBox<String>(job);

			for (int i = 0; i < text.length; i++) {
				la = new JLabel(text[i]);
				tf[i] = new JTextField();
				la.setHorizontalAlignment(JLabel.CENTER);
				tf[i].setHorizontalAlignment(JLabel.CENTER);

				add(la);
				add(tf[i]);

			}
//			add(new JLabel("                      직업"));
			add(jobs);
			setPreferredSize(new Dimension(230, 300));
		}
	}

//===============================================================================
	class West2 extends JPanel implements ActionListener {
		JPanel p = new JPanel(); // 정보검색패널
		JPanel search = new JPanel();
		CardLayout card;
		JRadioButton[] searchRadio = new JRadioButton[4];
		JLabel ageLabel = new JLabel();
		JLabel sexLabel = new JLabel();
		JLabel chulLabel = new JLabel();
		JLabel birthLabel = new JLabel();
		JTextField jf = new JTextField(10);

		// 생성자
		public West2() {
			card = new CardLayout();
			setLayout(card);
			// 신상정보카드
			p.setBorder(new TitledBorder(new LineBorder(Color.BLUE, 1), "신상정보"));
			p.setLayout(new GridLayout(4, 2, 10, 5));

			p.add(new JLabel("나이", JLabel.LEFT));
			p.add(ageLabel);

			p.add(new JLabel("성별", JLabel.LEFT));
			p.add(sexLabel);

			p.add(new JLabel("출생지", JLabel.LEFT));
			p.add(chulLabel);

			p.add(new JLabel("생일", JLabel.LEFT));
			p.add(birthLabel);

			p.setPreferredSize(new Dimension(260, 210));

			add(p, "첫번째 카드");

			search.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 2), "검색"));

			ButtonGroup group = new ButtonGroup();

			String[] search_name = { "이름", "직업", "출생지역", "생일 월일" };
			JButton search_btn = new JButton("찾기");
			JButton exit_btn = new JButton("나가기");

			// 사용자 자유배치 laout하는법
			search.setLayout(null);
			int x = -70;
			// 앞카드와 크기가 똑같아야 교환할 때 바뀌는 느낌을 줌
			search.setPreferredSize(new Dimension(350, 210));

			for (int i = 0; i < searchRadio.length; i++) {
				searchRadio[i] = new JRadioButton(search_name[i]);
				// 반복문 돌릴 때, x값이 변경되면서 searchRadio 버튼위치를 조정해주기위해 밖에 -70을 선언해준것
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

			add(search, "두번째 카드");

			search_btn.addActionListener(this);
			exit_btn.addActionListener(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("찾기"))
				search_btn();
			if (e.getActionCommand().equals("나가기"))
				exit_btn();

		}

		// 나가기 버튼이벤트 매서드
		public void exit_btn() {
			// 카드레이아웃변경
			card.next(west2);
			// JTable에 원래정보 출력
			showtable.datamodel.setDataVector(showtable.data, showtable.colum_name);
			// 검색버튼활성화
			buttons.search.setEnabled(true);
			// 신상정보 초기화
			if (west.tf[0].getText().trim().length() == 0) {
				ageLabel.setText(null);
				sexLabel.setText(null);
				chulLabel.setText(null);
				birthLabel.setText(null);

			}

		}

		public void search_btn() {
			if (jf.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "검색란에 입력해주세요", "경고메시지", JOptionPane.ERROR_MESSAGE);
				jf.requestFocus(true);
			} else {
				// JTable에서 검색된 2차배열을 저장하여 뿌려주기 위해 임시벡터 사용
				Vector<Vector<String>> findData = new Vector<Vector<String>>();

				// 선택한 라디오박스로 JTable의 검색할 열의 인덱스값 초기화
				int num = 0;
				if (searchRadio[0].isSelected())
					num = 1; // 이름
				if (searchRadio[1].isSelected())
					num = 9; // 직업
				if (searchRadio[2].isSelected())
					num = 7; // 출생지역
				if (searchRadio[3].isSelected())
					num = 8; // 생일

				for (int i = 0; i < showtable.data.size(); i++) {
					// 2차원 Vector인 data에서 행중 해당 열중 입력값이 포함되어 있는지 체크한다
					if (showtable.data.elementAt(i).get(num).contains(jf.getText().trim())) {
						// 임시 2차원 백터에 추가
						findData.addElement(showtable.data.elementAt(i));
					}
				}
				// 벡터에 아무것도 없다면 (검색된 값이 없을경우)
				if (findData.isEmpty()) {
					JOptionPane.showMessageDialog(null, "검색하신 내용이 없습니다", "경고메시지", JOptionPane.ERROR_MESSAGE);
					// showtable.datamodel.setDataVector(showtable.data, showtable.columnNames);
					jf.setText(null);
				} else {
					// JTable datamodel에 2차원 백터넣기
					// 컬럼 벡터와 데이터 벡터 합치기
					showtable.datamodel.setDataVector(findData, showtable.colum_name);
					showtable.datamodel.fireTableDataChanged();
				}
			}

		}
	}

//================================================================================================
// 내부 클래스 구현 => JTable에 입력한 정보를 보여주는 클래스
	class ShowTable extends MouseAdapter {// MouseAdapter클래스 상속
		DefaultTableModel datamodel; // 추가, 수정, 삭제 기능 추가
		JTable table;
		JScrollPane scroll;

		String[] colName = { "번호", "이 름", "핸드폰번호", "이메일", "주민번호", "나이", "성별", "출생지역", "생일", "직업" }; // 9

		// [중요]
		// DefaultTableModel 클래스의 생성자
		// DefaultTableModel(Vector data,Vector columnNames)
		// 자료 구조
		Vector<Vector<String>> data;
		Vector<String> colum_name;

		// 생성자 => 내부클래스 것
		public ShowTable() {
			data = new Vector<Vector<String>>(); // 기본 10개의 가변배열 객체생성
			colum_name = new Vector<String>(); // 기본 10개의 가변배열 객체생성

			for (int i = 0; i < colName.length; i++) {
				colum_name.add(colName[i]);
			}
//			[1단계] DefaultTableModel 결정
//			[중요]
			datamodel = new DefaultTableModel(data, colum_name); // 추가, 수정, 삭제 기능 삽입

//			[2단계]
			table = new JTable(datamodel);

//			[3단계]
			scroll = new JScrollPane(table);

			// 셀의 크기 조절
			table.getColumnModel().getColumn(0).setPreferredWidth(50); // 번호
			table.getColumnModel().getColumn(1).setPreferredWidth(50); // 이름
			table.getColumnModel().getColumn(2).setPreferredWidth(50); // 핸드폰번호
			table.getColumnModel().getColumn(3).setPreferredWidth(50); // 이메일
			table.getColumnModel().getColumn(4).setPreferredWidth(50); // 주민번호
			table.getColumnModel().getColumn(5).setPreferredWidth(50); // 직업

			// 셀의 데이터를 가운데 정렬 시키기
			DefaultTableCellRenderer tableCell = new DefaultTableCellRenderer(); // 글씨 중앙 정렬
			tableCell.setHorizontalAlignment(SwingConstants.CENTER);
			TableColumnModel tb = table.getColumnModel();
			for (int i = 0; i < tb.getColumnCount(); i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(tableCell);
			}
			// 이벤트연결
			table.addMouseListener(this);
		}

//================================================================================================
		@Override
		public void mouseClicked(MouseEvent e) { // MouseAdapter에 내장되있는 mouseClicked메서드를 불러옴
			updateRow = table.getSelectedRow();// 선택한 열의 index값을 리턴
			System.out.println("updateRow" + updateRow);
			west.tf[0].setText((String) showtable.table.getValueAt(updateRow, 0));
			west.tf[1].setText((String) showtable.table.getValueAt(updateRow, 1));
			west.tf[2].setText((String) showtable.table.getValueAt(updateRow, 2));
			west.tf[3].setText((String) showtable.table.getValueAt(updateRow, 3));
			west.tf[4].setText((String) showtable.table.getValueAt(updateRow, 4));
			west.jobs.setSelectedItem((String) showtable.table.getValueAt(updateRow, 9));
			// ※[중요] 주민번호는 수정 비활성화 시키자
			west.tf[4].setEditable(false);

			// 행 클릭시 신상정보에 정보가 뜸
			west2.ageLabel.setText((String) showtable.table.getValueAt(updateRow, 5));
			west2.sexLabel.setText((String) showtable.table.getValueAt(updateRow, 6));
			west2.chulLabel.setText((String) showtable.table.getValueAt(updateRow, 7));
			west2.birthLabel.setText((String) showtable.table.getValueAt(updateRow, 8));
		}
	}

//================================================================================================
// 내부 클래스 구현 => 하단에 버튼들 기능 처리 담당 클래스
	class Buttons extends JPanel implements ActionListener {
		// 멤버 변수 선언
		Vector<String> vector;
		JButton addBtn, updateBtn, delBtn, back, next, search;
		String juminNo;

		// 생성자 => 내부클래스 것
		public Buttons() {
			setLayout(new GridLayout(1, 6, 5, 0));
//			setLayout(new FlowLayout(3));
			addBtn = new JButton("추가");
			delBtn = new JButton("삭제");
			back = new JButton("이전");
			updateBtn = new JButton("수정");
			next = new JButton("다음");
			search = new JButton("검색");

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

			// 이벤트 연결
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
			if (e.getActionCommand().equals("추가"))
				addData(); // 사용자 정의 메소드 호출
			if (e.getActionCommand().equals("수정"))
				updateData(); // 사용자 정의 메소드 호출
			if (e.getActionCommand().equals("삭제"))
				deleteData(); // 사용자 정의 메소드 호출
			if (e.getActionCommand().equals("다음"))
				nextData(); // 사용자 정의 메소드 호출
			if (e.getActionCommand().equals("이전"))
				backData(); // 사용자 정의 메소드 호출
			if (e.getActionCommand().equals("검색"))
				searchData(); // 사용자 정의 메소드 호출

		}// actionPerformed end

		// updateData() 사용자 정의 메소드 구현
		// 사용자 정의 메소드
//================================================================================================

		public void addData() {
			Vector<String> vector = new Vector<String>();
			// 빈값 체크 매서드
			boolean checkInput = validInput();
			if (checkInput == false)
				return;

			vector.add(west.tf[0].getText()); // 번호 입력값 가져옴
			vector.add(west.tf[1].getText()); // 이름 입력값 가져옴
			vector.add(west.tf[2].getText()); // 전화번호 입력값 가져옴
			vector.add(west.tf[3].getText()); // 이메일 입력값 가져옴

			juminNo = west.tf[4].getText(); // 주민번호가져옴
			// 주민번호 유효성체크

			// ===========================================================================
			/**
			 * 입력된 주민번호 유효성 검사 => 정규 표현식 패턴 적용
			 */
			String regex = "^[0-9]{6}-[1234][0-9]{6}$";
			// 2가지 방법
			boolean check = juminNo.matches(regex);
			if (check == false) {
				JOptionPane.showMessageDialog(null, "주민번호가정규표현식에 맞지않음", "경고메세지", JOptionPane.ERROR_MESSAGE);
				west.tf[4].setText(null);
				west.tf[4].requestFocus();
				return; // 상태유지
			}

			int sum = 0; // 누계변수는 반드시 0으로 초기화
			int[] weight = { 2, 3, 4, 5, 6, 7, 0, 8, 9, 2, 3, 4, 5 };
			String[][] localeCode = { { "서울", "00", "08" }, { "부산", "09", "12" }, { "인천", "13", "15" },
					{ "경기", "16", "25" }, { "강원", "26", "34" }, { "충북", "35", "39" }, { "대전", "40", "41" },
					{ "충남", "42", "47" }, { "충남", "42", "47" }, { "세종", "44", "44" }, { "세종", "96", "96" },
					{ "전북", "48", "45" }, { "전남", "55", "64" }, { "광주", "65", "66" }, { "대구", "67", "70" },
					{ "경북", "71", "80" }, { "경남", "81", "84" }, { "경남", "86", "90" }, { "울산", "85", "85" },
					{ "제주", "91", "95" } };

			// ===========================================================================
			// 처리단계 => logic => 제어문을 활용
			for (int i = 0; i < 13; i++) {
				if (juminNo.charAt(i) == '-') {
					// continue;
				} else {
					sum += (juminNo.charAt(i) - '0') * weight[i];
				}
			}
			if (11 - (sum % 11) == juminNo.charAt(13) - '0') {
				System.out.println("확인되었습니다");
			} else {
				System.out.println("주민번호를 다시 확인해주세요");
			}

			vector.add(west.tf[4].getText()); // 주민번호저장

			// ===========================================================================
			Calendar cal = Calendar.getInstance(Locale.KOREA); // 한국 시간 가져오기
			int year = cal.get(Calendar.YEAR); // 한국 연도만 가져와서 변수에넣기
			String myyear = juminNo.substring(0, 2); // substring으로 입력한 주민번호 연도만 빼오기
			int myold = Integer.parseInt(myyear); // 연도 문자열을 정수로변환
			// String myyear = Integer.parseInt(juminNum.substring(0, 2)); //이렇게하면 변수두개를 사용할
			// 필요없음
			int gendercode = Integer.parseInt(juminNo.substring(7, 8));// substring(시작주소, 시작주소+가져올갯수);
			if (gendercode == 1 || gendercode == 2) { // 만약 입력한 주민 7번째 숫자가 1이거나 2면
				myold = myold + 1900;
			} else if (gendercode == 3 || gendercode == 4) { // 만약 입력한 주민 7번째 숫자가 3이거나 4면
				myold = myold + 2000;
			}
			int age = year - myold + 1;
			String a = Integer.toString(age);
			vector.add(a);
			// ===========================================================================
			// '성별' 추출
			if ((juminNo.charAt(7) - '0') % 2 == 0) { // 2,4인경우 여자 짝수는 여자
				vector.add("여자");
			} else { // 1, 3 인경우 홀수는 남자
				vector.add("남자");
			}
			// ===========================================================================
			// 출생지역 계산
			String localeStinrg = juminNo.substring(8, 10); // 입력한 주민번호 8,9번째 넣음
			int locale = Integer.parseInt(localeStinrg);
			String birthPlace = "";
			// 123456-1251233
			for (int j = 0; j < localeCode.length; j++) {
				if (locale >= Integer.parseInt(localeCode[j][1]) && (locale <= Integer.parseInt(localeCode[j][2]))) {
					birthPlace = localeCode[j][0];
				}
			}
			System.out.println("주소" + birthPlace);
			vector.add(birthPlace);
//			// '생년월일' 추출
			vector.add(myold + "/" + juminNo.substring(2, 4) + "/" + juminNo.substring(4, 6));
			if (west.jobs.getSelectedItem().equals("기타")) {
				vector.add(west.tf[5].getText());
			} else {
				vector.add((String) west.jobs.getSelectedItem());
			}
			/*
			 * 입력받은 주민번호는 유효성 체크와 공식을 적용하며 체크한 후 정상적인 주민번호일때만 Vector 객체에 저장한다.
			 */

			west.tf[0].setText(null);
			west.tf[1].setText(null);
			west.tf[2].setText(null);
			west.tf[3].setText(null);
			west.tf[4].setText(null);
			west.tf[0].requestFocus();
			showtable.data.addElement(vector);
			// 데이터가 변경된 후 변경 사항을 JTable에 적용하려면
			// fireTableDataChanged() 메소드를 호출해야한다.
			showtable.datamodel.fireTableDataChanged();

		} // end add.data

	}

//================================================================================================
	public void updateData() {

		if (showtable.table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(null, "수정할 항목이 선택해주시거나 입력란에 내용을 입력해주세요!", "경고 메세지",
					JOptionPane.ERROR_MESSAGE);
			west.tf[0].requestFocus();
			return;
		}

		// 빈값 체크 매서드
		boolean checkInput = validInput();
		if (checkInput == false)
			return;

		// 수정 후 = table 값 변경하기
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

		// 주민번호 입력란 활성화 시키자
		west.tf[4].setEditable(true);
		west.tf[0].requestFocus();
		// 선택한 Row값을 제거
		showtable.table.removeRowSelectionInterval(0, updateRow);
	}

//================================================================================================
	// deletData()메소드 구현
	private void deleteData() {
		// JTable tb = showtable.table;

		int deleteRow = showtable.table.getSelectedRow();
		System.out.println("updateRow값" + updateRow);
		if (deleteRow == -1) { // 사용자가 행을 선택하지 않고 '삭제' 버튼을 누른경우
			JOptionPane.showMessageDialog(null, "선택하신후 삭제버튼을 실행해주세요", "선택 오류 메세지", JOptionPane.ERROR_MESSAGE);
			return;
		}
		int yes_no_select = JOptionPane.showConfirmDialog(null, "정말 삭제 하시겠습니다까?", "고객 데이터 삭제",
				JOptionPane.YES_NO_OPTION);
		if (yes_no_select == JOptionPane.YES_OPTION) {
			// DefaultTableModel model = (DefaultTableModel) tb.getModel();
			showtable.datamodel.removeRow(deleteRow);

			west.tf[0].setText(null);
			west.tf[1].setText(null);
			west.tf[2].setText(null);
			west.tf[3].setText(null);
			west.tf[4].setText(null);

			// 주민번호 입력란 활성화 시키자
			west.tf[4].setEditable(true);
			west.tf[0].requestFocus();
		} else { // 확인창에서 '아니오'를 누른 경우
			return;

		}

	}

	private boolean validInput() {
		if (west.tf[0].getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "번호가 입력이 안되었습니다", "입력 오류", JOptionPane.ERROR_MESSAGE);
			west.tf[0].requestFocus();
			return false;
		}
		if (west.tf[1].getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "이름이 입력이 안되었습니다", "입력 오류", JOptionPane.ERROR_MESSAGE);
			west.tf[1].requestFocus();
			return false;
		}
		if (west.tf[2].getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "핸드폰번호가 입력이 안되었습니다", "입력 오류", JOptionPane.ERROR_MESSAGE);
			west.tf[2].requestFocus();
			return false;
		}
		if (west.jobs.getSelectedItem().equals("직업선택")) {
			JOptionPane.showMessageDialog(null, "직업이 입력이 안되었습니다", "입력 오류", JOptionPane.ERROR_MESSAGE);
			west.tf[5].requestFocus();
			return false;
		}

		// 핸드폰번호 입력값 검사
		String hpNum = west.tf[2].getText(); // 핸드폰번호필드값
		String hpnum_pattern = "^[0-9]{3}-[0-9]{4}-[0-9]{4}$";
		boolean checkHP = hpNum.matches(hpnum_pattern);
		if (checkHP == false) {
			JOptionPane.showMessageDialog(null, "핸드폰 입력을 다시 확인해주세요", "핸드폰 입력오류", JOptionPane.ERROR_MESSAGE);
			west.tf[2].requestFocus();
			return false;
		}
		// 이메일 입력값 검사
		String email = west.tf[3].getText(); // 이메일필드값
		String email_pattern = "^\\w+(\\.)?\\w+@\\w+\\.\\w+(\\.\\w+)?";
		boolean checkEmail = email.matches(email_pattern);
		if (checkEmail == false) {
			JOptionPane.showMessageDialog(null, "이메일 입력을 다시 확인해주세요", "이메일 입력오류", JOptionPane.ERROR_MESSAGE);
			west.tf[3].requestFocus();
			return false;
		}
		return true;
	}

	public void searchData() {
		west2.card.next(west2);
		west2.searchRadio[0].setSelected(true);
		buttons.search.setEnabled(false); // 검색버튼 비활성화
	}

	// 다음 버튼 이벤트처리
	public void nextData() {

		updateRow = updateRow + 1;
		// 전체테이블의 행갯수를 가져와서 현재 진행중인 row값과 비교
		if (showtable.table.getRowCount() == updateRow) {
			JOptionPane.showMessageDialog(null, "더이상 보여줄 데이타가 없습니다", "출력 상태메시지", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		west.tf[0].setText((String) showtable.table.getValueAt(updateRow, 0));
		west.tf[1].setText((String) showtable.table.getValueAt(updateRow, 1));
		west.tf[2].setText((String) showtable.table.getValueAt(updateRow, 2));
		west.tf[3].setText((String) showtable.table.getValueAt(updateRow, 3));
		west.tf[4].setText((String) showtable.table.getValueAt(updateRow, 4));
		west.jobs.setSelectedItem((String) showtable.table.getValueAt(updateRow, 9));

		// ※[중요] 주민번호는 수정 비활성화 시키자
		west.tf[4].setEditable(false);

		// 행 클릭시 신상정보에 정보가 뜸
		west2.ageLabel.setText((String) showtable.table.getValueAt(updateRow, 5));
		west2.sexLabel.setText((String) showtable.table.getValueAt(updateRow, 6));
		west2.chulLabel.setText((String) showtable.table.getValueAt(updateRow, 7));
		west2.birthLabel.setText((String) showtable.table.getValueAt(updateRow, 8));

	}

	// 이전 버튼
	public void backData() {

		updateRow = updateRow - 1;

		if (updateRow < 0) {
			JOptionPane.showMessageDialog(null, "더이상 보여줄 데이타가 없습니다", "출력 상태메시지", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		west.tf[0].setText((String) showtable.table.getValueAt(updateRow, 0));
		west.tf[1].setText((String) showtable.table.getValueAt(updateRow, 1));
		west.tf[2].setText((String) showtable.table.getValueAt(updateRow, 2));
		west.tf[3].setText((String) showtable.table.getValueAt(updateRow, 3));
		west.tf[4].setText((String) showtable.table.getValueAt(updateRow, 4));
		west.jobs.setSelectedItem((String) showtable.table.getValueAt(updateRow, 9));

		// ※[중요] 주민번호는 수정 비활성화 시키자
		west.tf[4].setEditable(false);

		// 행 클릭시 신상정보에 정보가 뜸
		west2.ageLabel.setText((String) showtable.table.getValueAt(updateRow, 5));
		west2.sexLabel.setText((String) showtable.table.getValueAt(updateRow, 6));
		west2.chulLabel.setText((String) showtable.table.getValueAt(updateRow, 7));
		west2.birthLabel.setText((String) showtable.table.getValueAt(updateRow, 8));

	}

	public void info() { // 도움말 정보
		JOptionPane.showMessageDialog(null, "TEST", "정보", JOptionPane.INFORMATION_MESSAGE);

	}

	public void fnew() { //새로 만들기
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
