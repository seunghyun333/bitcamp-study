package bitcamp.personalapp.handler;

import java.io.IOException;

import org.apache.ibatis.session.SqlSessionFactory;

import bitcamp.personalapp.dao.DiaryDao;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public class DiaryDeleteListener implements ActionListener {
	
	DiaryDao diaryDao;
	SqlSessionFactory sqlSessionFactory;
    
    public DiaryDeleteListener(DiaryDao diaryDao, SqlSessionFactory sqlSessionFactory) {
    	this.diaryDao = diaryDao;
    	this.sqlSessionFactory = sqlSessionFactory;
    }


    public void service(BreadcrumbPrompt prompt) throws IOException {
    	
    	String password = prompt.inputString("비밀번호 입력: ");
  	  if (!"0318".equals(password)) {
  	    System.out.println("비밀번호가 틀렸습니다..");
  	    return;
  	  }
  	  
    	try {
    	if(diaryDao.delete(prompt.inputInt("삭제할 번호?"))==0) {
    		prompt.println("무효한 번호입니다!! ");
    	} 
    		prompt.println("삭제 완료 ! ");
    		sqlSessionFactory.openSession(false).commit();
    		
    	} catch (Exception e) {
    		sqlSessionFactory.openSession(false).rollback();
    		throw new RuntimeException(e);
    		
    	}
    	
      }
   
    }


