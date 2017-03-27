/*
 * java에는 디렉토리를 제어하기 위한 클래스가 별도로 존재하지 않고,디렉토리도 파일로 간주
 * 결론	java.io.File 클래스가 파일+디렉토리까지 처리
 */
package file;

import java.io.File;

public class FileTest {
	public static void main(String[] args) {
		// C드라이브 하위에 존재하는 모든 디렉토리 및 파일을 출력
		// File(String pathname)
		File file=new File("C:/");
		
		// 하위 디렉토리 및 파일의 목록 출력
		// list() 
		// Returns an array of strings naming the files and directories in the directory denoted by this abstract pathname.
		File[] dir=file.listFiles();	// 디렉토리 파일 객체(클래스)를 배열로 받음
										// 파일 하나하나에 매칭되는 객체
		// String[] dir=file.list();	// 디렉토리의 명단만 받음
		for(int i=0; i<dir.length; i++){
			/*
			// 디렉토리만 출력
			if(dir[i].isDirectory()){
				System.out.println(dir[i]);
			}
			*/
			// 파일만 출력
			if(dir[i].isFile()){
				System.out.println(dir[i]);
			}
		}
	}
}
