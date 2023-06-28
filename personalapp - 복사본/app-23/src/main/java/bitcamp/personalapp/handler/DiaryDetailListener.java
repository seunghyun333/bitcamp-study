package bitcamp.personalapp.handler;

import bitcamp.personalapp.vo.Diary;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class DiaryDetailListener extends AbstractDiaryListener {
    
    public DiaryDetailListener(List list) {
    	super(list);
    }


    public void service(BreadcrumbPrompt prompt) {
        int diaryNo = prompt.inputInt("일기 번호? ");
        
        Diary diary = this.findBy(diaryNo);
        if(diary == null) {
            System.out.println("해당 번호의 일기가 없습니다.");
            return;
        }

            System.out.printf("날짜 : %s \n", diary.getDate());
            System.out.printf("날씨 : %s \n", diary.getWeather());
            System.out.printf("제목 : %s \n", diary.getTitle());
            System.out.printf("내용 : %s \n", diary.getContents());
            System.out.printf("모닝커피 : %s \n", toCoffeeString(diary.getCoffee()));
          }
        
   
    
 
   
    }


