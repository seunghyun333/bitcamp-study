<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp" %>
<%@ page import="java.text.SimpleDateFormat"%> 
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bitcamp.personalapp.vo.Board" %>

<jsp:useBean id="loginUser" class="bitcamp.personalapp.vo.Member" scope="session"/>
<jsp:useBean id="boardDao" type="bitcamp.personalapp.dao.BoardDao" scope="application"/>


<% 
   request.setAttribute("refresh", "2;url=list.jsp" );
	if (loginUser.getNo() == 0) {
    response.sendRedirect("/auth/form.jsp");
    return;
  }

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

<h1 style='margin:5px; text-align:center'>☆ my diary ☆</h1>
<div style='margin:5px; text-align:center'>
<a href='/board/form.jsp'>새 글</a>
</div>




<table class="center-table">

<thead>
  <tr><th>번호</th> <th>제목</th> <th>작성자</th> <th>조회수</th> <th>등록일</th></tr>
</thead>



<%
    List<Board> list = boardDao.findAll();
	List<Board> allList = boardDao.findAll();
	List<Board> filteredList = new ArrayList<>();
	
	int loggedInUserNo = loginUser.getNo();
	
	for (Board board : allList) {
	    if (board.getMno().getNo() == loggedInUserNo) {
	        filteredList.add(board);
	    }
	}	
%>
<tbody>

<%
  for (Board board : filteredList) {
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
  





