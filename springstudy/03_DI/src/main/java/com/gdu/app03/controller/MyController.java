package com.gdu.app03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.gdu.app03.domain.Board;
import com.gdu.app03.domain.Notice;

// # 컨트롤러 에너테이션
// - 에너테이션 형태 : @Controller
@Controller
public class MyController {

	
	// # 웰컴페이지로 만들기 : 요청하기
	// - 요청 에너테이션 : @RequestMapping
	// - 대상 : default.jsp
	// - 설명 : 인덱스 반환시 컨텍스트 패스로 이동, 요청은 get방식
	/*
	 * @RequestMapping(value="/", method=RequestMethod.GET) // 해석 : / =>
	 * http://localhost:9090/app03으로 접속하면 처리되는 코드 public String welcome() { return
	 * "default";
	 */
		// * view resolver에 의해서 return "/web-inf/views/default.jsp"로 해석된다------------*
		// - view resolver은 servlet-context에서 확인가능
	//}
	
	/* 
	  GET  방식 : @RequestMapping(value="/" method=RequestMethod.GET
	  POST 방식 : @RequestMapping(value="/" method=RequestMethod.GET
	  * spring4부터 새로운 애너테이션으로 바꿔서 사용할 수 있다
	  - @GetMapping("/")
	  - @PostMapping("/")
	  // => 이거두개 사용해라
	 */
	// * 예시
	@GetMapping(value="/")	// 해석 : / => http://localhost:9090/app03으로 접속하면 처리되는 코드
	public String welcome2() {
		return "default";
		// * view resolver에 의해서 return "/web-inf/views/default.jsp"로 해석된다------------*
		// - view resolver은 servlet-context에서 확인가능
	}
	
	// * 주의 : 동일한 value값을 지닌 에너테이션은 오류가 발생한다 
	
	//------------------------------------------------------------------------------------------------------------------------------
	
	// # xml, 또는 자바에 저장된 자바빈을 컨트롤러로 가져오기
	
	// #) 1 XML방식으로 저장한 자바빈을 컨트롤러로 가지고 오기
	/*
	 - @bean 가져오는 에너테이션 종류
	  @Inject	 : 타입(class)이 일치하는 bean을 가져오는 에너테이션
	  @Resource	 : 이름(id)가 일치하는 bean을 가져오는 에너테이션
	  @Autowired : 타입(class)가 일치하는 에너테이션 					* 실무에서 쓰이는건 @Autowired ----------------------------*
	  
	  * inject와 autowired의 차이-----------------------------------------------------------------------------------------------------
	  (1) @inject	 : 오직 타입만 확인, 동일 타입이 다수 발견되면 @qualifier을 통해서 이름(id)구분, @qualifier가 없으면 최종오류 발생
	  => @qualifier작업을 해줘야하는 번거로움
	  (2) @resource  : id로만 구분 확인
	  (3) @Autowired : 일단 타입으로 구분, 동일한 타입이 다수일경우 차선책으로 필드의 객체명과 자바빈의 id를 비교---------------------
	  // => @qualifier이 내장되있음
	  
	 */
	
	// - bean 가져오기 3가지 방법( * 가져오는 타입이 중요하다)
	// 1. 필드로 생성된 bean 가져오기
	//		- 방법 : 필드마다 @Autowired를 추가한다 (필드가 10개면 @Autowired도 10개)
	//		- 특징 : 필드가 많으면 사용하지 않는다
	// 2. 생성자를 이용해 bean 가져오기
	//		- 방법 : 생성자의 '매개변수'로 bean 가져오기
	//		- 특징 : @Autowired를 작성할 필요, 써도되고 생략해도 상관없다
	// 3. setter메서드를 이용해 bean 가져오기
	// 		- 정의 : 메서드의 매개변수로 bean을 가져온다
	//		- @Autowired를 작성해야 한다
	//		- 일반적으로 setter을 사용하지만 setter가 아니여도 상관없다
	
	
	
	
	// # 실습 #######################################################################################################################
	
	
	// 1) xml방식으로 저장된 자바빈 @Autowired로 가져오기============================================================================/
	// * 특징
	// (1) xml방식은 xml 컨테이너에서 태그로 자바빈을 만들었다
	// (2) 자바빈의 id와 필드의 객체명, 또는 매개변수명이 동일해야한다
	
	
	// (1) 필드에 @Autowired : 필드의 객체명과 자바빈의 id가 동일해야한다---------------------------------------------/
	
	
	/*
	 * @Autowired private Board board1; private Board board2;
	 * 
	 * public void board() { System.out.println(board1.getBoardNo());
	 * System.out.println(board1.getTitle());
	 * System.out.println(board1.getCreateDate());
	 * 
	 * @GetMapping("board/detail")
	 * public void board() { System.out.println(board2.getBoardNo());
	 * System.out.println(board2.getTitle());
	 * System.out.println(board2.getCreateDate());
	 * 
	 * }
	 */
	
	// (2) 생성자에 @Autowired ----------------------------------------------------------------------------------------/
	
	/*
	 * private Board b1; private Board b2;
	 * 
	 * @Autowired public MyController(Board board1, Board board2) { // 매개변수는 자바
	 * 컨테이너의 메서드명, 또는 생성자명과 동일해야한다 super(); this.b1 = board1; this.b2 = board2; }
	 * 
	 * @GetMapping("board/detail") public void board() {
	 * System.out.println(b1.getBoardNo()); System.out.println(b1.getTitle());
	 * System.out.println(b1.getCreateDate()); System.out.println(b2.getBoardNo());
	 * System.out.println(b2.getTitle()); System.out.println(b2.getCreateDate()); }
	 */
	
	
	// (3) setter 메서드에 @Autowired
	
	private Board b1;
	private Board b2;
	
	// * 메서드 사용
	@Autowired
	public void setter(Board board1, Board board2) {
		this.b1 = board1;
		this.b2 = board2;
	}
	
	@GetMapping("board/detail")		// * 이거없으면 초기화면도 실행안된다?
	public void board() {
		System.out.println(b1.getBoardNo());
		System.out.println(b1.getTitle());
		System.out.println(b1.getCreateDate());
		System.out.println(b2.getBoardNo());
		System.out.println(b2.getTitle());
		System.out.println(b2.getCreateDate());
	}
	
	// & /베이스패키지/ 오류 : mapping문제로 인해 발생하는게 아닐까?-----------------------------------------------?
	
	
	
	
	
	// 실습 1) 필드로 생성된 bean 가져오기 : @Autowired사용----------------------------------------------------------------------------
	/* @Autowired */
//	private Board board;
	// - @Autowired의 기능 : @Autowired는 타입(class)이 일치하는 bean을 container에서 가져온다-------------------------------------------*
	// (타입이 'Board'인 빈을 container(root-context.xml)에서 가져왔다)
	
	// Container -------------------------
	// <bean id="board1" class="Board">~~~</bean>	=> 를 가져왔다
	//------------------------------------
	

//	@GetMapping("/board/detail") // = @GetMapping("board/detail") : 슬래쉬는 사용해도 안해도 상관없다
//	public void board() {
//		System.out.println(board.getBoardNo());
//		System.out.println(board.getTitle());
//		System.out.println(board.getCreateDate());
//	}--------------------------------------------------------------------------------------------------------------------------------------
	
	// 실습 2) 생성자를 이용해서 bean 가져오기
	// - 특징 : 생성자를 이용한 방법은 @Autowired가 필요없다
	// - 특징 : Board board 매개변수를 보고 bean의 타입이 board인 bean을 가져온다
//	private Board board;
//	
//	@Autowired // 생성자를 이용해 bean을 가져올때 @Autowired는 써도되고 생략해도 상관없다--------------------------------*
//	public MyController(Board board) {
//		super();
//		this.board = board;
//	}
//	
//	@GetMapping("/board/detail") // = @GetMapping("board/detail") : 슬래쉬는 사용해도 안해도 상관없다
//	public void board() {
//		System.out.println(board.getBoardNo());
//		System.out.println(board.getTitle());
//		System.out.println(board.getCreateDate());
//	}//---------------------------------------------------------------------------------------------------------------------------------------
//	
	// 실습 3) setter메서드를 이용해서 bean을 가져오는 방법
	// * setter을 쓰면 매개변수가 만들어지기 떄문에, 일반적으로 쓰인다

/*	private Board board;

	@Autowired	// * setter을 사용하는 경우에는 @Autowired를 써야한다
	public void setBoard(Board board) {
		this.board = board;
	}
	
	@GetMapping("/board/detail")
	public void board() {
		System.out.println(board.getBoardNo());
		System.out.println(board.getTitle());
		System.out.println(board.getCreateDate());
	}
	*/
	
	
	
	// # @Autowired 작동원리와 오류 --------------------------------#
	// #) 1 상황 : 필드에 생성된 @Autowired, 동일한 타입의 bean이 여러개 등록된 경우
	//	- 변수명을 자동으로 식별자(@qualifier)로 인식한다
	//  - 식별자(@qualifier)는 bean의 이름(id)이 일치하는 bean을 가져온다

	/*
	 * @Autowired private Board board1; // private Board b1;;
	 * 
	 * @Autowired private Board board2; // private Board b2;
	 */
	/*
	 * @GetMapping("board/detail") public void boardDetail() {
	 * System.out.println(board1.getBoardNo());
	 * System.out.println(board1.getTitle());
	 * System.out.println(board1.getCreateDate());
	 * System.out.println(board2.getBoardNo());
	 * System.out.println(board2.getTitle());
	 * System.out.println(board2.getCreateDate()); }
	 */
	//------------------------------------------------------------
	
	// #) 2 상황 : 생성자를 이용해 bean 가져오기 
	/*
	 * private Board b1; private Board b2;
	 * 
	 * public MyController(Board board1, Board board2) { // * 생성자방법은 필드가 아닌 매개변수로
	 * 주입됨 : 매개변수명과 자바빈의 id가 동일해야함 b1 = board1; b2 = board2; }
	 * 
	 * @GetMapping("board/detail") public void boardDetail() {
	 * System.out.println(board1.getBoardNo());
	 * System.out.println(board1.getTitle());
	 * System.out.println(board1.getCreateDate());
	 * System.out.println(board2.getBoardNo());
	 * System.out.println(board2.getTitle());
	 * System.out.println(board2.getCreateDate()); }
	 */
	
	// #) 3 상황 : 메서드 이용해서 Bean 가져오기
	
	/*private Board b1;
	private Board b2;
	
	@Autowired
	public void setB1(Board board1, Board board2) {
		this.b1 = board1;
		this.b2 = board2;
	}
	// => setter 메서드 두개를 합침 : @Autowired를 통해 매개변수가 자동으로 주입된다
	
	
	@GetMapping("board/detail")  // @GetMapping("/board/detail")
	public void boardDetail() {
		System.out.println(b1.getBoardNo());
		System.out.println(b1.getTitle());
		System.out.println(b1.getCreateDate());
		System.out.println(b2.getBoardNo());
		System.out.println(b2.getTitle());
//		System.out.println
	
	// @Autowired를 사용하는 이유 : @Inject + @Qualifier 실습 
	 /*
	 @Inject
	 @Qualifier(value="board1")
	 private Board b1;
	 
	 @Inject
	 @Qualifier(value="board2")
	 private Board b2;
	
	*/
	 
	 
	
	
	
	
	 // 2) 자바로 만든 빈을 @Autowired 어노테이션으로 가져오기  ==========================================================================/
	// * 특징
	// (1) 자바클래스를 이용한 자바 컨테이너에서 자바빈을 만들었다
	// (2) 자바빈의 매서드명, 또는 생성자명과 컨트롤러의 필드 객체명과 매개변수명이 동일해야한다
	
	// (1) 필드에 @Autowired : 자바의 경우, '필드의 객체명'과 '메서드의 이름'과 동일해야 @Autowired가 작동한다------------/
	
	/*
	 * @Autowired private Notice notice1;
	 * 
	 * @Autowired private Notice notice2;
	 * 
	 * @GetMapping("notice/detail") public void noticeDetail() {
	 * System.out.println(notice1.getNoticeNo());
	 * System.out.println(notice1.getTitle());
	 * System.out.println(notice2.getNoticeNo());
	 * System.out.println(notice2.getTitle()); }
	 */
	
	// (2) 생성자를 이용한 방법 : 매개변수명과 메서드의 이름과 동일해야한다-----------------------------------------------/
	/*
	 * private Notice n1; private Notice n2;
	 * 
	 * // # 생성자
	 * 
	 * @Autowired public MyController(Notice notice1, Notice notice2) { // : 매개변수
	 * 객체명과 자바빈의 메서드명이 동일해야한다 super(); this.n1 = notice1; this.n2 = notice2; }
	 * 
	 * @GetMapping("notice/detail") public void noticeDetail() {
	 * System.out.println(n1.getNoticeNo()); System.out.println(n1.getTitle());
	 * System.out.println(n2.getNoticeNo()); System.out.println(n2.getTitle()); }
	 */
	
	// (3) setter를 이용한 방법 : 매개변수명과 메서드의 이름과 동일해야한다-----------------------------------------------/
	
	

	private Notice n1;
	private Notice n2;
	
	// # 메서드
	@Autowired
	public void setController(Notice notice1, Notice notice2) {	// : setter 두개를 하나로 합친 형태 ( * 생성자와 반환타입의 유무가 다름)
		this.n1 = notice1;
		this.n2 = notice2;
	}

	@GetMapping("notice/detail") 
	public void noticeDetail() {
		System.out.println(n1.getNoticeNo());
		System.out.println(n1.getTitle());
		System.out.println(n2.getNoticeNo());
		System.out.println(n2.getTitle()); 
		}




		
	
}
