package ex04_socket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

	public static void main(String[] args) {

		ServerSocket serverSocket = null;
		
		try {
			
			// 1. serversocket 생성
			serverSocket = new ServerSocket();
			
			// 2. inet 생성후 serversocket 연결, 호스트/ 포트번호 설정
			serverSocket.bind(new InetSocketAddress("localhost", 9099));
			
			// 3. 접속한 클라이언트 개수
			int clientCnt = 0;
			
			// 4. 종료없이 무한루프 서버
			while(true) {
				System.out.println("[서버] 클라이언트 접속 기다리는 중");
				
			// 5. 클라이언트 접속 및 카운팅
				Socket clientSocket = serverSocket.accept();
				clientCnt++;
				
			// 6. 클라이언트에게 "welcome" 메시지 전송
			// => byte 출력스트림에서 date는 writeutf를 이용해 문자열(한글)을 깨짐없이 보낼 수 있다 	* data 스트림을 쓰는 이유
				DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
				out.writeUTF("[서버] 게스트님 환영합니다" + clientCnt + "님 환영합니다!");
			
				
				// 9. 클라이언트가 보낸 메시지 확인
				InputStreamReader in = new InputStreamReader(clientSocket.getInputStream());
				char[] cbuf = new char[5];
				int readByte = 0;
				StringBuilder sb = new StringBuilder();
				while((readByte = in.read(cbuf)) != -1) {
					sb.append(cbuf, 0, readByte);
					// * byte 타입을 문자열로 변환해 stringbuilder에 저장
				}
				System.out.println(sb.toString());
				
				// * clientsocket을 전부 쓰기위해서는 제일 밑에 close를 해야한다
				out.close();				
				in.close();
				
				// 8. 클라이언트 접속 종료
//				clientSocket.close(); 		=> out.close가 대신한다
				
			// 7. 접속한 클라이언트 3명이면 무한루프중
			if(clientCnt == 3) {
				System.out.println("[서버] 서버 종료");
				serverSocket.close();
				break;
			}
			
			}
			
			
			// server 					client
			// 1. clientsocket.getoutputstream(출력은 목적지 기준으로 해야함)
			
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
