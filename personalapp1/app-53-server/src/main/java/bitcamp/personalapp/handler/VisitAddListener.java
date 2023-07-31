package bitcamp.personalapp.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.personalapp.dao.VisitDao;
import bitcamp.personalapp.vo.Visit;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class VisitAddListener implements ActionListener {

  VisitDao visitDao;
  SqlSessionFactory sqlSessionFactory;


  public VisitAddListener(VisitDao visitDao, SqlSessionFactory sqlSessionFactory) {
    this.visitDao = visitDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }


  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {

    Visit visit = new Visit();

    visit.setName(prompt.inputString("이름을 적어주세요 ♡"));
    prompt.printf("%s아~ 고마워♡\n", visit.getName());

    try {
      visitDao.insert(visit);
      sqlSessionFactory.openSession(false).commit();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}


