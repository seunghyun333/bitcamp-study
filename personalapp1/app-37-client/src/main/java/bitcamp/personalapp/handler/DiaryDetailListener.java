package bitcamp.personalapp.handler;

import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.vo.Diary;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class DiaryDetailListener implements ActionListener {
	
	DiaryDao diaryDao;
    
    public DiaryDetailListener(DiaryDao diaryDao) {
    	this.diaryDao = diaryDao;
    }


    public void service(BreadcrumbPrompt prompt) {
        int diaryNo = prompt.inputInt("일기 번호? ");
        
        Diary diary = diaryDao.findBy(diaryNo);
        if(diary == null) {
            System.out.println("해당 번호의 일기가 없습니다.");
            return;
        }

            System.out.printf("날짜 : %s \n", diary.getDate());
            System.out.printf("날씨 : %s \n", diary.getWeather());
            System.out.printf("제목 : %s \n", diary.getTitle());
            System.out.printf("내용 : %s \n", diary.getContents());
            System.out.printf("모닝커피 : %s \n", diary.getCoffee() == 'O' ? "마심" : "안 마심");
          }   
    }


