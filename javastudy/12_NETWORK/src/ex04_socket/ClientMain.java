package ex04_socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {

	public static void main(String[] args) {

		Socket clientSocket = null;
		
		try {
			
			// * socket는 바이트 스트림을 입출력하는데 사용되며, 그를 위한 getinputstream, getoutputstream 메서드를 가지고있다
			
			// 1. socket 생성
			clientSocket = new Socket();
			
			// 2. 서버 접속(* clientsocket을 입력출력을 둘다 쓰는 이유 => clientsocket에 서버주소가 저장되있기 때문)
			clientSocket.connect(new InetSocketAddress("localhost", 9099));
			
			// 3. 서버에 접속되면 welcome 메시지가 넘어옴
			// => 서버가 dataoutputstream의 writeutf로 메시지를 전송하므로, 클라이언트는 datainputstream readutf() 메시지를 받음
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			String message = in.readUTF();
			System.out.println("[클라이언트]" + message);
			
			// * clientsicket인 이유는 대상이 되는 곳이기 때문?
			
			
			// 4. scanner 클래스를 이용해 입력받은 데이터를 서버로 보내기
			Scanner sc = new Scanner(System.in);
			System.out.println("서버로 전송할 메시지 >>>");
			String send = sc.nextLine();
			OutputStreamWriter out = new OutputStreamWriter(clientSocket.getOutputStream());
			// (clientsocket에 정보가 담겨있기 때문)
			out.write(send);							// * getbyte("utf-8) => writer은 문자열을 보내기 때문에 변환이 필요없음
			
			// * socket 클래스 => getoutputstream, inputstream 메서드를 가지고있음
			
			out.close();
			in.close();
			sc.close();
			
			
			
			
			
		} catch (IOException e) {
			
		} finally {
			try {
				if(clientSocket.isClosed() == false) {
					System.out.println("[클라이언트] 클라이언트 종료");
					clientSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
