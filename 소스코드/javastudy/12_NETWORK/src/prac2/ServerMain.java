package prac2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {
	
	// # 생성된 서버목록
	public static List<Server> servers = new ArrayList<>();
	// * main에서 쓸꺼면 메서드는 무조건 static
	// * 뒤쪽은 <> 제네릭을 생략할 수 있다
	
	// # 모든 서버에 메시지 전송
	public static void sendMessage(String message) throws IOException {
	
//		for(Server server : servers) {
//			server.sendMessage(message);
//		}
	}
	
	public static void main(String[] args) {
		
		// server	: 스레드
		// client 	: 스레드 
		// server 하나당 client 하나씩 담당하도록 만듬
		
		ServerSocket server = null;
		Socket client = null;
		
		try {
			
			server = new ServerSocket();
			server.bind(new InetSocketAddress("localhost", 9090));
			// * 서버와 주소를 연결
			System.out.println("★★채팅 서버 오픈★★");
			
			while(true) {
				
				client = server.accept();
			// * 서버가 접속을 허용해주면 client 생성
				Server s = new Server(client);		// * 서버마다 한명의 client를 담당
				servers.add(s);
				
				s.start();
				
				System.out.println("현재 접속중인 클라이언트" + servers.size() + "명");
				

			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(server.isClosed() == false) {
					server.close();
				}
			}catch (IOException e) {
					e.printStackTrace();
				}			
				
		
		}
	}
}
