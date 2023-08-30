<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bitcamp.personalapp.vo.Board"%>
<%@ page import="bitcamp.personalapp.vo.Comment"%>
<%@ page import="bitcamp.personalapp.vo.AttachedFile"%>

<jsp:useBean id="boardDao" type="bitcamp.personalapp.dao.BoardDao" scope="application"/>
<jsp:useBean id="commentDao" type="bitcamp.personalapp.dao.CommentDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>
<jsp:useBean id="ncpObjectStorageService" type="bitcamp.personalapp.service.NcpObjectStorageService" scope="application"/>
<jsp:useBean id="loginUser" class="bitcamp.personalapp.vo.Member" scope="session"/>
<jsp:useBean id="cno" class="bitcamp.personalapp.vo.Board" scope="session"/>

<%
    if (loginUser.getNo() == 0) {
      response.sendRedirect("../auth/form.jsp");
      return;
    }

	request.setAttribute("refresh", "2;url=list.jsp");


      Comment comment = new Comment();
      comment.setMno(loginUser);
      comment.setNo(Integer.parseInt(request.getParameter("no")));
      comment.setCno(cno);
      comment.setContent(request.getParameter("content"));



      if (commentDao.update(comment) == 0) {
        throw new Exception("게시글이 없거나 변경 권한이 없습니다.");
      } else {   
        sqlSessionFactory.openSession(false).commit();
      sqlSessionFactory.openSession(false).rollback();
    }

%>


