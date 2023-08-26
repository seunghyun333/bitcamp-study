<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bitcamp.personalapp.vo.Board"%>
<%@ page import="bitcamp.personalapp.vo.Member"%>


<jsp:useBean id="memberDao" type="bitcamp.personalapp.dao.MemberDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>

<%
	request.setAttribute("refresh", "2;url=list.jsp");
      if (memberDao.delete(Integer.parseInt(request.getParameter("no"))) == 0) {
        throw new Exception("해당 번호의 회원이 없습니다!! ");
      } else {
    	 sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("/member/list.jsp");
      }
%>
    





