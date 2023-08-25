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
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>게시글 목록</h1>
<div style='margin:5px;'>
<a href='/board/form.jsp'>새 글</a>
</div>
<table border='1'>
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
   	
	<tr>
	<td>${board.no}</td> <td><a href='/board/detail.jsp?no=${board.no}'>${board.title}</a>
	</td><td>${board.mno.name}</td> <td>${board.v_count}</td> <td>${simpleDateFormmater.format(board.w_date)}</td></tr>
<%
    }
%>	
	</tbody>
    </table>
    <a href='/'>메인</a>
    
    <jsp:include page="../footer.jsp"/>

</body>
</html>
  





