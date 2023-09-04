package bitcamp.personalapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import bitcamp.personalapp.dao.MemberDao;

@Component("/member/detail")
public class MemberDetailController implements PageController {
  MemberDao memberDao;

  public MemberDetailController(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    request.setAttribute("member", memberDao.findBy(Integer.parseInt(request.getParameter("no"))));
    return "/WEB-INF/jsp/member/detail.jsp";


  }
}
