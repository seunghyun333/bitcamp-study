package bitcamp.personalapp.handler;

import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.personalapp.dao.BoardDao;
import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.dao.VisitDao;

@WebServlet(value = "/init", loadOnStartup = 1)
public class InitServlet {

  public static SqlSessionFactory sqlSessionFactory;
  public static BoardDao boardDao;
  public static VisitDao visistDao;
  public static DiaryDao diaryDao;



}
