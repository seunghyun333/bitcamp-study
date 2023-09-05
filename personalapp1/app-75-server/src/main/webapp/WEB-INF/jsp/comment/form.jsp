<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true" 
    errorPage="/error.jsp" %>
<%@ page import="bitcamp.personalapp.vo.Board" %>
<% Board currentBoard = (Board) session.getAttribute("currentBoard"); %>
<jsp:useBean id="loginUser" class="bitcamp.personalapp.vo.Member" scope="session"/>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>비트캠프</title>
<style>
  .custom-textarea {
   width: 300px; /* 원하는 너비로 조정하세요 
    height: 200px; /* 원하는 높이로 조정하세요 
    resize: vertical; /* 세로 방향으로만 조절 가능하도록 설정 
    font-size: 16px; /* 원하는 폰트 크기로 조정하세요 
  }
  .label {
    margin-bottom: 5px; 
  }
</style>
</head>
<body>



<form action='add' method='post'>


<div style="display: flex; flex-direction: column; align-items: center;">
<br>
<%
Board board = (Board) request.getAttribute("board");
%>
<label for="content" class="label"> 내용 </label> <textarea name="content" class="custom-textarea">댓글을 입력하세요</textarea>
게시글 번호<input type="number" name='cno' value='<%= board.getNo() %>'><br>
회원 번호<input type="number" name='mno' value='${loginUser.no}'><br>
  
  <button>등록</button>
</div>
</form>



</body>
</html>



