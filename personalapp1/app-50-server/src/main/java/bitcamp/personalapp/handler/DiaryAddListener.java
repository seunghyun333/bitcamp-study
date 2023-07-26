package bitcamp.personalapp.handler;

import java.io.IOException;

import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.vo.Diary;
import bitcamp.util.BreadcrumbPrompt;

public class DiaryAddListener implements DiaryActionListener {

	DiaryDao diaryDao;
    
    public DiaryAddListener(DiaryDao diaryDao) {
    	this.diaryDao = diaryDao;
    }


    public void service(BreadcrumbPrompt prompt) throws IOException {
    	
      Diary diary = new Diary();
      diary.setDate(prompt.inputString("날짜 ? "));
      diary.setWeather(prompt.inputString("날씨 ? "));
      diary.setTitle(prompt.inputString("제목 ? "));
      diary.setContents(prompt.inputString("내용 ? "));
      diary.setCoffee(DiaryActionListener.inputCoffee((char) 0, prompt));

      diaryDao.insert(diary);
    }
    }


