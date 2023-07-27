package bitcamp.personalapp.handler;

import java.io.IOException;

import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class DiaryDeleteListener implements ActionListener {
	
	DiaryDao diaryDao;
	DataSource ds;
    
    public DiaryDeleteListener(DiaryDao diaryDao, DataSource ds) {
    	this.diaryDao = diaryDao;
    	this.ds = ds;
    }


    public void service(BreadcrumbPrompt prompt) throws IOException {
    	try {
    	if(diaryDao.delete(prompt.inputInt("삭제할 번호?"))==0) {
    		prompt.println("무효한 번호입니다!! ");
    	} 
    		prompt.println("삭제 완료 ! ");
    		ds.getConnection().commit();
    		
    	} catch (Exception e) {
    		try {ds.getConnection().rollback();} catch (Exception e2) {}
    		throw new RuntimeException(e);
    		
    	}
    	
      }
   
    }


