package bitcamp.personalapp.handler;

import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.vo.Diary;
import bitcamp.util.BreadcrumbPrompt;

public class DiaryUpdateListener implements DiaryActionListener {

	DiaryDao diaryDao;
    
    public DiaryUpdateListener(DiaryDao diaryDao) {
    	this.diaryDao = diaryDao;
    }


    
    public void service(BreadcrumbPrompt prompt){
        int diaryNo = prompt.inputInt("수정할 일기 번호? ");
       
        
        Diary d = diaryDao.findBy(diaryNo);
        if (d == null) {
        	System.out.println("해당 번호의 일기가 없습니다. ");
        	return;
        }
        
        d.setDate(prompt.inputString("날짜(%s)? >", d.getDate()));
        d.setTitle(prompt.inputString("제목(%s)? >", d.getTitle()));
        d.setContents(prompt.inputString("내용(%s)? >", d.getContents()));
            d.setCoffee(DiaryActionListener.inputCoffee(d.getCoffee(), prompt));
        }
      
    
 
   
    }


