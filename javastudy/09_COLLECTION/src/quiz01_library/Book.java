package quiz01_library;

// quiz01	:	프레임워크로 배열만들기
// 차이점
// (1)	addbook()에 allcheck가 없어짐 
// => 배열이 추가한만큼 늘어나기 때문에, 용량이 꽉찰일이 없기 때문
// (2)	removebook() - Empty check 필요함(예외처리로 변경)
// => removeBookByIndex()	: 인덱스로 지우기
// => removeBookObject()	: 객체 정보를 이용해 제거
// (3)	findbook() - Empty check 필요함(예외처리로 변경)
// (4)	printAllBook() - Empty check 필요함(예외처리로 변경)

// * 추가
// (5) modifyBook()		:	수정


public class Book {
	
	// 1. 디폴트, 개발자 생성자 둘다 만들기
	// 2. tostring, equals 꼭 만들어주기
	
	private int bookNo;
	private String title;
	private String author;
	
	
	
	public Book() {
		
	}

	public Book(int bookNo, String title, String author) {
		super();
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [bookNo=" + bookNo + ", title=" + title + ", author=" + author + "]";
	}
	// => 반환값을 문자열로 변환

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (bookNo != other.bookNo)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	// * object의 equals은 내용이 아닌 참조값을 비교한다
	
	
	
	
	
	
	
	

}
