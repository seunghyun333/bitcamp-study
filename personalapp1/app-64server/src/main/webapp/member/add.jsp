<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp" %>

<%@ page import="bitcamp.personalapp.vo.Member"%>
<%@ page import="bitcamp.personalapp.vo.AttachedFile"%>

<jsp:useBean id="memberDao" type="bitcamp.personalapp.dao.MemberDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>
<jsp:useBean id="ncpObjectStorageService" type="bitcamp.util.NcpObjectStorageService" scope="application"/>

<% 
	request.setAttribute("refresh", "2;url=list.jsp");

    Member m = new Member();
    m.setName(request.getParameter("name"));
    m.setEmail(request.getParameter("email"));
    m.setPw(request.getParameter("pw"));
    m.setTel(request.getParameter("tel"));


    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
      String uploadFileUrl = ncpObjectStorageService.uploadFile("bitcamp-nc7-bucket-07",
          "member/", photoPart);
      m.setPhoto(uploadFileUrl);
    }

  
      memberDao.insert(m);
      sqlSessionFactory.openSession(false).commit();
      response.sendRedirect("list.jsp");
%>

  




