package memberDao;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Notice;

public class NoticeDao {
	
	// # 싱글턴 코드==================================================
	private SqlSessionFactory factory;
	private static NoticeDao dao = new NoticeDao();
	
	private NoticeDao() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static NoticeDao getInstance() {
		return dao;
	}
	
	// # Dao ===========================================================
	
	// * mapper 저장 -------------------------
	String mapper = "mybatis.mapper.notice.";
	
	// # 전체 공지사항 수 ----------------------------------------------
	public int selectAllNoticesCnt() {
		SqlSession ss = factory.openSession();
		int cnt = ss.selectOne(mapper + "selectAllNoticesCnt");
		ss.close();
		return cnt;
	}
	
	// # 모든 공지사항 --------------------------------------------------
	public List<Notice> selectAllNotices(Map<String, Object> map) {
		SqlSession ss = factory.openSession();
		List<Notice> notices = ss.selectList(mapper + "selectAllNotices", map);
		ss.close();
		return notices;
	}

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	

}
