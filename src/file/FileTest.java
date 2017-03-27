/*
 * java���� ���丮�� �����ϱ� ���� Ŭ������ ������ �������� �ʰ�,���丮�� ���Ϸ� ����
 * ���	java.io.File Ŭ������ ����+���丮���� ó��
 */
package file;

import java.io.File;

public class FileTest {
	public static void main(String[] args) {
		// C����̺� ������ �����ϴ� ��� ���丮 �� ������ ���
		// File(String pathname)
		File file=new File("C:/");
		
		// ���� ���丮 �� ������ ��� ���
		// list() 
		// Returns an array of strings naming the files and directories in the directory denoted by this abstract pathname.
		File[] dir=file.listFiles();	// ���丮 ���� ��ü(Ŭ����)�� �迭�� ����
										// ���� �ϳ��ϳ��� ��Ī�Ǵ� ��ü
		// String[] dir=file.list();	// ���丮�� ��ܸ� ����
		for(int i=0; i<dir.length; i++){
			/*
			// ���丮�� ���
			if(dir[i].isDirectory()){
				System.out.println(dir[i]);
			}
			*/
			// ���ϸ� ���
			if(dir[i].isFile()){
				System.out.println(dir[i]);
			}
		}
	}
}
