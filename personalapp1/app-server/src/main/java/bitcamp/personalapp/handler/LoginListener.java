package bitcamp.personalapp.handler;

import java.io.IOException;
import bitcamp.personalapp.dao.VisitDao;
import bitcamp.personalapp.vo.Visit;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Component;

@Component("/auth/login")
public class LoginListener implements VisitActionListener {

  VisitDao visitDao;

  public LoginListener(VisitDao visitDao) {
    this.visitDao = visitDao;
  }


  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {

    while (true) {
      Visit v = new Visit();
      v.setName(prompt.inputString("이름을 입력하세요!"));

      Visit loginUser = visitDao.findByIdAndPassword(v);
      if (loginUser == null) {
        prompt.println("방문자 정보가 일치하지 않습니다!");
      } else {
        prompt.setAttribute("loginUser", loginUser);
        break;
      }
      prompt.end();
    }
  }
}


