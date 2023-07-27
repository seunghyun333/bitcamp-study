package bitcamp.personalapp.handler;

import java.io.IOException;

import bitcamp.personalapp.dao.VisitDao;
import bitcamp.personalapp.vo.Visit;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class VisitAddListener implements ActionListener {

  VisitDao visitDao;
  DataSource ds;


  public VisitAddListener(VisitDao visitDao, DataSource ds) {
    this.visitDao = visitDao;
    this.ds =ds;
  }


  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {

    Visit visit = new Visit();

    visit.setName(prompt.inputString("이름을 적어주세요 ♡"));
    prompt.printf("%s아~ 고마워♡\n", visit.getName());

    try {
    visitDao.insert(visit);
    ds.getConnection().commit();
    } catch (Exception e) {
    	try {ds.getConnection().rollback();} catch (Exception e2) {}
    	throw new RuntimeException(e);
    }
  }

}


