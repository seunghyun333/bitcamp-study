package bitcamp.personalapp.handler;

import java.io.IOException;

import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.vo.Diary;
import bitcamp.util.BreadcrumbPrompt;

public class DiaryAddListener implements DiaryActionListener {

	DiaryDao diaryDao;
	SqlSessionFactory sqlSessionFactory;
    
    public DiaryAddListener(DiaryDao diaryDao, SqlSessionFactory sqlSessionFactory) {
    	this.diaryDao = diaryDao;
    	this.sqlSessionFactory = sqlSessionFactory;
    }


    public void service(BreadcrumbPrompt prompt) throws IOException {
    	
      Diary diary = new Diary();
      diary.setDate(prompt.inputString("날짜 ? "));
      diary.setWeather(prompt.inputString("날씨 ? "));
      diary.setTitle(prompt.inputString("제목 ? "));
      diary.setContents(prompt.inputString("내용 ? "));
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


