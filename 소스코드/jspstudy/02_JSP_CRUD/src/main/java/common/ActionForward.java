package common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// * lib에 lombok.jar 파일을 저장하면, bulidpath 등록없이도 적용된다

public class ActionForward {
	
	// # ActionForward : 이동경로 클래스
	
		private String view;
		private boolean isRedirect;	// boolean 타입은 get은 isRedirect, set은 setRedirect
		
//		public String getView() {
//			return view;
//		}
//		public void setView(String view) {
//			this.view = view;
//		}
//		public boolean isRedirect() {
//			return isRedirect;
//		}
//		public void setRedirect(boolean isRedirect) {
//			this.isRedirect = isRedirect;
//		}
//		public ActionForward(String view, boolean isRedirect) {
//			super();
//			this.view = view;
//			this.isRedirect = isRedirect;
//		}
//		public ActionForward() {
//			super();
//			// TODO Auto-generated constructor stub
//		}
		
		

	
	
}
