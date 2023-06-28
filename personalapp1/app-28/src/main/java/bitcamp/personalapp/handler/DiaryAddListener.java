package bitcamp.personalapp.handler;

import java.util.List;

import bitcamp.personalapp.vo.Diary;
import bitcamp.util.BreadcrumbPrompt;

public class DiaryAddListener extends AbstractDiaryListener {


    
    public DiaryAddListener(List<Diary> list) {
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


