

<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp" %>
<%@ page import="java.text.SimpleDateFormat"%> 
<%@ page import="java.util.List"%>
<%@ page import="bitcamp.personalapp.vo.Board" %>
<%@ page import="bitcamp.personalapp.vo.Comment" %>


<% 
   request.setAttribute("refresh", "2;url=list.jsp" );
%>


<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>댓글</title>

<style>
  .center-table {
    margin: 0 auto;
    border-collapse: collapse;
  }
  .center-table th, .center-table td {
    border: 1px solid black;
    padding: 5px;
  }
</style>

</head>
<body>


<h1 style='margin:5px; text-align:center'>댓글</h1>
<div style='margin:5px; text-align:center'>
</div>




<table class="center-table">

<thead>
  <tr><th>번호</th> <th>작성자</th> <th>내용</th> <th>등록일</th> </tr>
</thead>

<jsp:useBean id="commentDao" type="bitcamp.personalapp.dao.CommentDao" scope="application"/>


<%
    List<Comment> list = commentDao.findAll();


%>
<tbody>

<%
  for (Comment comment : list) {
    pageContext.setAttribute("comment", comment);
    


%>
    
  <tr style='margin:5px; text-align:center'>
  <td>${comment.no}</td> 
  <td>${comment.mno.name}</td> 
  <td>${comment.content}</td> 
  <td>${simpleDateFormatter.format(comment.w_date)}</td>
  </tr>
<%
    }
%>  
  </tbody>
    </table>


</body>
</html>
  




