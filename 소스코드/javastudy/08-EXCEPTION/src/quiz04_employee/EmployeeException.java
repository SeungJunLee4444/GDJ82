package quiz04_employee;

public class EmployeeException extends Exception {
	
	private static final long serialVersionUID = -1151349864955739722L;
	
	
	private int errorCode;
	

	public EmployeeException(String message, int errorCode) {
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
