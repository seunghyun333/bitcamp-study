<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp" %>
<%@ page import="bitcamp.personalapp.vo.Member"%>


<% 
	request.setAttribute("refresh", "2;url=/auth/form.jsp");

    Member m = new Member();
    m.setEmail(request.getParameter("email"));
    m.setPw(request.getParameter("pw"));
    
    if (request.getParameter("saveEmail") != null) {
    	Cookie cookie = new Cookie("email", m.getEmail());
    	response.addCookie(cookie);
    } else {
    	Cookie cookie = new Cookie("email", "no");
    	cookie.setMaxAge(0);
    	response.addCookie(cookie);
    }
%>

    <jsp:useBean id="memberDao" type="bitcamp.personalapp.dao.MemberDao" scope="application"/>
    
<% 
    Member loginUser = memberDao.findByIdAndPassword(m);
    if (loginUser == null) {
    	throw new Exception("회원 정보가 일치하지 않습니다.");
    }
      session.setAttribute("loginUser", loginUser);
      response.sendRedirect("/");
%>

