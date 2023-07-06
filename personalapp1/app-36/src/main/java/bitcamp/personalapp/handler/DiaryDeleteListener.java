package bitcamp.personalapp.handler;

import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class DiaryDeleteListener implements ActionListener {
	
	DiaryDao diaryDao;
    
    public DiaryDeleteListener(DiaryDao diaryDao) {
    	this.diaryDao = diaryDao;
    }


    public void service(BreadcrumbPrompt prompt){
    	if(diaryDao.delete(prompt.inputInt("삭제할 번호?"))==0) {
    		System.out.println("무효한 번호입니다!! ");
    	}
      }
   
    }


