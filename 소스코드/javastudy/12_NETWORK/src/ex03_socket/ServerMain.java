package ex03_socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

	public static void main(String[] args) {
		
		// * 개발할때 쓸 것은 아니지만, 프로젝트에서 상담원과의 채팅을 구현할 때(웹소켓) 사용될 수 있다
		// => 채팅관련에는 스레드를 배워야함
		
		// 1. ServerSocket
		// => '서버'(주)가 클라이언트와 통신(채팅)할 때 사용하는 클래스
		
		// 2. InetSocketAddress 	* inet = internet
		// => Socket 사용시 '호스트이름(url의 메인주소)과 포트번호를 관리하는 클래스
		
		// 3. Socket
		// => 클라이언트가 서버와 통신할 때 사용하는 클래스
		
		ServerSocket serverSocket = null;
		
		try {
			
			serverSocket = new ServerSocket(); // * IO error when opening the socket.
			
			InetSocketAddress address = new InetSocketAddress("localhost", 9090);
			// * 포트번호는 누가 사용하는 번호를 피해주면된다
			
		// 1 + 2) serversocket에 inetsocket 연결
			serverSocket.bind(address);
			
			// -------------------------------
			
		// 1.1) serverSocket는 무한루프로 구현
			while(true) {
				System.out.println("[서버] 클라이언트 접속 기다리는중");
			Socket client = serverSocket.accept(); // * accept 메서드 : socket 생성자 반환
			// => client가 받아들이는것
			
			// 1.2 inet도 연결해주기
			InetSocketAddress clientAddress = (InetSocketAddress)client.getRemoteSocketAddress();
			System.out.println("접속이 허용된 클라이언트 :" + clientAddress.getHostName());
			}
			// = gethostname을 쓰기위해 inetsocketaddress 를 선언
			// get.hostname => 아이피주소
			// 무한루프라 클라이언트접속 2번나옴
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(serverSocket.isClosed() == false) {
					serverSocket.close();
				} 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
		

	}

}
