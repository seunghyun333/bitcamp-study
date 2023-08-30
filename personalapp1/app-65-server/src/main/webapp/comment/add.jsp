<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp" %>
<%@ page import="bitcamp.personalapp.vo.Board"%>
<%@ page import="bitcamp.personalapp.vo.Comment"%>


<jsp:useBean id="boardDao" type="bitcamp.personalapp.dao.BoardDao" scope="application"/>
<jsp:useBean id="commentDao" type="bitcamp.personalapp.dao.CommentDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>
<jsp:useBean id="loginUser" class="bitcamp.personalapp.vo.Member" scope="session"/>
<jsp:useBean id="currentBoard" class="bitcamp.personalapp.vo.Board" scope="session"/>




<%
	if (loginUser.getNo() == 0) {
    response.sendRedirect("/auth/form.jsp");
    return;
	}




    // 오류가 발생했을 때 refresh 할 URL을 미리 지정한다.
    request.setAttribute("refresh", "2;url=/board/list.jsp");

      Comment comment = new Comment();
      comment.setMno(loginUser);
      comment.setCno(currentBoard);
      comment.setContent(request.getParameter("content"));
      

      
      commentDao.insert(comment);

      sqlSessionFactory.openSession(false).commit();
      response.sendRedirect("/board/detail.jsp?no=" + currentBoard.getNo());
      
      


%>


