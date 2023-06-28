package bitcamp.personalapp.handler;

import bitcamp.personalapp.vo.Diary;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class DiaryAddListener extends AbstractDiaryListener {


    
    public DiaryAddListener( List list) {
    	super(list);
    }


    public void service(BreadcrumbPrompt prompt) {
    	
      Diary diary = new Diary();
      diary.setDate(prompt.inputString("날짜 ? "));
      diary.setWeather(prompt.inputString("날씨 ? "));
      diary.setTitle(prompt.inputString("제목 ? "));
      diary.setContents(prompt.inputString("내용 ? "));
      diary.setCoffee(inputCoffee((char) 0, prompt));

      this.list.add(diary);
    }
    
   
    }


