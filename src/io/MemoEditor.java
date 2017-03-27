/*
 * �޸��� �����
 * open	save	��ư	Panel
 * textArea		JScrollPane
 * 700*600
 * 
 * ���� ��� ��Ʈ��
 * : �� �پ� �о����, ������ ������ ������ ����
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
	ImageIcon icon;	// �Ϲ� Ŭ����
	
	// ���� ��ư ������ �����ǵ���
	FileInputStream fis;
	FileOutputStream fos;
	
	InputStreamReader reader;	// ���� ��� �Է� ��Ʈ��
	OutputStreamWriter writer;	// ���� ����� ��� ��Ʈ��
	
	BufferedReader buffr;		// ���۷� ó���� ���� ��� �Է� ��Ʈ��
	BufferedWriter buffw;		// ���۷� ó���� ���� ��� ��� ��Ʈ��
	
	PrintWriter writer2;		// ���� ����� ��� ��Ʈ��(System.out�� ��ü), �ѱ��� ������ ����غ�
	
	String ori="C:/Users/user/Desktop/SIST(17.02.13-17.07.21)/java_workspace2/project0327/res/memo.txt";		// ����
	String dest="C:/Users/user/Desktop/SIST(17.02.13-17.07.21)/java_workspace2/project0327/res/memo_copy.txt";	// �ٸ� ������ ���� ���
	
	public MemoEditor() {
		p_north=new JPanel();
		// JButton(Icon icon)�� �̿��ؼ� ���� �̹��� �ֱ�
		// Icon�� �������̽��̹Ƿ� All Known Implementing Classes: (�ڽ� Ŭ����)ImageIcon�� �̿�
		// ImageIcon(String filename) 
		icon=new ImageIcon("C:/java_workspace2/project0327/res/file_on.png");
		bt_open=new JButton(icon);
		bt_save=new JButton("����");
		area=new JTextArea();
		scroll=new JScrollPane(area);
		
		p_north.add(bt_open);
		p_north.add(bt_save);
		
		bt_open.setBorderPainted(false);	// �̹��� �׵θ� ����
		bt_open.setContentAreaFilled(false);// �̹����� ä���� ��� ����
		bt_open.setFocusPainted(false);		// focusing ���� 
		bt_open.setOpaque(false);			// 
		
		add(p_north,BorderLayout.NORTH);
		add(scroll);
		
		// ��ư 2�� ���� �͸����� ����
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
	
	// ����
	public void open(){
		try {
			fis=new FileInputStream(ori);
			// ���� ��� ��Ʈ������ ������ �ڵ� �Ӽ��� ���� ����
			reader=new InputStreamReader(fis,"utf-8");
			// BufferedReader(Reader in)
			// InputStreamReader extends reader�̹Ƿ� �μ��� reader �ֱ�
			// ���� FileInputStream, InputStreamReader, BufferedReader���� �� 3�� ������
			buffr=new BufferedReader(reader);
			
			//int data;
			String data;
			int count=0;	//read() Ƚ���� �˱� ���� ����
			
			// ���ڼ���ŭ read()�޼ҵ� �����
			while(true){
				//data=reader.read();	// 2 byte �� �о����
				data=buffr.readLine();
				/*
				if(data==-1){
					break;
				}
				*/
				// String�� ��ü�̱� ������ ���� ���� null�� ��� �б� ����
				if(data==null)	break;
				count++;
				//area.append(Character.toString((char)data));
				// ���� ���� ��� reader�� data�� append�ϸ� �ٹٲ��� �Ͼ�� ����
				// ����, ���� �а� �ٹٲ� �߰����ֱ�!
				// char������ ���� ���� �о���Ƿ� enter�� \n�� \r�� �ν��Ͽ� int�� ��� ����������
				// String���� �ҷ��� ��� enter �ν� �Ұ�
				area.append(data+"\n");
				System.out.println("read() Ƚ�� : "+count);
			}
			
			
		} catch (FileNotFoundException e) {
			//  ����ڸ� ���� ���� �߻� �޽��� ǥ��
			JOptionPane.showMessageDialog(this, "������ �������� �ʽ��ϴ�");
			// ������ �� ��Ȳ�� stack���� �Ʒ����� ���� ������ �ֽ� ����
			// �����ڸ� ���� ���� ���� ǥ��
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// ����
	public void save(){
		try {
			// FileOutputStream�� ������ ����� ������ ������ ����
			// (ũ�� : 0byte�� empty, �� ����)
			// ��ο� ���� �̸��� ������ �Ȱ��� �����ϸ� �� ���Ϸ� �����ع���
			fos=new FileOutputStream(dest);
			//writer=new OutputStreamWriter(fos, "utf-8");
			//buffw=new BufferedWriter(writer);
			
			// PrintWriter(OutputStream out), fos�� �μ��� �ֱ� 
			writer2=new PrintWriter(fos);
			
			// ������ stream�� ���� ������ ����� write ���� ���� �� �����Ƿ� ��Ʈ���� �ݾƾ� ��
			// writer.write(area.getText());
			writer2.write(area.getText());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}/* PrintWriter�� �ϸ� �� catch�� �ʿ�X
		catch (UnsupportedEncodingException e) {
			JOptionPane.showMessageDialog(this, "�߸��� ���ڵ� ����Դϴ�.");
			e.printStackTrace();
		}*/
		catch (IOException e) {
			JOptionPane.showMessageDialog(this, "IO ������ ������ �߻��߽��ϴ�.");
			e.printStackTrace();
		} finally{
			// fos�� writer�� �� �ϳ��� �ݾƵ� ������
			// ��������� �ϳ��� �ݴ°� FM
			// ������ ��Ʈ�� ���� �ݴ� ó��
			/*
			if(writer!=null){	// writer ��Ʈ���� �����ϸ� �ݱ�
				
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			*/
			
			//PrintWriter�� try-catch�� �ʿ�X
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
