package bitcamp.personalapp.handler;

import java.io.IOException;
import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.vo.Diary;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Component;

@Component("/diary/detail")
public class DiaryDetailListener implements ActionListener {

  DiaryDao diaryDao;

  public DiaryDetailListener(DiaryDao diaryDao) {
    this.diaryDao = diaryDao;
  }


  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int diaryNo = prompt.inputInt("일기 번호? ");

    Diary diary = diaryDao.findBy(diaryNo);
    if (diary == null) {
      prompt.println("해당 번호의 일기가 없습니다.");
      return;
    }

    prompt.printf("날짜 : %s \n", diary.getDate());
    prompt.printf("날씨 : %s \n", diary.getWeather());
    prompt.printf("제목 : %s \n", diary.getTitle());
    prompt.printf("내용 : %s \n", diary.getContent());
    prompt.printf("모닝커피 : %s \n", diary.getCoffee() == 'O' ? "마심" : "안 마심");
  }
}


