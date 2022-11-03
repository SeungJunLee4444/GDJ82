package ex03_hash;

public class Book {
	
	// 1. hash
	
	private int bookNo;
	private String title;
	
	public Book(int bookNo, String title) {
		super();
		this.bookNo = bookNo;
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book [bookNo=" + bookNo + ", title=" + title + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookNo;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
		
		// 객체가 동등한지 비교하고싶다 equals
		// 만약 hash를 사용한다? => equals,와 hashcode도 둘다 생성할것
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookNo != other.bookNo)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
	

}
