<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp" %>
<%@ page import="java.text.SimpleDateFormat"%> 
<%@ page import="java.util.List"%>
<%@ page import="bitcamp.personalapp.vo.Board" %>
<%@ page import="bitcamp.personalapp.vo.Member" %>


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

<h1>내가 쓴 글</h1>
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
	SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 날짜 포맷터 초기화
%>
<tbody>

<%
	Member loginUserMember = (Member) session.getAttribute("loginUser");
	String loginUser = loginUserMember.getName();
  	for (Board board : list) {
    	if (board.getMno() != null && board.getMno().getName().equals(loginUser)) {
%>

   	
	<tr>
	<td>${board.no}</td> 
	<td><a href='/board/detail.jsp?no=${board.no}'>
	${board.title.length() > 0 ? board.title : "제목없음"}
	</a></td>
	<td>${board.mno.name}</td> 
	<td>${board.v_count}</td> 
	<td><%= board.getW_date() != null ? simpleDateFormatter.format(board.getW_date()) : "" %></td>
	</tr>
<%
    }
    }
%>	
	</tbody>
    </table>
    <a href='/'>메인</a>
    
    <jsp:include page="../footer.jsp"/>

</body>
</html>
  





