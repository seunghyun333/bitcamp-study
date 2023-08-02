package bitcamp.personalapp.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Component;

@Component("/diary/delete")
public class DiaryDeleteListener implements ActionListener {

  DiaryDao diaryDao;
  SqlSessionFactory sqlSessionFactory;

  public DiaryDeleteListener(DiaryDao diaryDao, SqlSessionFactory sqlSessionFactory) {
    this.diaryDao = diaryDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }


  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {



    try {
      if (diaryDao.delete(prompt.inputInt("삭제할 번호?")) == 0) {
        prompt.println("무효한 번호입니다!! ");
      }
      prompt.println("삭제 완료 ! ");
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);

    }

  }

}


