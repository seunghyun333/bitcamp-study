package bitcamp.personalapp.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.vo.Diary;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Component;

@Component("/diary/add")
public class DiaryAddListener implements DiaryActionListener {

  DiaryDao diaryDao;
  SqlSessionFactory sqlSessionFactory;

  public DiaryAddListener(DiaryDao diaryDao, SqlSessionFactory sqlSessionFactory) {
    this.diaryDao = diaryDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }


  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {

    String password = prompt.inputString("비밀번호 입력: ");
    if (!"0318".equals(password)) {
      System.out.println("비밀번호가 틀렸습니다..");
      return;
    }

    Diary diary = new Diary();
    diary.setDate(prompt.inputString("날짜 ? "));
    diary.setWeather(prompt.inputString("날씨 ? "));
    diary.setTitle(prompt.inputString("제목 ? "));
    diary.setContent(prompt.inputString("내용 ? "));
    diary.setCoffee(DiaryActionListener.inputCoffee((char) 0, prompt));

    try {
      diaryDao.insert(diary);
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}


