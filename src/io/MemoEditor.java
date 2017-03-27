/*
 * 메모장 만들기
 * open	save	버튼	Panel
 * textArea		JScrollPane
 * 700*600
 * 
 * 버퍼 기반 스트림
 * : 한 줄씩 읽어들임, 문장이 끝나는 곳까지 읽음
 * */
package io;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MemoEditor extends JFrame{
	JPanel p_north;
	JButton bt_open, bt_save;
	JTextArea area;
	JScrollPane scroll;
	ImageIcon icon;	// 일반 클래스
	
	// 열기 버튼 누르면 생성되도록
	FileInputStream fis;
	FileOutputStream fos;
	
	InputStreamReader reader;	// 문자 기반 입력 스트림
	OutputStreamWriter writer;	// 문자 기반의 출력 스트림
	
	BufferedReader buffr;		// 버퍼로 처리된 문자 기반 입력 스트림
	BufferedWriter buffw;		// 버퍼로 처리된 문자 기반 출력 스트림
	
	PrintWriter writer2;		// 문자 기반의 출력 스트림(System.out내 객체), 한글이 깨져서 사용해봄
	
	String ori="C:/Users/user/Desktop/SIST(17.02.13-17.07.21)/java_workspace2/project0327/res/memo.txt";		// 원본
	String dest="C:/Users/user/Desktop/SIST(17.02.13-17.07.21)/java_workspace2/project0327/res/memo_copy.txt";	// 다른 파일의 저장 경로
	
	public MemoEditor() {
		p_north=new JPanel();
		// JButton(Icon icon)을 이용해서 폴더 이미지 넣기
		// Icon은 인터페이스이므로 All Known Implementing Classes: (자식 클래스)ImageIcon을 이용
		// ImageIcon(String filename) 
		icon=new ImageIcon("C:/java_workspace2/project0327/res/file_on.png");
		bt_open=new JButton(icon);
		bt_save=new JButton("저장");
		area=new JTextArea();
		scroll=new JScrollPane(area);
		
		p_north.add(bt_open);
		p_north.add(bt_save);
		
		bt_open.setBorderPainted(false);	// 이미지 테두리 제거
		bt_open.setContentAreaFilled(false);// 이미지에 채워진 배경 제거
		bt_open.setFocusPainted(false);		// focusing 제거 
		bt_open.setOpaque(false);			// 
		
		add(p_north,BorderLayout.NORTH);
		add(scroll);
		
		// 버튼 2개 내부 익명으로 구현
		bt_open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				open();
			}
		});
		
		bt_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		
		setSize(700,600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// 열기
	public void open(){
		try {
			fis=new FileInputStream(ori);
			// 문자 기반 스트림에는 문자인 코딩 속성을 지정 가능
			reader=new InputStreamReader(fis,"utf-8");
			// BufferedReader(Reader in)
			// InputStreamReader extends reader이므로 인수로 reader 넣기
			// 현재 FileInputStream, InputStreamReader, BufferedReader까지 총 3번 덧씌움
			buffr=new BufferedReader(reader);
			
			//int data;
			String data;
			int count=0;	//read() 횟수를 알기 위한 변수
			
			// 글자수만큼 read()메소드 실행됨
			while(true){
				//data=reader.read();	// 2 byte 씩 읽어들임
				data=buffr.readLine();
				/*
				if(data==-1){
					break;
				}
				*/
				// String은 객체이기 때문에 값이 없는 null인 경우 읽기 종료
				if(data==null)	break;
				count++;
				//area.append(Character.toString((char)data));
				// 현재 버퍼 기반 reader로 data를 append하면 줄바꿈이 일어나지 않음
				// 따라서, 한줄 읽고 줄바꿈 추가해주기!
				// char에서는 한자 한자 읽어오므로 enter의 \n과 \r을 인식하여 int로 출력 가능하지만
				// String으로 불러올 경우 enter 인식 불가
				area.append(data+"\n");
				System.out.println("read() 횟수 : "+count);
			}
			
			
		} catch (FileNotFoundException e) {
			//  사용자를 위한 에러 발생 메시지 표기
			JOptionPane.showMessageDialog(this, "파일이 존재하지 않습니다");
			// 에러가 난 현황을 stack으로 아래에서 위로 갈수록 최신 에러
			// 개발자를 위한 에러 정보 표기
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 저장
	public void save(){
		try {
			// FileOutputStream은 지정한 경로의 파일을 생성해 버림
			// (크기 : 0byte인 empty, 빈 파일)
			// 경로와 파일 이름을 원본과 똑같이 설정하면 빈 파일로 저장해버림
			fos=new FileOutputStream(dest);
			//writer=new OutputStreamWriter(fos, "utf-8");
			//buffw=new BufferedWriter(writer);
			
			// PrintWriter(OutputStream out), fos를 인수로 넣기 
			writer2=new PrintWriter(fos);
			
			// 하지만 stream을 닫지 않으면 제대로 write 되지 않을 수 있으므로 스트림을 닫아야 함
			// writer.write(area.getText());
			writer2.write(area.getText());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}/* PrintWriter로 하면 이 catch문 필요X
		catch (UnsupportedEncodingException e) {
			JOptionPane.showMessageDialog(this, "잘못된 인코딩 방식입니다.");
			e.printStackTrace();
		}*/
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, "IO 과정중 문제가 발생했습니다.");
			e.printStackTrace();
		} finally{
			// fos나 writer둘 중 하나만 닫아도 되지만
			// 명시적으로 하나씩 닫는게 FM
			// 열어진 스트림 전부 닫는 처리
			/*
			if(writer!=null){	// writer 스트림이 존재하면 닫기
				
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			*/
			
			//PrintWriter는 try-catch문 필요X
			if(writer2!=null){
				writer2.close();
			}
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new MemoEditor();
	}

}
