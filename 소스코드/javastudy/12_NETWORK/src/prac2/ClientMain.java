package prac2;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
	
	// client가 server에 scanner로 메시지를 보내는 코드
	
	
	public static void main(String[] args) {
		
	
	Socket socket = null;
	Scanner sc = null;
	BufferedWriter out = null;
	
	
	try {
		
		socket = new Socket();
		socket.connect(new InetSocketAddress("localhost", 9090));
		
		
		Client client = new Client(socket);
		client.start();
		// 스레드

		sc = new Scanner(System.in);
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		// * 서버와 연결된 socket
		
		while(true) {
			// 1. 출력
			String message = sc.nextLine();
//			if(message.equalsIgnoreCase("exit")) {
//				break;
//			}
			out.write(message);
			out.flush();
			
		}
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			if(out != null) {
				out.close();
			}
			if(socket.isClosed() == false) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	}
}
