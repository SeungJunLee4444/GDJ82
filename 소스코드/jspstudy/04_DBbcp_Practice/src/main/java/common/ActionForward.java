package common;

public class ActionForward {

	// [ActionForward] : 경로 반환용 클래스
	// # 필드
	// (1) view : ???
	private String view;
	// (2) isRedirect : true면 redirect처리, false면 forward 처리
	private boolean isRedirect;

	
	// # 생성자, getter,setter처리
	// - 롬복으로 처리
	
	// (1) view : setview(), getview()
	// (2) isRedirect : isRedirect() , setRedirect()	* boolean의 getter,setter은 다르다

}
