package bitcamp.personalapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.personalapp.dao.MemberDao;

public class MemberDeleteController implements PageController {
	
	MemberDao memberDao;
	SqlSessionFactory sqlSessionFactory;
	
	public MemberDeleteController(MemberDao memberDao, SqlSessionFactory sqlSessionFactory) {
		this.memberDao = memberDao;
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    try {
      if (memberDao.delete(Integer.parseInt(request.getParameter("no"))) == 0) {
        throw new Exception("해당 번호의 회원이 없습니다!! ");
      } else {
        sqlSessionFactory.openSession(false).commit();
        return "redirect:list";
      }

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}


