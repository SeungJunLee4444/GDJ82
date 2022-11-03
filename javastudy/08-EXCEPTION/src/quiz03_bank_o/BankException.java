package quiz03_bank_o;

public class BankException extends Exception {

	  private static final long serialVersionUID = 6483585115162653442L;
		// # exception은 seriarizable 족보를 가지고 있기 때문에, uid를 하나 만들어둘것
	  
	  private int errorCode;
	
	
	  public BankException(String message, int errorCode) {
		 super(message);
		this.errorCode = errorCode;
		// #2 생성자 형성(부모클래스의 메시지 상속, 에러코드 필드에 선언)
	}
	
	
	public int getErrorCode() {
		return errorCode;
	}
	
	
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	  
  
  
	

}
