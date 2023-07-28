package bitcamp.personalapp.handler;

import java.io.IOException;

import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.personalapp.vo.Diary;
import bitcamp.util.BreadcrumbPrompt;

public class DiaryUpdateListener implements DiaryActionListener {

	DiaryDao diaryDao;
	SqlSessionFactory sqlSessionFactory;
    
    public DiaryUpdateListener(DiaryDao diaryDao, SqlSessionFactory sqlSessionFactory) {
    	this.diaryDao = diaryDao;
    	this.sqlSessionFactory = sqlSessionFactory;
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
        d.setContents(prompt.inputString("내용(%s)? >", d.getContent()));
        d.setCoffee(DiaryActionListener.inputCoffee(d.getCoffee(), prompt));
            
        try {
         diaryDao.update(d);
         sqlSessionFactory.openSession(false).commit();
         
        } catch(Exception e) {
        	sqlSessionFactory.openSession(false).rollback();
        	throw new RuntimeException(e);
        }
        }
      
    
 
   
    }


