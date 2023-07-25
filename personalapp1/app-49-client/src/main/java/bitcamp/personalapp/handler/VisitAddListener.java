package bitcamp.personalapp.handler;

import bitcamp.personalapp.dao.VisitDao;
import bitcamp.personalapp.vo.Visit;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class VisitAddListener implements ActionListener {

  VisitDao visitDao;


  public VisitAddListener(VisitDao visitDao) {
    this.visitDao = visitDao;
  }


  @Override
  public void service(BreadcrumbPrompt prompt) {

    Visit visit = new Visit();

    visit.setName(prompt.inputString("이름을 적어주세요 ♡"));
    System.out.printf("%s아~ 고마워♡\n", visit.getName());
    System.out.printf("아이디 번호는 %d 야!♡\n", visit.getNo());

    visitDao.insert(visit);
  }

}


