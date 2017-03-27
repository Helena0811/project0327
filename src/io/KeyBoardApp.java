/*
FileInputStream ���� �����ڰ� ���ϴ� ������ ��Ʈ���� ������ �� ������,
Ű����� ���� ǥ�� �Է� �ϵ������ ��쿣 java�� ��Ʈ���� �������� ���ϰ� �ش� os�� �����ϹǷ�,
�����ڴ� os�κ��� ǥ�� �Է� ��Ʈ���� ���;� �Ѵ�.

java�� Ŭ���� �� System Ŭ������ static ������� ��, 
�� has a ����� ������ InputStream�� in ��ü�� �ٷ� ǥ�� �Է� ��Ʈ���� �޾Ƴ��� Ŭ����
����, ��� �ÿ� �׳� System.in;�� ǥ��

���� ��� ��Ʈ�� Ŭ������ �̸� ��Ģ(�ѱ��� ������ ����)
�Է�	~Reader
���	~Writer

*/
package io;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class KeyBoardApp{
	public static void main(String[] args) 
	{
		InputStream is=System.in;

		// ���� ��� ��Ʈ���� �ܵ����� �����ϴ� ���� �ƴ϶�,
		// �̹� ����Ʈ ��� ��Ʈ���� ������ �Ѵ�.
		// ����, ������ �������� �μ��� ����Ʈ ��� ��Ʈ���� �ִ´�
		// ��Ʈ���� �귯���� ��θ� ������� ����
		InputStreamReader reader=null;
		reader=new InputStreamReader(is);
		

		int data;
		try{
			while(true){
				// data=is.read();	// 1byte �о����
				data=reader.read();	// 2byte �о����
				System.out.print(data);
				// reader�� ����ϸ� Unicode ��
				System.out.print((char)data);	
				// 13�� 10�� ����� ����ϴ� Ư�����ڿ� �ش��ϱ� ������ char�� �������� �� ǥ������ ����
			}
			// read()�� �ѹ� ���۵Ǳ� ������ enter�� �ش��ϴ� 13(\n)�� 10(\r)�� ��µ��� ����
			// read()�� ������ ���۽�Ű�� ����� ����
		}catch(IOException e){
			// read() �޼ҵ� ������ IOException ���� �߻� ���ɼ��� �����Ƿ� try-catch ���
		}
	}
}