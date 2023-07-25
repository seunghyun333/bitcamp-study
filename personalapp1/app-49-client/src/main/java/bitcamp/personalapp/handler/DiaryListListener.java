package bitcamp.personalapp.handler;

import java.util.List;

import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.vo.Diary;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class DiaryListListener implements ActionListener{
	
	DiaryDao diaryDao;

    
    public DiaryListListener(DiaryDao diaryDao) {
    	this.diaryDao = diaryDao;
    }

    public void service(BreadcrumbPrompt prompt){
        System.out.println("--------------------------------------------");
        System.out.println("번호, 날짜, 날씨, 제목, 모닝커피");      
        System.out.println("--------------------------------------------");

        List<Diary> list = diaryDao.list();
        for (Diary diary : list) {
          System.out.printf("%d, %s, %s, %s, %s\n", 
        diary.getNo(), diary.getDate(), diary.getWeather(), diary.getTitle(), 
        diary.getCoffee() == 'O' ? "마심" : "안 마심");
        }
      }  

 
   
    }


