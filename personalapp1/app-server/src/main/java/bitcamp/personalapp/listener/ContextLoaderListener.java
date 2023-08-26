package bitcamp.personalapp.listener;

import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.dao.CommentDao;
import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.dao.MemberDao;
import bitcamp.personalapp.dao.MySQLBoardDao;
import bitcamp.personalapp.dao.MySQLCommentDao;
import bitcamp.personalapp.dao.MySQLDiaryDao;
import bitcamp.personalapp.dao.MySQLMemberDao;
import bitcamp.util.NcpConfig;
import bitcamp.util.NcpObjectStorageService;
import bitcamp.util.SqlSessionFactoryProxy;

@WebListener
public class ContextLoaderListener implements ServletContextListener{
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {

		ServletContext ctx = sce.getServletContext();
		
		try {
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(
					new SqlSessionFactoryBuilder().build(
							Resources.getResourceAsStream(ctx.getInitParameter("mybatis-config"))));
			
			BoardDao boardDao = new MySQLBoardDao(sqlSessionFactory);
			MemberDao memberDao = new MySQLMemberDao(sqlSessionFactory);
			DiaryDao diaryDao = new MySQLDiaryDao(sqlSessionFactory);
			CommentDao commentDao = new MySQLCommentDao(sqlSessionFactory);
			NcpObjectStorageService ncpObjectStorageService = new NcpObjectStorageService(new NcpConfig());
			
			ctx.setAttribute("sqlSessionFactory", sqlSessionFactory);
			ctx.setAttribute("boardDao", boardDao);
			ctx.setAttribute("memberDao", memberDao);
			ctx.setAttribute("diaryDao", diaryDao);
			ctx.setAttribute("commentDao", commentDao);
			ctx.setAttribute("ncpObjectStorageService", ncpObjectStorageService);
			ctx.setAttribute("simpleDateFormatter", new SimpleDateFormat("yyyy-MM-dd"));
			
			System.out.println("ContextLoaderListener.contextInitialized() - 공통 객체 준비 완료");
			
			
		} catch (Exception e) {
			System.out.println("ContextLoaderListener.contextInitialized() - 실행 중 오류 발생");
			e.printStackTrace();
		}
	}

}
