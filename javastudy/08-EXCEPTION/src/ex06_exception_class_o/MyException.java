package ex06_exception_class_o;

public class MyException extends Exception{

	private static final long serialVersionUID = -7774118171104436322L;	
	
	// 1. 직렬화 
	// => 클래스 구조 : seriarizable - throwable - exception - myexception 
	// (1) seriarizable : 직렬화, 메서드내용이 없는 타입 구분용 클래스
	// * serializable 인터페이스 : 이 인터페이스를 구현하면 직렬화가 가능
		// => 대신 serialversionUID 값을 가져야 한다
		// * serialversionUID : long타입, 개발자가 아닌 컴퓨터가 버전 비교에 사용
								// static final: 고유한 값
	
	// (2) throwable, exception, myexception : serialversionUID 값이 필요함
											 
	// 2. 사용자 정의 예외 클래스 규칙
	// => 규칙은 오직 "Exception 클래스 상속"받으면 된다
	
	private int errorCode;
	
	// * exception은 string message, myexception은 message를 상속받고, errorcode를 지님
	// * exception은 추천에서 string을 바로 사용 가능
	
	public MyException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
	
	


}
