package bitcamp.personalapp.handler;

import java.io.IOException;

import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.vo.Diary;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class DiaryUpdateListener implements DiaryActionListener {

	DiaryDao diaryDao;
	DataSource ds;
    
    public DiaryUpdateListener(DiaryDao diaryDao, DataSource ds) {
    	this.diaryDao = diaryDao;
    	this.ds = ds;
    }


    
    public void service(BreadcrumbPrompt prompt) throws IOException {
        int diaryNo = prompt.inputInt("수정할 일기 번호? ");
       
        
        Diary d = diaryDao.findBy(diaryNo);
        if (d == null) {
        	prompt.println("해당 번호의 일기가 없습니다. ");
        	return;
        }
        
        d.setDate(prompt.inputString("날짜(%s)? >", d.getDate()));
        d.setTitle(prompt.inputString("제목(%s)? >", d.getTitle()));
        d.setContents(prompt.inputString("내용(%s)? >", d.getContents()));
        d.setCoffee(DiaryActionListener.inputCoffee(d.getCoffee(), prompt));
            
        try {
         diaryDao.update(d);
         ds.getConnection().commit();
         
        } catch(Exception e) {
        	try {ds.getConnection().rollback();} catch (Exception e2) {}
        	throw new RuntimeException(e);
        }
        }
      
    
 
   
    }


