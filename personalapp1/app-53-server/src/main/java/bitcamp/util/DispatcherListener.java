package bitcamp.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.dao.MySQLBoardDao;
import bitcamp.personalapp.dao.MySQLDiaryDao;
import bitcamp.personalapp.dao.MySQLVisitDao;
import bitcamp.personalapp.dao.VisitDao;
import bitcamp.personalapp.handler.BoardAddListener;
import bitcamp.personalapp.handler.BoardDeleteListener;
import bitcamp.personalapp.handler.BoardDetailListener;
import bitcamp.personalapp.handler.BoardListListener;
import bitcamp.personalapp.handler.BoardUpdateListener;
import bitcamp.personalapp.handler.DiaryAddListener;
import bitcamp.personalapp.handler.DiaryDeleteListener;
import bitcamp.personalapp.handler.DiaryDetailListener;
import bitcamp.personalapp.handler.DiaryListListener;
import bitcamp.personalapp.handler.DiaryUpdateListener;
import bitcamp.personalapp.handler.LoginListener;
import bitcamp.personalapp.handler.VisitAddListener;
import bitcamp.personalapp.handler.VisitListListener;

public class DispatcherListener implements ActionListener {
	
  // 객체 보관소
  Map<String,Object> beanContainer = new HashMap<>();
  
  public DispatcherListener() throws Exception {
    //Mybatis 준비
	SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(
		new SqlSessionFactoryBuilder().build(
			Resources.getResourceAsStream("bitcamp/personalapp/config/mybatis-config.xml")));
	beanContainer.put("sqlSessionFactory", sqlSessionFactory);
  
	// DAO 준비
	DiaryDao diaryDao = new MySQLDiaryDao(sqlSessionFactory);
	BoardDao boardDao = new MySQLBoardDao(sqlSessionFactory);
	VisitDao visitDao = new MySQLVisitDao(sqlSessionFactory);
	beanContainer.put("diaryDao", diaryDao);
	beanContainer.put("boardDao", boardDao);
	beanContainer.put("visitDao", visitDao);
	
	// Listener 준비
	beanContainer.put("login", new LoginListener(visitDao));
	
	beanContainer.put("diary/add", new DiaryAddListener(diaryDao,sqlSessionFactory));
	beanContainer.put("diary/list", new DiaryListListener(diaryDao));
	beanContainer.put("diary/detail", new DiaryDetailListener(diaryDao));
	beanContainer.put("diary/update", new DiaryUpdateListener(diaryDao,sqlSessionFactory));
	beanContainer.put("diary/delete", new DiaryDeleteListener(diaryDao,sqlSessionFactory));
	
	beanContainer.put("board/add", new BoardAddListener(boardDao,sqlSessionFactory));
	beanContainer.put("board/list", new BoardListListener(boardDao));
	beanContainer.put("board/detail", new BoardDetailListener(boardDao,sqlSessionFactory));
	beanContainer.put("board/update", new BoardUpdateListener(boardDao,sqlSessionFactory));
	beanContainer.put("board/delete", new BoardDeleteListener(boardDao,sqlSessionFactory));
	
	beanContainer.put("visit/add", new VisitAddListener(visitDao,sqlSessionFactory));
	beanContainer.put("visit/list", new VisitListListener(visitDao));
}
  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
	  ActionListener listener = (ActionListener) beanContainer.get(prompt.getAttribute("menuPath"));
	  if (listener == null) {
		  throw new RuntimeException("해당 요청을 처리할 수 없습니다.");
	  }
	  listener.service(prompt);
  }
  
  public Object getBean(String name) {
	  return beanContainer.get(name);
  }
}
