<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp" %>
<%@ page import="java.text.SimpleDateFormat"%> 
<%@ page import="java.util.List"%>
<%@ page import="bitcamp.personalapp.vo.Board" %>


<% 
   request.setAttribute("refresh", "2;url=list.jsp" );
%>


<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>게시글</title>

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

<jsp:include page="../header.jsp"/>
<br>
<h1 style='margin:5px; text-align:center'>☆전체 게시글 목록☆</h1>
<div style='margin: 5px auto; text-align: center; margin-right: -300px;'>
<br>
<a href='/board/form.jsp'>새 글</a>
</div>




<table class="center-table">

<thead>
  <tr><th>번호</th> <th>제목</th> <th>작성자</th> <th>조회수</th> <th>등록일</th></tr>
</thead>

<jsp:useBean id="boardDao" type="bitcamp.personalapp.dao.BoardDao" scope="application"/>

<%
    List<Board> list = boardDao.findAll();
%>
<tbody>

<%
  for (Board board : list) {
    pageContext.setAttribute("board", board);
%>
   	
	<tr style='margin:5px; text-align:center'>
	<td>${board.no}</td> 
	<td><a href='/board/detail.jsp?no=${board.no}'>
	${board.title.length() > 0 ? board.title : "제목없음"}
	</a></td>
	<td>${board.mno.name}</td> 
	<td>${board.v_count}</td> 
	<td>${simpleDateFormatter.format(board.w_date)}</td>
	</tr>
<%
    }
%>	
	</tbody>
    </table>
    <a href='/'>메인</a>
    


    <jsp:include page="../footer.jsp"/>

</body>
</html>
  





