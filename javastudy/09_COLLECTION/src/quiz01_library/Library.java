package quiz01_library;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

// quiz01	:	trycatch와 프레임워크 list 활용


public class Library {
	
	private Scanner sc; 	
	private List<Book> books; 	// * list 인터페이스를 사용함으로서 그에 속한 메서드를 사용가능하게된다
//	private int idx;			// * 배열이 없어지면서 자동으로 idx도 폐기
		
	public Library() {
		sc = new Scanner(System.in);
		books = new ArrayList<Book>();		
	}									

	private void addBook() {		
//		if(idx == books.length) {						// * 길이도 알아서 늘어나니 폐기				
//			System.out.println("더 이상 등록할 수 없습니다"); 
//			return;
		// * 배열의 길이에 한계가 없기 때문에, 꽉찰수가 없다
//		}
		System.out.println("=== 책등록 ===");
		System.out.print("책 번호 >>>"); 
		int bookNo = sc.nextInt();
		System.out.print("제목 입력 >>>"); 
		String title = sc.next();
		System.out.print("저자 입력 >>>");	
		String author = sc.next();
		Book book = new Book(bookNo, title, author);	
		books.add(book); 			
	}
	
	// # remove 1
	
	private void removeBookByIndex() {		
		// * book remove(int index)
		if(books.isEmpty()) {
			throw new RuntimeException("등록된 책이 한권도 없습니다");	// * unchecked니까 throws 생략가능
		}
		System.out.println("=== 책제거 ===");
		System.out.print("삭제할 책의 번호)>>> ");
		int bookNo = sc.nextInt();
		sc.nextLine();
		for(int i = 0, length = books.size(); i < length; i++) {
			if(books.get(i).getBookNo() == bookNo) {
				Book removeBook = books.remove(i);
				// * 책을 삭제하고, 삭제한 책의 번호값을 저장
				
//				System.arraycopy(books, i + 1, books, i, idx - i -1);
//				books[--idx] = null; 
				// * 알아서 빈배열칸을 당겨주니 필요없음
			
				System.out.println("책 번호가" + removeBook+ "인 책을 삭제했습니다");
				return;	// # 삭제 끝나면 바로 종료하기
			}
		}
		throw new RuntimeException("책 번호가" + bookNo + "인 책은 없습니다");
		}
	
	// # remove 2
		
	private void removeBookByObject() {
		// * boolean remove(Object obj)	: 책을 전달해주면 지워줌
		if(books.isEmpty()) {
			throw new RuntimeException("등록된 책이 한권도 없습니다");
		}
		System.out.println("=== 책등록 ===");
		System.out.print("책 번호 >>>"); 
		int bookNo = sc.nextInt();
		System.out.print("제목 입력 >>>"); 
		String title = sc.next();
		System.out.print("저자 입력 >>>");	
		String author = sc.next();
		Book book = new Book(bookNo, title, author);	
		if(books.remove(book)) {
			System.out.println(book + "책을 삭제했습니다");
		}
		throw new RuntimeException(book + "책은 없습니다");
	}

	
	
	private void findBook() {		// # 책 조회
		if(books.isEmpty()) {
			throw new RuntimeException("등록된 책이 한 권도 없습니다");
		}
		System.out.println("=== 책조회 ===");
		System.out.println("조회할 책제목 입력 >>> ");
		String title = sc.next();					
		for(int i = 0, length = books.size(); i < length; i++) {			
			if(books.get(i).getTitle().equals(title)) { 
				System.out.println(books.get(i));
				return;  
			}									
		} 
		throw new RuntimeException("제목이" + title + "인 책은 없습니다");
		
	}
	
	private void printAllBooks() {	// # 전체 조회
		if(books.isEmpty()) {
			throw new RuntimeException("등록된 책이 한 권도 없습니다");	
		}
		System.out.println("=== 전체조회 ===");
		for(Book book : books) {	
			System.out.println(book);
			// * tostring이 없으면 문자열값으로 변환되지않는다
		}
	}
	
	public void manage() {
		
		while(true) {
			try {
				System.out.print("1.추가 2.삭제 3.조회 4.전체목록 0.프로그램종료 >>> "); 
				int choice = sc.nextInt();		
				sc.nextLine();		
				switch(choice) {
				case 1: addBook(); break;	
				case 2: removeBookByIndex(); break;
				case 3: findBook(); break;
				case 4: printAllBooks(); break;
				case 0 : System.out.println("Library 프로그램 종료"); 
						return; // # manage() 메서드 종료
				default : System.out.println("알 수 없는 명령입니다. 다시 시도하세요");
				}
				} catch (InputMismatchException e) {
					sc.next();
					System.out.println("명령은 정수입니다");
				} catch (RuntimeException e) {
					System.out.println(e.getMessage());
				}
			// # 무한 반복문은 조건에 맞지 않으면 다시 입력할 수 있게만들어준다
			
			}
			
		}
	
		
		
		
		
	}
	
	

