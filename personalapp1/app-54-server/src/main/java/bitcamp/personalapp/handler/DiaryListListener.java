package bitcamp.personalapp.handler;

import java.util.List;
import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.vo.Diary;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Component;

@Component("/diary/list")
public class DiaryListListener implements ActionListener {

  DiaryDao diaryDao;


  public DiaryListListener(DiaryDao diaryDao) {
    this.diaryDao = diaryDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    prompt.println("--------------------------------------------");
    prompt.println("번호, 날짜, 날씨, 제목, 모닝커피");
    prompt.println("--------------------------------------------");

    List<Diary> list = diaryDao.findAll();
    for (Diary diary : list) {
      prompt.printf("%d, %s, %s, %s, %s\n", diary.getNo(), diary.getDate(), diary.getWeather(),
          diary.getTitle(), diary.getCoffee() == 'O' ? "마심" : "안 마심");
    }
  }



}


