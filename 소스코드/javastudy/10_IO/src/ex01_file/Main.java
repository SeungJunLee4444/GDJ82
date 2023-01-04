package ex01_file;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;


public class Main {
	
	public static void m1() {
		
		// & 17일차 : IO
		// => input(입력) + output(출력) 을 줄여서 io라 한다
		// =>폴더, 파일 생성, 삭제, 리스트화
		
		// 2. 생성방법
		// => ne
		
		
		
		
		// 2) 기능 : 파일 및 디렉터리를 관리
		// 3) 생성방법
		// (1) new file(경로, 파일)
		// (2) new file(폴더 or 파일)
		
		// 4) 경로구분 방법
		// 윈도우: \(역슬래시), 리눅스 : / (슬래시)
		
		// 5) 폴더(디렉터리) 만들기 과정
		// (1) 새로운 폴더 만들기
		File dir = new File("C:\\storage");
		// => c드라이브 안에 storage
		// * c는 대문자, 소문자 상관없음
		// :(콜론) 은 드라이브라는 의미
		// * 자바에서는 \하나를 이스케이프로 인식하기 때문에, 
		// \를 쓰기 위해서는 \\두개 써야한다
		
		// (2) 폴더가 존재하지 않으면 만들겠다
		if(dir.exists() == false) {	//if(!dir.exist()) 같은 코드
		
			dir.mkdirs();		// * 폴더 하나, 다중 생성(폴더 아래 폴더) 자주사용
		} 
		// (3) 존재하면 삭제하겠다
		else {
			//dir.delete();			// * 지금 지우겠다
			//dir.deleteOnExit();	// * jvm이 종료되면 지운다
		}
	}
	
	public static void m2() {
		
		// 6) 파일 만들기
		File file = new File("C:\\storage", "my.txt");
		try {
			if(file.exists() == false) {
				file.createNewFile();	// * 파일 생성시에는 createnewfile
				
				// * 파일 오류 : exception
				// => 파일 만들다 실패할 수있으니 exception을 만들어라
			} else {
				file.delete();
			}
		} catch (IOException e) {
			// ** 개발할 때 넣는 catch 블록 코드
			e.printStackTrace(); 	// * 에러를 콘솔에 띄우는 코드
		} 
			
	}
	
	public static void m3() {
		
		File file = new File("C:\\storage", "my.txt");
		
		
		// 1) 파일 이름 알아낼 때 쓰는 메서드
		System.out.println("파일명:" + file.getName());
		
		// 2) 경로를 알아내는 메서드
		System.out.println("경로:" + file.getParent());
		
		// 3) 전체경로(경로 + 파일명)
		System.out.println("전체경로(경로 + 파일명" + file.getPath());
		
		// 4) 폴더인지 파일인지 확인
		System.out.println("폴더인가?:" + file.isDirectory());
		System.out.println("파일인가?:" + file.isFile());
		
		// 5) 마지막으로 수정된 날짜
		long lastModifiedDate = file.lastModified();
		System.out.println("수정한 날짜:" + lastModifiedDate);
		
		// 5-1) 날짜를 simpledateformat으로 형식으로 출력하기
		
		SimpleDateFormat sdf = new SimpleDateFormat("a hh:mm yyyy-MM-dd");
		// * a 는 오전오후 여부를 알려줌
		
		String lastModified = sdf.format(file.lastModified());
		System.out.println(lastModified);
		
		// 6) 파일 크기 확인하는법
		long size = file.length();
		// file의 length는 byte 단위로 나온다
		System.out.println("파일의 크기:" + size + "byte");
		// * 영어, 숫자는 1바이트, 한글(UTF8처리)은 2~3바이트 차지한다 **
		
		// => 수정한 날짜: 오전 09:50 2022-08-10
		
		// => 날짜타입이 long 타입
		// => timestamp값으로 반환하겠다
		
	}
	
	public static void m4() {
		
		// 7) 해당 폴더 내 모든 파일을 리스트로 열람
		// (1) 해당 폴더를 객체에 저장
		File dir = new File("c:\\GDJ");

		// (2) 해당 폴더 내 모든 파일, 폴더를 FILE 배열에 저장
		File[] list = dir.listFiles();
		// => 디렉토리에 있는 내부의 모든 파일, 폴더를 file객체로 불러온다
		for(int i = 0; i < list.length; i++) {
			System.out.println(list[i].getName());
		}
		
	
		
	}

	
	public static void q1() {
		
		File dir = new File("c:\\GDJ");
		File[] list = dir.listFiles();
		int dirCnt = 0;
		int fileCnt = 0;
		long totalSize = 0;
		for(File file : list) {
			if(file.isHidden()) {
				continue;	// 조건문만 벗어나고, 반복문으로 다시 돌아감
			}
			String lastmodified = new SimpleDateFormat("yyyy-MM-dd a hh:mm").format(file.lastModified());
			String directory = file.isDirectory() ? " <DIR>" : "     ";
			String size = "";

			if(file.isDirectory()) {
				directory = " <DIR>";
				size = "     ";
				dirCnt++;
			} else {
				directory = "     ";
				size = new DecimalFormat("#,##0").format(file.length())  + "";
				fileCnt++;
				totalSize += Long.parseLong(size.replace(",", ""));	// * 문자열에 포함된 ,를 지우고 숫자로 변환하기
			}
			String name = file.getName();
			System.out.println(lastmodified + " " + directory + " " + size + "  " + name);
			// * decimalformat.format는 숫자에 형식을 부여하여 작성할 수 있는 simpledateformat와 비슷한 메서드다
			}
		System.out.println(dirCnt + "개 디렉터리");
		System.out.println(fileCnt + "개 파일" + new DecimalFormat("#,##0").format(totalSize) + "바이트");
		
			System.out.println();
		} 

		
		// * ishidden
		// => 콘솔에는 숨김폴더도 전부 표시되기 때문에,
		// ishidden을 통해 숨김폴더인지 여부를 확인하고 이를 처리할 수 있다
	
	public static void m5() {
		
		// 9) 플랫폼마다 다른 경로 구분자 지원
		File file = new File("C:" + File.separator + "storage" + File.separator + "my.txt");	
		// => \\만 썼으면 리눅스에서는 작동 안헀을 것
		// => 다양한 환경에서 변경없이 쓸 수 있도록 File.separator을 경로표현에 사용
		System.out.println(file.getName());
	}
	
	public static void q2() {
		
		// # 파일삭제
		String sep = File.separator;
		
		// * 
		File file = new File("c:" + sep + "storage", "my.txt");
		if(file.exists()) {
			file.delete();
			
		}
		// => 파일을 지우고, 폴더를 지우지 않으면, 폴더가 안지워질 수 있다
		
		File dir = new File("c:" + sep + "storage");
		if(dir.exists()) {
			dir.delete();
		}
		
	
			
	}
		
	

	
	
	
	
	
	
	public static void main(String[] args) {
		//m1();
		//m2();
		m3();
		//m4();
		//m5();
		//q1();
		//q2();
	}

}
