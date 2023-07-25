package bitcamp.personalapp.handler;

import bitcamp.personalapp.ClientApp;
import bitcamp.personalapp.dao.VisitDao;
import bitcamp.personalapp.vo.Visit;
import bitcamp.util.BreadcrumbPrompt;

public class LoginListener implements VisitActionListener {

  VisitDao visitDao;


  public LoginListener(VisitDao visitDao) {
    this.visitDao = visitDao;
  }



  @Override
  public void service(BreadcrumbPrompt prompt) {

    while (true) {

      Visit v = new Visit();
      v.setName(prompt.inputString("이름을 입력하세요!"));

      Visit loginUser = visitDao.findByIdAndPassword(v);
      if (loginUser == null) {
        System.out.println("방문자 정보가 일치하지 않습니다!");
      } else {
        ClientApp.loginUser = loginUser;
        break;
      }
    }
  }
}


