/*
FileInputStream 등은 개발자가 원하는 시점에 스트림을 생성할 수 있지만,
키보드와 같은 표준 입력 하드웨어의 경우엔 java가 스트림을 생성하지 못하고 해당 os가 관리하므로,
개발자는 os로부터 표준 입력 스트림을 얻어와야 한다.

java의 클래스 내 System 클래스의 static 멤버변수 중, 
즉 has a 관계로 보유한 InputStream형 in 객체가 바로 표준 입력 스트림을 받아놓은 클래스
따라서, 사용 시에 그냥 System.in;로 표기

문자 기반 스트림 클래스의 이름 규칙(한글이 깨지지 않음)
입력	~Reader
출력	~Writer

*/
package io;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;

class KeyBoardApp{
	public static void main(String[] args) 
	{
		InputStream is=System.in;

		// 문자 기반 스트림은 단독으로 존재하는 것이 아니라,
		// 이미 바이트 기반 스트림을 전제로 한다.
		// 따라서, 생성시 생성자의 인수에 바이트 기반 스트림을 넣는다
		// 스트림이 흘러가는 통로를 덧씌우는 느낌
		InputStreamReader reader=null;
		reader=new InputStreamReader(is);
		

		int data;
		try{
			while(true){
				// data=is.read();	// 1byte 읽어들임
				data=reader.read();	// 2byte 읽어들임
				System.out.print(data);
				// reader를 출력하면 Unicode 형
				System.out.print((char)data);	
				// 13과 10은 기능을 담당하는 특수문자에 해당하기 때문에 char로 변경했을 때 표현되지 않음
			}
			// read()가 한번 동작되기 때문에 enter에 해당하는 13(\n)과 10(\r)이 출력되지 않음
			// read()를 여러번 동작시키면 제대로 나옴
		}catch(IOException e){
			// read() 메소드 때문에 IOException 에러 발생 가능성이 있으므로 try-catch 사용
		}
	}
}