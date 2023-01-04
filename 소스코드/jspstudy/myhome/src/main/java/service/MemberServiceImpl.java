package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ActionForward;
import domain.Member;
import memberDao.MemberDao;

public class MemberServiceImpl implements MemberService {

	@Override
	public ActionForward login(HttpServletRequest request, HttpServletResponse response) {
		
		// # 로그인 서비스
		
		// # 로그인을 위한 id,pw 파라미터값 가져오기
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// # 가져온 id,pw를 dto에 저장
		Member member = Member.builder()
				.id(id)
				.pw(pw)
				.build();
		
		// # dao의 login메서드 실행
		Member login = MemberDao.getInstance().login(member);
		if(login != null) {
			// # 성공
			// 로그인정보는 session에 저장
			// * session은 request를 이용해 session을 얻을 수 있다
			HttpSession session = request.getSession();
			
			// * session영역에 login속성에 로그인 되었다는 정보를 index.jsp로 넘긴다(로그인하면 index.jsp의 로그인 화면으로 전환시켜줌)
			session.setAttribute("login", login);	
			
			return new ActionForward(request.getContextPath(), true);	// getContextPath : index.jsp로 넘어가는것
		} else {
			try {	// 입출력이니 예외처리
			PrintWriter out = response.getWriter();		// => jsp페이지로 반환하기 때문에 script문을 사용하는것
			out.println("<script>");
			out.println("alert('로그인실패');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return null;	// * 반환값이 있는 메서드에서, if - else 문 둘다 return이 들어가야한다
		}

	}
	
	//------------------------------------------------------------------------------------------
	// # 로그아웃 메서드
	// * 'dao 쿼리문에 접근할 필요가 없음'
	// - 이유 : 로그인시 session 영역에 저장된 login 속성을 초기화하면 되기 때문

	@Override
	public ActionForward logout(HttpServletRequest request, HttpServletResponse response) {
		
		// 세션에 저장된 값을remove
		HttpSession session = request.getSession();
		session.invalidate();						// 세션 초기화
		
		
		return new ActionForward(request.getContextPath(), true);	// * 초기화면으로 돌아가는 경로 반환
		

	}
	
	//------------------------------------------------------------------------------------------

	// # 회원등록 메서드
	@Override
	public void register(HttpServletRequest request, HttpServletResponse response) {
		
		// # join.jsp에서 전달받은 파라미터 4개(id,pw,name,email)
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email = request.getParameter("email");

		// # dto에 저장
		Member member = Member.builder()
				.id(id)
				.pw(pw)
				.name(name)
				.email(email)
				.build();
		
		// # DB로 보내기(insertMember메서드 사용)
		int result = MemberDao.getInstance().insertMember(member);
		
		// # 응답 및 이동
		// * 등록은 actionforward를 반환하지 않고, 직접 경로를 작성한다
		// * 등록이 성공하면 초기화면으로 돌아가고, 실패하면 이전페이지로 돌아간다
		try {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if(result > 0) {
				
				// # 회원가입하면 로그인 처리
				// * 회원가입한 회원의 정보를 db에서 가져온 뒤 session에 login이라는 이름으로 올리기(속성 login의 이름은 통일해줘야한다)
				HttpSession session = request.getSession();
				// 올바른 코드
				session.setAttribute("login", MemberDao.getInstance().login(member));	// * dao의 login 메서드 : 모든 정보를 조회한 상태
				// 잘못된 코드
				session.setAttribute("login", member);	
				// => 설명 : 코드상 로그인하는데는 문제가 없으나(로그인 조건이 null이 아니면 되기 때문)
				// member은 날짜정보를 가지고 오지 않기 때문에 정확한 개인정보를 가져오는 것이 아니기 때문에 문제되는 코드다
				
				
				
				out.println("alert('환영합니다');");
				out.println("location.href='"+request.getContextPath()+"';");		// request.getContextPath() : 웰컴파일로 이동하라
			} else {
				out.println("alert('회원가입에 실패했습니다');");
				out.println("history.back();");
				}
			out.println("</script>");
			out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	//------------------------------------------------------------------------------------------
	
	// # 회원탈퇴 메서드

	@Override
	public void cancel(HttpServletRequest request, HttpServletResponse response) {
		
	// # request로 파라미터를 받지 않고 session 정보를 꺼내서 정보를 얻는다
	// session에 저장된 login에는 로그인마다 각각의 정보가 저장된다
		HttpSession session = request.getSession();
		
		// # 멤버 번호를 전달해서 delete를 실시
		Member login = (Member)session.getAttribute("login");
		int memberNo = login.getMemberNo();	
		int result = MemberDao.getInstance().deleteMember(memberNo);
		
		
		try {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			if(result > 0) {
				session.invalidate();	// * session 정보 초기화
				out.println("alert('탈퇴했습니다');");
				out.println("location.href='"+request.getContextPath()+"';");		// request.getContextPath() : 웰컴파일로 이동하라
			} else {
				out.println("alert('탈퇴에 실패했습니다');");
				out.println("history.back();");
				}
			out.println("</script>");
			out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	
		

	}

}
